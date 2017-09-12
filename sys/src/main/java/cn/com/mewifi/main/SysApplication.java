package cn.com.mewifi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * description: author: wangjc date: 2017/8/31 01:55
 */
@SpringBootApplication
@ComponentScan({"cn.com.mewifi.sdp"})
@EnableConfigurationProperties()
public class SysApplication extends SpringBootServletInitializer {
    // public class InfApplication {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SysApplication.class);
    }
}