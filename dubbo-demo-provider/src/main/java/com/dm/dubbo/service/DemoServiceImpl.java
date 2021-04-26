package com.dm.dubbo.service;

import com.dm.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * ,;,,;
 * ,;;'(
 * __      ,;;' ' \
 * /'  '\'~~'~' \ /'\.)
 * ,;(      )    /  |.
 * ,;' \    /-.,,(   ) \
 * ) /       ) / )|
 * ||        ||  \)
 * (_\       (_\
 *
 * @author dm
 * @version 1.0
 * @className DemoServiceImpl
 * @cescription TODO
 * @date 2021/4/25 16:29
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
@Service(version = "default")
@Slf4j
public class DemoServiceImpl implements DemoService {
    @Override
    public String called(String name) {
        log.info("执行了服务{}", name);
        URL url = RpcContext.getContext().getUrl();
        return url.getProtocol() + ":" + url.getPort() + ",Hello," + name;
    }
}