package com.dm.framework.protocol;

import com.dm.framework.entity.Invocation;
import com.dm.framework.entity.URL;

/**
 * 协议接口
 *
 * @author dm
 * @date 2023/12/14
 */
public interface Protocol {

    /**
     * 启动
     *
     * @param url 请求地址
     */
    void start(URL url);

    /**
     * 发送数据包
     *
     * @param url        请求地址
     * @param invocation 接口信息
     * @return {@link String}
     */
    String send(URL url, Invocation invocation);
}
