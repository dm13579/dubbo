package com.dm;

import org.apache.dubbo.config.*;

import java.io.IOException;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:DubboServer
 * @Description: 服务暴露
 * @date 2019/12/5
 */
public class DubboServer {

    public static void main(String[] args) throws IOException {

        // application
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dm-dubbo");
        applicationConfig.setQosEnable(false);

        // protocol
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(-1);
        protocolConfig.setThreads(20);

        // register
//        RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);
//        RegistryConfig registryConfig = new RegistryConfig("redis://122.51.157.42:6379");
//        registryConfig.setPassword("7028858@dm");
//        registryConfig.setUsername("dm");

        RegistryConfig registryConfig = new RegistryConfig("zookeeper://122.51.157.42:2181");
        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
        configCenterConfig.setTimeout(Long.parseLong("50000"));

        // service
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);
//        serviceConfig.setRef(new UserServiceImpl());

        // 设值mock
        setMock(serviceConfig);

        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setConfigCenter(configCenterConfig);
        serviceConfig.export();

        System.out.println("服务已暴露");
        System.in.read();
    }

    private static void setMock(ServiceConfig serviceConfig){
        serviceConfig.setRef(new MockService());
    }

}
