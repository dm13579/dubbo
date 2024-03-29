package com.dm.framework.protocol.dubbo;

import com.dm.framework.entity.Invocation;
import com.dm.framework.protocol.Protocol;
import com.dm.framework.entity.URL;

public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
