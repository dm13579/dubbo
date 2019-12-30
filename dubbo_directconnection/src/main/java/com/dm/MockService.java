package com.dm;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version V1.0
 * @ClassName:MockService
 * @Description:TODO
 * @date 2019/12/12
 */
public class MockService implements GenericService{

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {

        if(method.equals("getUser")){
//            User user = new User();
//            user.setName("我是mock啊。");
//            return user;

            Map<String,String> map = new HashMap<>();
            map.put("id","1");
            map.put("name","mock");
            return map;
        }

        return null;
    }
}
