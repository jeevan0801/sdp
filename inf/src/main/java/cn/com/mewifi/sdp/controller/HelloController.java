package cn.com.mewifi.sdp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/30 16:29
 */
//@RestController
@Slf4j
public class HelloController {
    /**
     * test
     * 
     * @return
     */
    @RequestMapping(value = "/hello")
    public String sayHello() {
        
        log.info("sfsd");
        return "Hello Spring Boot!";
        
    }
}