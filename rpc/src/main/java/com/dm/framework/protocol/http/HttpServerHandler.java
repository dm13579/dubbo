package com.dm.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.dm.framework.entity.Invocation;
import com.dm.framework.register.LocalRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * http请求处理器
 *
 * @author dm
 * @date 2023/12/14
 */
@Slf4j
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 从请求中获取到Invocation对象
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();

            // 通过接口找到类 在通过类调用方法
            Class implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(implClass, invocation.getParams());

            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
    }
}
