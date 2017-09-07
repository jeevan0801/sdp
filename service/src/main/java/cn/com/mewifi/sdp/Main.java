package cn.com.mewifi.sdp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import cn.com.mewifi.sdp.bo.db.SPInfo;
import cn.com.mewifi.sdp.dao.SPInfoDao;
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
@Slf4j
public class Main implements CommandLineRunner {
    
    @Autowired
    private SPInfoDao spInfoDao;
    
    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Override
    public void run(String... strings)
        throws Exception {
        SPInfo spInfo = spInfoDao.selectById("001");
        log.info(spInfo.toString());
        
        List<SPInfo> spInfoList = spInfoDao.selectAll();
        log.info(spInfoList.toString());
    }
}
