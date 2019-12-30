package com.dm.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:SpringServer
 * @Description:TODO
 * @date 2019/12/5
 */
public class SpringServer {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("provider.xml");
        System.out.println("服务暴露");
        System.in.read();
    }
}
