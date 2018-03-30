package cn.com.mewifi.sdp.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017-9-15.
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisScannerConfig {
    /*
     * @Bean public MapperScannerConfigurer mapperScannerConfigurer() { MapperScannerConfigurer mapperScannerConfigurer
     * = new MapperScannerConfigurer(); //获取之前注入的beanName为sqlSessionFactory的对象
     * mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory"); //指定xml配置文件的路径
     * mapperScannerConfigurer.setBasePackage("mybatis-mapper"); return mapperScannerConfigurer; }
     */
}
