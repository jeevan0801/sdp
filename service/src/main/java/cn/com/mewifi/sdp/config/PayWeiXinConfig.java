package cn.com.mewifi.sdp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信支付配置参数
 * description:
 * author: wangjc
 * date: 2017/9/13 11:27
 */
@Configuration
@ConfigurationProperties(prefix = "pay.weixin")
@PropertySource("classpath:config/pay.weixin.properties")
@Data
public class PayWeiXinConfig {

//    @Value("${pay.weixin.appId}")
    private String appId;

//    @Value("${pay.weixin.mchId}")
    private String mchId;

//    @Value("${pay.weixin.deviceInfo}")
    private String deviceInfo;

//    @Value("${pay.weixin.singType}")
    private String singType;

//    @Value("${pay.weixin.tradeType}")
    private String tradeType;

//    @Value("${pay.weixin.body}")
    private String body;

//    @Value("${notifyUrl}")
    private String notifyUrl;

//    @Value("${h5InfoType}")
    private String h5InfoType;

//    @Value("${h5InfoWapUrl}")
    private String h5InfoWapUrl;

//    @Value("${h5InfoWapName}")
    private String h5InfoWapName;

//    @Value("${minOfExpired}")
    private String minOfExpired;

//    @Value("${apiKey}")
    private String apiKey;

}
