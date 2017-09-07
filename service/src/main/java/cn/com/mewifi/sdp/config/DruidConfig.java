package cn.com.mewifi.sdp.config;

import java.sql.SQLException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * author: wangjc
 * date: 2017/9/6 19:34
 */
@Configuration
@ConfigurationProperties(prefix = "spring.druid.datasource")
@PropertySource("classpath:config/spring.druid.properties")
@Data
@Slf4j
public class DruidConfig {
    private String url;
    
    private String username;
    
    private String password;
    
    private String driverClassName;
    
    private String initialSize;
    
    private String minIdle;
    
    private String maxActive;
    
    private String maxWait;
    
    private String timeBetweenEvictionRunsMillis;
    
    private String minEvictableIdleTimeMillis;
    
    private String validationQuery;
    
    private boolean testWhileIdle;
    
    private boolean testOnBorrow;
    
    private boolean testOnReturn;
    
    private boolean poolPreparedStatements;
    
    private String maxPoolPreparedStatementPerConnectionSize;
    
    private String filters;
    
    private String connectionProperties;

    /**
     * druid数据源
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setInitialSize(Integer.valueOf(initialSize));
        datasource.setMinIdle(Integer.valueOf(minIdle));
        datasource.setMaxActive(Integer.valueOf(maxActive));
        datasource.setMaxWait(Integer.valueOf(maxWait));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(timeBetweenEvictionRunsMillis));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(minEvictableIdleTimeMillis));
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource
            .setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(maxPoolPreparedStatementPerConnectionSize));
        try {
            datasource.setFilters(filters);
        }
        catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    /**
     * 映射druid的后台
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", "127.0.0.1"); // 白名单
        reg.addInitParameter("deny", ""); // 黑名单
        reg.addInitParameter("loginUsername", "admin");
        reg.addInitParameter("loginPassword", "admin");
        return reg;
    }

    /**
     * durid的例外
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
