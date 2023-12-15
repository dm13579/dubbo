package com.dm.framework.protocol.dubbo;

import com.dm.framework.entity.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Netty 客户端
 *
 * @author dm
 * @date 2023/12/15
 */
@Slf4j
public class NettyClient<T> {

    public NettyClientHandler client = null;
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(8));

    /**
     * 启动客户端
     *
     * @param hostName 地址
     * @param port     端口
     */
    public void start(String hostName, Integer port) {
        try {
            client = new NettyClientHandler();

            Bootstrap b = new Bootstrap();
            EventLoopGroup group = new NioEventLoopGroup();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("handler", client);
                        }
                    });
            b.connect(hostName, port).sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 发送数据包
     *
     * @param hostName   主机
     * @param port       端口
     * @param invocation 数据包
     * @return {@link String}
     */
    public String send(String hostName, Integer port, Invocation invocation) {
        if (client == null) {
            start(hostName, port);
        }

        client.setInvocation(invocation);
        try {
            return (String) threadPoolExecutor.submit(client).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


}
