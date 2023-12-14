package com.dm.producer;

import com.dm.api.HelloService;
import com.dm.framework.ProtocolFactory;
import com.dm.framework.entity.URL;
import com.dm.framework.register.LocalRegister;
import com.dm.producer.impl.HelloServiceImpl;

public class Provider {
    public static void main(String[] args) {
        // 本地注册，注册服务
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        // TODO 启动一次注册zk一次


        // 协议工厂获取配置启动协议
        ProtocolFactory.getProtocol().start(new URL("localhost",8080));
    }
}
