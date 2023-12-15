package com.dm.framework.register;

import com.alibaba.fastjson.JSONObject;
import com.dm.framework.entity.URL;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ZookeeperRegister {

    /**
     * curator 客户端
     */
    private static CuratorFramework curatorFramework;

    /**
     * Dubbo服务根目录
     */
    private static final String DUBBO_SERVICE_PREFIX = "/dubbo/service/";


    /**
     * 服务列表
     */
    private static Map<String, List<URL>> SERVICE_LIST = new HashMap<>(8);

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(ZookeeperConstants.BASE_SLEEP_TIME_MS, ZookeeperConstants.MAX_RETRIES);
        curatorFramework = CuratorFrameworkFactory.builder().connectString(ZookeeperConstants.SERVER)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(ZookeeperConstants.SESSION_TIME_OUT)
                .connectionTimeoutMs(ZookeeperConstants.CONNECTION_TIME_OUT)
                .canBeReadOnly(true)
                .build();

        curatorFramework.getConnectionStateListenable().addListener((client, newState) -> {
            if (newState == ConnectionState.CONNECTED) {
                log.info("zookeeper连接成功！");
            }
        });
        curatorFramework.start();
    }

    /**
     * 注册服务接口
     *
     * @param interfaceName 接口名称
     */
    public void register(String interfaceName) {
        try {
            // 看是不是有父节点 没有创建容器节点
            createIfNeed(CreateMode.CONTAINER, DUBBO_SERVICE_PREFIX + interfaceName);
            // 创建子节点 临时节点
            URL url = new URL();

            createIfNeed(CreateMode.EPHEMERAL, DUBBO_SERVICE_PREFIX + interfaceName + "/" + JSONObject.toJSONString(url));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取服务列表
     *
     * @param interfaceName 接口名称
     * @return {@link List}<{@link URL}> 服务列表
     */
    public List<URL> getUrlList(String interfaceName) {
        try {
            // 从缓存里面取
            if (SERVICE_LIST.containsKey(interfaceName)) {
                return SERVICE_LIST.getOrDefault(interfaceName, Collections.emptyList());
            }
            // 缓存里面没有就从zk上面拿 TODO 加个监听 节点变化刷新缓存
            List<String> result = curatorFramework.getChildren().forPath(DUBBO_SERVICE_PREFIX + interfaceName);

            // 存入缓存
            List<URL> urlList = result.stream().map(s -> JSONObject.parseObject(s, URL.class)).collect(Collectors.toList());
            SERVICE_LIST.put(interfaceName, urlList);
            return urlList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * 有就不创建 没有就创建
     *
     * @param model 节点类型
     * @param path  节点路径
     */
    public void createIfNeed(CreateMode model, String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat == null) {
            String s = curatorFramework.create().withMode(model).forPath(path);
            log.info("path {} created! ", s);
        }
    }

}
