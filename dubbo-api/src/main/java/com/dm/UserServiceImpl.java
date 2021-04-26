//package com.dm;
//
//import java.lang.management.ManagementFactory;
//
//public class UserServiceImpl implements UserService {
//
//    @Override
//    public User getUser(Integer id) {
//        User user = new User();
//        user.setId(id);
//        user.setName("dm:" + ManagementFactory.getRuntimeMXBean().getName());
//        user.setSex("man");
//        return user;
//    }
//}
