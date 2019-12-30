package com.dm;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("dm:" + ManagementFactory.getRuntimeMXBean().getName());
        user.setSex("man");
        return user;
    }
}
