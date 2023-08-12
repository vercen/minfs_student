package com.ksyun.campus.client.util;

import com.ksyun.campus.client.domain.ClusterInfo;
import lombok.Data;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.framework.recipes.cache.NodeCache;

import javax.annotation.PostConstruct;

@Data
public class ZkUtil {
    private CuratorFramework zkClient;
    private ClusterInfo clusterInfo;

    //构造方法
    public ZkUtil() throws Exception {
        // todo 初始化zkClient

        postCons();
    }

//    @PostConstruct
    public void postCons() throws Exception {
        // todo 初始化，与zk建立连接，注册监听路径，当配置有变化随时更新
        NodeCache nodeCache = new NodeCache(zkClient, "/metaServer/masterserver");
        nodeCache.start();
        //监听主节点的变化，拿到主节点的信息
        nodeCache.getListenable().addListener(() -> {
            byte[] data = nodeCache.getCurrentData().getData();
            String masterInfo = new String(data);
            System.out.println("主节点信息发生变化，新的主节点信息为：" + masterInfo);
            //分割主节点的信息，拿到ip和端口号，赋值给ClusterInfo的MetaServerMsg
            //MetaServerMsg是内部类
            String[] split = masterInfo.split(":");
            clusterInfo = new ClusterInfo();
            ClusterInfo.MetaServerMsg metaServerMsg = clusterInfo.new MetaServerMsg();
            metaServerMsg.setHost(split[0]);
            metaServerMsg.setPort(Integer.parseInt(split[1]));
            clusterInfo.setMasterMetaServer(metaServerMsg);
        });

    }
}
