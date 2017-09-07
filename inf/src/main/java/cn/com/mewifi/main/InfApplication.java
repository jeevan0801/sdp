package cn.com.mewifi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/30 15:56
 */
@SpringBootApplication
@ComponentScan({"cn.com.mewifi.sdp"})
@EnableConfigurationProperties()
@EnableWebMvc
public class InfApplication extends SpringBootServletInitializer {
    // public class InfApplication {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(InfApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InfApplication.class);
    }
}
