package com.dm.consumer;

import com.dm.framework.ProxyFactory;
import com.dm.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
        HelloService proxy = ProxyFactory.getProxy(HelloService.class);
        String result = proxy.sayHello("daadada");
        System.out.println(result);
    }
}
