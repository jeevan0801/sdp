package cn.com.mewifi.sdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * description: author: wangjc date: 2017/8/30 16:29
 */
@Controller
@Slf4j
public class HelloController {
    
    /**
     * test
     * 
     * @return
     */
    @RequestMapping(value = "/sys/hello")
    public String sayHello(ModelMap modelMap) {
        String userName = "wang";
        modelMap.put("name", userName);
        return "sys/test1";
        
    }
}