package com.dm.framework.register;


/**
 * zookeeper 常量类
 *
 * @author dm
 * @date 2023/12/15
 */
public class ZookeeperConstants {

    /**
     * 是否启用zk
     */
    public final static Boolean enable = true;

    /**
     * zk服务端地址
     */
    public final static String SERVER = "*:2181";

    public final static String CLUSTER_SERVER = "*:2181,*:2182,*:2183";
    /**
     * zk session 超时时间
     */
    public final static Integer SESSION_TIME_OUT = 50000;

    /**
     * zk session 连接时间
     */
    public final static Integer CONNECTION_TIME_OUT = 10000;

    /**
     * 重试等待时间
     */
    public final static Integer BASE_SLEEP_TIME_MS = 1000;

    /**
     * 最大重试次数
     */
    public final static Integer MAX_RETRIES = 3;



}
