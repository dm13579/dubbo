package com.dm.framework;

import com.dm.framework.protocol.Protocol;
import com.dm.framework.protocol.dubbo.DubboProtocol;
import com.dm.framework.protocol.http.HttpProtocol;
import io.netty.util.internal.StringUtil;

/**
 * 协议工厂
 *
 * @author dm
 * @date 2023/12/14
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        // 获取配置启动协议类
        String name = StringUtil.isNullOrEmpty(System.getProperty("protocolName")) ? "dubbo" : System.getProperty("protocolName");

        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                return new DubboProtocol();
        }
    }
}
