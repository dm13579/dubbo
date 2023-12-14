package com.dm.framework.protocol.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * 启动tomcat服务
 *
 * @author dm
 * @date 2023/12/14
 */
@Slf4j
public class HttpServer {

    public void start(String hostname, Integer port) {
        try {
            Tomcat tomcat = new Tomcat();
            Server server = tomcat.getServer();
            Service service = server.findService("Tomcat");

            Connector connector = new Connector();
            connector.setPort(port);

            Engine engine = new StandardEngine();
            engine.setDefaultHost(hostname);

            Host host = new StandardHost();
            host.setName(hostname);

            String contextPath = "";
            Context context = new StandardContext();
            context.setPath(contextPath);
            context.addLifecycleListener(new Tomcat.FixContextListener());

            host.addChild(context);
            engine.addChild(host);

            service.setContainer(engine);
            service.addConnector(connector);

            tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
            context.addServletMappingDecoded("/*", "dispatcher");

            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            log.error(e.getMessage(), e);
        }
    }
}
