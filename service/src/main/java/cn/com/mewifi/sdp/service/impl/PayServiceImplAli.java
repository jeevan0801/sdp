package cn.com.mewifi.sdp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mewifi.core.util.HttpUtil;
import cn.com.mewifi.core.util.MD5Util;
import cn.com.mewifi.core.util.SignUtil;
import cn.com.mewifi.sdp.config.PayAliConfig;
import cn.com.mewifi.sdp.service.IPayService;
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
     * @param fee     支付金额
     * @return
     */
    public String getPayUrl(String orderId, String fee) {
        Map<String, Object> sParaTemp = new HashMap<String, Object>();
        sParaTemp.put("service", payAliConfig.getService());
        sParaTemp.put("return_url", payAliConfig.getReturnUrl());
        sParaTemp.put("partner", payAliConfig.getPartner());
        sParaTemp.put("_input_charset", payAliConfig.getInputCharset());
        sParaTemp.put("notify_url", payAliConfig.getNotifyUrl());
        sParaTemp.put("out_trade_no", orderId);
        sParaTemp.put("subject", payAliConfig.getSubject());
        sParaTemp.put("payment_type", payAliConfig.getPaymentType());
        sParaTemp.put("total_fee", fee);
        sParaTemp.put("seller_id", payAliConfig.getSellerId());
        
        String text = SignUtil.getSortedString(sParaTemp);
        String signText = MD5Util.sign(text, payAliConfig.getPrivateKey(), payAliConfig.getInputCharset());
        
        sParaTemp.put("sign", signText);
        sParaTemp.put("sign_type", payAliConfig.getSignType());
        
        String url = payAliConfig.getSubmitUrl() + "?";
        url += HttpUtil.getUrlFromMap(sParaTemp);
        
        log.info("url={}", url);
        return url;
    }
}
