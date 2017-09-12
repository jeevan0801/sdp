package cn.com.mewifi.sdp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * description:
 * author: wangjc
 * date: 2017/9/11 16:13
 */
@Configuration
@ConfigurationProperties(prefix = "sms")
@PropertySource("classpath:config/sms.properties")
@Data
public class SMSConfig {
    
    @Value("${maxCountPerDay}")
    private String maxCountPerDay;
    
    @Value("${maxInterval}")
    private String maxInterval;

    @Value("${lengthOfAuthCode}")
    private String lengthOfAuthCode;
    
}
