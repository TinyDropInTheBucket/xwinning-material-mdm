package com.winning.material.mdm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //使用配置类注解
@EnableSwagger2 //启用这个配置类
public class Swagger2 {
    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean //配置这个bean是为了让swagger扫描到controller
    public Docket createRestApi() {
        // 为swagger添加header参数可供输入
        /*ParameterBuilder userTokenHeader = new ParameterBuilder();
        ParameterBuilder userIdHeader = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        userTokenHeader.name("headerUserToken").description("userToken")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        userIdHeader.name("headerUserId").description("userId")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(userTokenHeader.build());
        pars.add(userIdHeader.build());*/
        
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.winning.material.mdm.rest"))
                .paths(PathSelectors.any())
                .build();
//              .globalOperationParameters(pars);
    }

    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("swagger2接口数据文档")
                /*// 设置联系人
                .contact(new Contact("留歌", "http://csylh.cn", "csylh36@163.com"))
                // 描述
                .description("留歌短视频，这里是描述信息")*/
                // 定义版本号
                .version("V1.0").build();
    }

}