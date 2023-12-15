package com.dm.framework.protocol.dubbo;

import com.dm.framework.entity.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * Netty客户端处理器
 *
 * @author dm
 * @date 2023/12/15
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {

    /**
     * 上下文
     */
    private ChannelHandlerContext context;

    /**
     * 消息体
     */
    private Invocation invocation;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 线程回调写数据
     *
     * @return {@link Object} RPC调用返回结果
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(this.invocation);
        wait();
        return result;
    }

    /**
     * 客户端与服务端建立连接成功
     *
     * @param ctx 上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    /**
     * 客户端处理读事件
     *
     * @param ctx 上下文
     * @param msg 消息体
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify();
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }
}
