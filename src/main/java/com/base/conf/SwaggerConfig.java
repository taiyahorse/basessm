package com.base.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**设置多个分组*/
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }
    /**配置了swagger的Docket的bean实例*/
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //获取项目的环境,判断是否处在自己设定的环境当中，如果有上述环境就返回true
        boolean profiles1 = environment.acceptsProfiles(profiles);
        System.out.println(profiles1);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组
                .groupName("黄")
                //是否启用Swagger,false是不启用，默认是true
                .enable(profiles1)
                .select()
                //RequestHandlerSelectors：配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any:表示扫描全部
                //none:都不扫描
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.base.controller,com.base.entity"))
                //paths():过滤扫描路径--表示只扫描如下路径的内容
                //.paths(PathSelectors.ant("com.example.swagger2/**"))
                .build();
    }
    /**配置Swagger信息：apiInfo*/
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("黄", "", "2695818931@qq.com");
        return new ApiInfo(
                "Swagger",//标题
                "API接口",//描述
                "1.0",//版本
                "urn:tos",
                contact,//联系人
                "Apache 2.0",//许可
                "http://www.apache.org/licenses/LICENSE-2.0",//许可地址
                new ArrayList<VendorExtension>());
    }


}
