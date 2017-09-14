package cn.com.mewifi.sdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.com.mewifi.sdp.config.PayWeiXinConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * Service 模块的启动入口
 * description:
 * author: wangjc
 * date: 2017/9/7 18:48
 */
@SpringBootApplication
@ComponentScan({"cn.com.mewifi.sdp"})
@EnableConfigurationProperties()
@EnableWebMvc
@Slf4j
public class Main extends SpringBootServletInitializer {// implements CommandLineRunner {

    @Autowired
    private PayWeiXinConfig payWeiXinConfig;

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        log.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx:"+payWeiXinConfig.getAppId());
        return application.sources(Main.class);
    }
    
    // @Override
    // public void run(String... strings)
    // throws Exception {
    // log.debug("debugdebugdebugdebugdebugdebugdebugdebugdebugdebugdebug");
    // // SPInfo spInfo = spInfoDao.selectById("001");
    // // log.info(spInfo.toString());
    // //
    // // List<SPInfo> spInfoList = spInfoDao.selectAll();
    // // log.info(spInfoList.toString());
    // }
}
