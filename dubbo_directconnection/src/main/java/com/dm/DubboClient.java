package com.dm;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:DubboClient
 * @Description:dubbo客户端
 * @date 2019/12/5
 */
public class DubboClient {

    public static void main(String[] args) throws IOException {

        // application
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dm-dubbo");
        applicationConfig.setQosEnable(false);
        // register
//        RegistryConfig registryConfig = new RegistryConfig("redis://122.51.157.42:6379");
//        registryConfig.setPassword("7028858@dm");
//        registryConfig.setUsername("dm");

        RegistryConfig registryConfig = new RegistryConfig("zookeeper://122.51.157.42:2181");

        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
        configCenterConfig.setTimeout(Long.parseLong("50000"));

        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
//        referenceConfig.setUrl("dubbo://192.164.47.100:20880/com.dm.UserService");
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setConfigCenter(configCenterConfig);
        UserService userService = (UserService)referenceConfig.get();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (bufferedReader.readLine().equals("quit")){
                break;
            }
            System.out.println(userService.getUser(1));
        }

    }

}
