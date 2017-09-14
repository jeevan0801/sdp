package cn.com.mewifi.sdp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.JSONUtil;
import cn.com.mewifi.core.util.MD5Util;
import cn.com.mewifi.core.util.SignUtil;
import cn.com.mewifi.sdp.config.PayAliConfig;
import cn.com.mewifi.sdp.constant.Result;
import cn.com.mewifi.sdp.service.IPayService;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付宝支付
 * description:
 * author: wangjc
 * date: 2017/9/5 09:57
 */
@Service
@Slf4j
public class PayServiceImplAli implements IPayService {
    
    @Autowired
    private PayAliConfig payAliConfig;
    
    /**
     * 返回支付宝支付的h5页面url
     * @param orderId 订单流水号
     * @param productCode 商品code
     * @return
     */
    public String getPayUrl(String orderId, String productCode) {
        Map<String, Object> sParaTemp = new HashMap<String, Object>();
        sParaTemp.put("service", payAliConfig.getService());
        sParaTemp.put("return_url", payAliConfig.getReturnUrl());
        sParaTemp.put("partner", payAliConfig.getPartner());
        sParaTemp.put("_input_charset", payAliConfig.getInputCharset());
        sParaTemp.put("notify_url", payAliConfig.getNotifyUrl());
        sParaTemp.put("out_trade_no", orderId);
        sParaTemp.put("subject", productCode);
        sParaTemp.put("payment_type", payAliConfig.getPaymentType());
        sParaTemp.put("total_fee", "0.01"); // TODO: fee从商品信息中获取
        sParaTemp.put("seller_id", payAliConfig.getSellerId());
        
        String text = SignUtil.getSortedString(sParaTemp, false);
        String signText =
            MD5Util.sign(text, payAliConfig.getPrivateKey(), payAliConfig.getInputCharset(), false, false);
        
        sParaTemp.put("sign", signText);
        sParaTemp.put("sign_type", payAliConfig.getSignType());
        
        String url = payAliConfig.getSubmitUrl() + "?";
        url += HttpUtil.getUrlFromMap(sParaTemp);
        
        log.info("url={}", url);
        return url;
    }
    
    @Override
    public ResultVO returnPage(Map<String, Object> params) {
        log.info("returnPage params:{}", params);
        return this.signTextVerify(params);
    }
    
    @Override
    public ResultVO notify(Map<String, Object> params) {
        log.info("notify params:{}", params);
        return this.signTextVerify(params);
    }
    
    /**
     * 校验从支付宝返回的数据
     * @param params
     * @return
     */
    private ResultVO signTextVerify(Map<String, Object> params) {
        boolean verifyFlag = SignUtil.verifyMd5StrByDefault(params, payAliConfig.getPrivateKey(), false, false);
        if (verifyFlag) {
            // 校验通过返回完整的数据
            String jsonString = JSONUtil.obj2JsonString(params);
            return ResultVOUtil.success(jsonString);
        }
        return ResultVOUtil.error(Result.pub_pay_signVerifyFail);
        
    }
}
