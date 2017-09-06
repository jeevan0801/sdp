package cn.com.mewifi.sdp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * description:
 * author: wangjc
 * date: 2017/9/5 10:17
 */
@Configuration
@ConfigurationProperties(prefix = "pay.Ali")
@PropertySource("classpath:config/pay.ali.properties")
@Data
public class PayAliConfig {
    private String payName;
    private String submitUrl;
    private String service;
    private String returnUrl;
    private String partner;
    private String inputCharset;
    private String notifyUrl;
    private String subject;
    private String paymentType;
    private String sellerId;
    private String privateKey;
    private String publicKey;
    private String signType;
}
