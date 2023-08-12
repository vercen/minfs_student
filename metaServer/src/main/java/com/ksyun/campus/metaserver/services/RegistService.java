package com.ksyun.campus.metaserver.services;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.server.auth.IPAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class RegistService implements ApplicationRunner {
    private String ip;
    @Value("${server.port}")
    private String port;
    @Autowired
    private CuratorFramework zkClient;

    private NodeCache nodeCache;

    public void registToCenter() throws Exception {
        // todo 将本实例信息注册至zk中心，包含信息 ip、port
        //先判断是否有主节点
        if (zkClient.checkExists().forPath("/metaServer/masterserver") == null) {
            System.out.println("初始化主节点不存在，创建主节点");
            //如果没有主节点，那么就创建一个主节点
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/metaServer/masterserver", (ip + ":" + port).getBytes());
        } else {
            System.out.println("初始化主节点已经存在，创建从节点");
            //如果有主节点，那么就创建一个从节点
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/metaServer/slaveserver", (ip + ":" + port).getBytes());
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ip = InetAddress.getLocalHost().getHostAddress();
        registToCenter();
        listenMasterChange();
    }

    //监听器监听主节点的变化
    public void listenMasterChange() throws Exception {
        //监听主节点的变化
        nodeCache = new NodeCache(zkClient, "/metaServer/masterserver");
        nodeCache.start();
        nodeCache.getListenable().addListener(() -> {
            if (nodeCache.getCurrentData() == null) {
                System.out.println("主节点已经不存在，重新选举，自己变成主节点，删除自己的从节点");
                //如果主节点不存在，那么就创建一个主节点
                zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/metaServer/masterserver", (ip + ":" + port).getBytes());
                //删除自己的从节点
                zkClient.delete().forPath("/metaServer/slaveserver");
            }
        });

    }
}
