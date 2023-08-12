package com.ksyun.campus.dataserver.services;


import com.ksyun.campus.dataserver.dto.DataServerMsgZk;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

@Component
public class RegistService implements ApplicationRunner {
    @Autowired
    private CuratorFramework zkClient;

    private String ip;
    @Value("${server.port}")
    private int port;
    private int capacity=100;
    private String rack="rack1";
    private String zone="zone1";

    private DataServerMsgZk dataServerMsgZk;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ip= InetAddress.getLocalHost().getHostAddress();
        dataServerMsgZk = new DataServerMsgZk(ip, port, capacity, rack, zone);
        registToCenter();
    }

    public void registToCenter() throws Exception {
        // todo 将本实例信息注册至zk中心，包含信息 ip、port、capacity、rack、zone
        //注册临时有序节点，dataServer有3个
        zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/dataServer/node", dataServerMsgZk.toString().getBytes());
    }

    public List<Map<String, Integer>> getDslist() {
        return null;
    }


}

