package com.dm.dubbo;

import com.dm.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:SpringClient
 * @Description:TODO
 * @date 2019/12/5
 */
public class SpringClient {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        UserService userService = context.getBean(UserService.class);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (bufferedReader.readLine().equals("quit")){
                break;
            }
            System.out.println(userService.getUser(1));
        }
    }
}
