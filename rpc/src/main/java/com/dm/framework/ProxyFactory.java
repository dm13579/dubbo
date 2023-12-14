package com.dm.framework;

import com.dm.framework.entity.Invocation;
import com.dm.framework.entity.URL;
import com.dm.api.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 接口代理工厂
 *
 * @author dm
 * @date 2023/12/14
 */
public class ProxyFactory<T> {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = Invocation.builder()
                        .interfaceName(HelloService.class.getName())
                        .methodName(method.getName())
                        .paramTypes(new Class[]{String.class})
                        .params(args)
                        .build();

                // TODO 可以从注册中心获取

                return ProtocolFactory.getProtocol().send(new URL("localhost",8080), invocation);
            }
        });
    }

}
