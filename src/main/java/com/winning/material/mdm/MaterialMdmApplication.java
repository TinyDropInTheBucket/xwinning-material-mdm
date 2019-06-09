package com.winning.material.mdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author f_zl
 * @version v1.0
 * @className MaterialMdmApplication
 * @description
 * @date 2019/6/9
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.winning.material.mdm")
@EnableSwagger2
/*@EntityScan("com.winning.material.mdm.domain")//扫描实体类
@ComponentScan(basePackages={"com.winning.material.mdm"})*/
public class MaterialMdmApplication {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(MaterialMdmApplication.class, args);
    }
}
