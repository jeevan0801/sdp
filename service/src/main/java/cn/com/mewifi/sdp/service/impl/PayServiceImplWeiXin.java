package cn.com.mewifi.sdp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mewifi.core.util.JSONUtil;
import cn.com.mewifi.core.util.MathUtil;
import cn.com.mewifi.core.util.XMLUtil;
import cn.com.mewifi.sdp.config.PayWeiXinConfig;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.constant.SDPTypeEnum;
import cn.com.mewifi.sdp.service.IPayService;
import cn.com.mewifi.sdp.service.IPubService;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * author: wangjc
 * date: 2017/9/13 11:22
 */
@Service
@Slf4j
public class PayServiceImplWeiXin implements IPayService {
    
    @Autowired
    IPubService pubService;
    
    @Autowired
    PayWeiXinConfig payWeiXinConfig;
    
    @Override
    public String getPayUrl(String productId, String fee) {
        Map<String, Object> params = this.buildParamMap(productId, fee);
        String xmlStr = XMLUtil.toXml(params);
        log.info("微信支付参数:xmlStr={}", xmlStr);
        return null;
    }
    
    private Map<String, Object> buildParamMap(String productId, String fee) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("appid", payWeiXinConfig.getAppId()); // 调用接口提交的公众账号ID
        params.put("mch_id", payWeiXinConfig.getMchId()); // 微信支付分配的商户号
        params.put("nonce_str", MathUtil.getRandomString(32)); // 随机字符串，不长于32位
        params.put("body", ""); // 商品简单描述，该字段须严格按照规范传递 浏览器打开的移动网页的主页title名-商品概述,如:腾讯充值中心-QQ会员充值
        params.put("out_trade_no",
            pubService.getSerialNo(SDPTypeEnum.MEMBER.getSpType(), 20, PubConstant.Bool.YES.getCode())); // 商户系统内部的订单号,32个字符内、可包含字母,
                                                                                                         // 其他说明
        params.put("total_fee", fee); // 订单总金额，单位为分 //TODO:单位待确认
        
        Date now = new Date();
        params.put("notify_url", payWeiXinConfig.getNotifyUrl()); // 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
        params.put("trade_type", payWeiXinConfig.getTradeType()); // H5支付的交易类型为MWEB
        
        Map<String, Object> sceneInfo = new HashMap<>();
        Map<String, Object> h5Info = new HashMap<>();
        h5Info.put("type", payWeiXinConfig.getH5InfoType());
        h5Info.put("wap_url", payWeiXinConfig.getH5InfoWapUrl());
        h5Info.put("wap_name", payWeiXinConfig.getH5InfoWapName());
        sceneInfo.put("h5_info", h5Info);
        params.put("scene_info", JSONUtil.obj2JsonString(sceneInfo));// WAP网站应用 固定格式
        
        // 可不填的字段
        params.put("device_info", payWeiXinConfig.getDeviceInfo()); // 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB
        params.put("sign_type", payWeiXinConfig.getSingType()); // 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
        params.put("detail", "");// 单品优惠字段(暂未上线)
        params.put("attach", ""); // 附加数据,在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        params.put("fee_type", ""); // 符合ISO 4217标准的三位字母代码，默认人民币：CNY
        params.put("time_start", DateFormatUtils.format(now, "yyyyMMddhh24mmss"));// 订单生成时间，格式为yyyyMMddHHmmss
        params.put("time_expire",
            DateFormatUtils.format(DateUtils.addMinutes(now, Integer.valueOf(payWeiXinConfig.getMinOfExpired())),
                "yyyyMMddhh24mmss")); // 订单失效时间，格式为yyyyMMddHHmmss,最短失效时间间隔必须大于5分钟
        params.put("goods_tag", "");// 商品标记，代金券或立减优惠功能的参数
        params.put("product_id", productId); // trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义
        params.put("limit_pay", "");// 指定不能使用的卡支付; no_credit:信用i
        params.put("openid", "");// 用户在商户appid下的唯一标识,trade_type=JSAPI，此参数必传
        return params;
    }
}
