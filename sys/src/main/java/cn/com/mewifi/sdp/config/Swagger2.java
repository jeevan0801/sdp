package cn.com.mewifi.sdp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger描述信息
 * description:
 * author: wangjc
 * date: 2017/8/31 20:42
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 配置api信息
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("cn.com.mewifi.sdp.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 应用描述信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("SDP RESTful APIs")
            .description("--")
            .termsOfServiceUrl("")
            .contact("")
            .version("1.0")
            .build();
    }
}
