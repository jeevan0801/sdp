package cn.com.mewifi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * description: author: wangjc date: 2017/8/31 01:55
 */
@SpringBootApplication
@ComponentScan({"cn.com.mewifi.sdp.controller"})
public class SysApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }
}
