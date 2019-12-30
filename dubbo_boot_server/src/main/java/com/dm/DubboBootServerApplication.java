package com.dm;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:DubboBootServerApplication
 * @Description:TODO
 * @date 2019/12/5
 */
@EnableDubbo
@SpringBootApplication
public class DubboBootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboBootServerApplication.class, args);
        System.out.println("服务已开启");
    }
}
