package com.dm.framework.protocol.http;

import com.dm.framework.entity.Invocation;
import com.dm.framework.protocol.Protocol;
import com.dm.framework.entity.URL;

public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(),invocation);
    }
}
