package cn.com.mewifi.sdp.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.com.mewifi.sdp.bo.db.SPInfo;
import lombok.Data;

/**
 * description:
 * author: wangjc
 * date: 2017/8/31 18:15
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spInfo")
public class SPConfigProperties {
    private List<SPInfo> spInfoList;
    
}
