package com.wmk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableOpenApi
//@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建 API 应用
     * apiInfo() 增夹API相关信息
     * 通过 select()函数返回一个 ApiSelectorBuilder实例，用来控制哪些接口暴露给Swagger来展现
     * 本例采用指定扫描的包路径来指定要建立API的目录
     */
    @Bean
    public Docket restApi(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("标准接口")
                .apiInfo(apiInfo("Springboot使用Swagger3来构建Restful APIs", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wmk.springboot.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 创建该 API的基本信息（这些基本信息回展现在文档页面中）
     * 访问地址：
     *  生成接口信息，包括标题、联系人等.
     * */
    private ApiInfo apiInfo(String title, String version){
        return new ApiInfoBuilder()
                .title(title)
                .description("若有疑问，请百度")
                .contact(new Contact("百度","www.baidu.com", "baidu@qq.com"))
                .version(version)
                .build();
    }
}
