package com.dm.framework.protocol.dubbo;

import com.dm.framework.entity.Invocation;
import com.dm.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * Netty 服务端处理器
 *
 * @author dm
 * @date 2023/12/15
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务端处理读事件
     *
     * @param ctx 上下文
     * @param msg 消息体
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 从请求中获取到Invocation对象
        Invocation invocation = (Invocation) msg;

        // 通过接口找到类 在通过类调用方法
        Class implClass = LocalRegister.get(invocation.getInterfaceName());
        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object result = method.invoke(implClass.newInstance(), invocation.getParams());

        System.out.println("Netty-------------" + result.toString());
        ctx.writeAndFlush("Netty:" + result);
    }
}
