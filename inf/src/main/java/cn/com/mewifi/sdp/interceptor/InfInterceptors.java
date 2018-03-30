package cn.com.mewifi.sdp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017-9-15.
 */
@Configuration
public class InfInterceptors extends WebMvcConfigurerAdapter {
    
    @Autowired
    /****ip地址拦截器****/
    private AccessAddressInterceptor accessAddressInterceptor;
    @Autowired
    /****http请求截器****/
    private HttpLogInterceptor httpLogInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessAddressInterceptor).addPathPatterns("/**");
        registry.addInterceptor(httpLogInterceptor).addPathPatterns("/*.html");
        super.addInterceptors(registry);
    }
    
}
