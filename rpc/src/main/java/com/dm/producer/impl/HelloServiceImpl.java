package com.dm.producer.impl;

import com.dm.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String userName) {
        return "Hello: " + userName;
    }
}
