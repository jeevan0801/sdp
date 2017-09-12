package cn.com.mewifi.sdp.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;

/**
 * description:
 * author: wangjc
 * date: 2017/9/8 10:51
 */
@Configuration
public class FreemarkerConfig extends WebMvcConfigurerAdapter {
    /**
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }
    
    /**
     * @return
     * @throws IOException io
     * @throws TemplateException template
     */
    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer()
        throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths("classpath:templates", "src/main/resource/templates");
        factory.setDefaultEncoding("UTF-8");
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setConfiguration(factory.createConfiguration());
        return result;
    }
}

// #spring:
// # freemarker:
// # cache: false
// # charset: UTF-8
// # check-template-location: true
// # content-type: text/html
// # expose-request-attributes: true
// # expose-session-attributes: true
// # request-context-attribute: request
// # suffix: .html
