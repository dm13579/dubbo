package com.dm;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:DubboBootServerApplication
 * @Description:TODO
 * @date 2019/12/5
 */
@EnableDubbo
@SpringBootApplication
public class DubboBootClientApplication {

    @Reference
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DubboBootClientApplication.class, args).close();
    }

    @Bean
    public ApplicationRunner getBean() {
        return args ->{
            System.out.println(userService.getUser(1));
        };
    }
}
