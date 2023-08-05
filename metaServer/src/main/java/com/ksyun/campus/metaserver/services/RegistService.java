package com.ksyun.campus.metaserver.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetAddress;

@Component
@Slf4j
public class RegistService implements ApplicationRunner {

    private PathChildrenCache pathChildrenCache;

    @Autowired
    private CuratorFramework zkClient;

    private String ip;
    @Value("${server.port}")
    private int port;
    public void registToCenter(){
        // 将本实例信息注册至zk中心，包含信息 ip、port,临时节点
        //尝试将自己注册为主节点
        try {
            zkClient.create().withMode(CreateMode.EPHEMERAL).forPath("/metaServer", (ip + ":" + port).getBytes());
            log.info("{} 注册成功",ip+":"+port);
        } catch (Exception e) {
            log.info("{} 注册失败",ip+":"+port);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ip = InetAddress.getLocalHost().getHostAddress();
        //registToCenter();
        //创建监听器,监听子节点的变化
        pathChildrenCache = new PathChildrenCache(zkClient,"/",true);
//        NodeCache nodeCache = new NodeCache(zkClient,"/metaServer");
        //添加监听事件
        //监听器监听是否有主节点/metaServer
        pathChildrenCache.getListenable().addListener((client, event)->{
            //查看是否有/metaserver节点
            Boolean metaserverExists  = zkClient.checkExists().forPath("/metaServer")!= null;
            if(!metaserverExists){
                //如果主节点挂了，就尝试注册自己为主节点
                System.out.println("主节点挂了，尝试注册自己为主节点");
                registToCenter();
            }
        });
        //启动监听器
        pathChildrenCache.start();
    }

    @PreDestroy
    public void closeListener() throws IOException {
        if (pathChildrenCache != null) {
            pathChildrenCache.close();
            System.out.println("监听器关闭");
        }
    }
}
