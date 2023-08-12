package com.ksyun.campus.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author vercen
 * @version 1.0
 */
public class CuratorTest {
    private CuratorFramework Client;
    @BeforeEach
    public void testCurator() {
        /*
        参数
        connectString：服务器列表，格式host1:port1,host2:port2,...
        sessionTimeoutMs：会话超时时间，单位毫秒，默认60000ms
        connectionTimeoutMs：连接创建超时时间，单位毫秒，默认60000ms
        retryPolicy：重试策略，内建有四种重试策略
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("10.243.137.17:2181", retryPolicy);
//        curatorFramework.start();
        /*第二种*/
        Client = CuratorFrameworkFactory.builder().connectString("10.243.137.17:2181").retryPolicy(retryPolicy).namespace("minFS").build();
        Client.start();

        System.out.println("test");
    }
    //创建节点
    @Test
    public void testCreate() throws Exception {
        //String path = build.create().forPath("/test", "test".getBytes());
        //不填写内容，默认为数据为ip地址
        //创建永久节点
        //String path = build.create().forPath("/test2");
        //创建临时节点
        String path2 = Client.create().withMode(CreateMode.EPHEMERAL).forPath("/test3");
        System.out.println(path2);
        //会话结束，临时节点消失

        //创建多级节点永久
        String path3 = Client.create().creatingParentsIfNeeded().forPath("/test4/test5");
        //创建多级节点临时
        String path4 = Client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test5/test6");

    }
    //获取节点数据
    @Test
    public void testGet() throws Exception {
        byte[] bytes = Client.getData().forPath("/test");
        System.out.println(new String(bytes));

    }
    //查询子节点
    @Test
    public void testGet2(){
        try {
            List<String> strings = Client.getChildren().forPath("/");
            System.out.println(strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询节点状态
    @Test
    public void testGet3(){
        try {
            Stat stat = Client.checkExists().forPath("/test");
            System.out.println(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改节点数据
    @Test
    public void testUpdate() throws Exception {
        Client.setData().forPath("/test","test2".getBytes());
    }
    //修改节点数据根据版本号
    @Test
    public void testUpdate2() throws Exception {
        Stat stat = new Stat();
        Client.getData().storingStatIn(stat).forPath("/test");
        System.out.println(stat.getVersion());
        Client.setData().withVersion(stat.getVersion()).forPath("/test","test3".getBytes());
    }

    //监听节点
    @Test
    public void testWatch() throws Exception {
        //创建监听对象
        NodeCache nodeCache = new NodeCache(Client,"/test");
        //启动监听

        //添加监听事件
        nodeCache.getListenable().addListener(()->{
//            if(nodeCache.getCurrentData()!=null){
//                System.out.println("节点数据发生变化");
//            }
            Boolean stat = Client.checkExists().forPath("/test")!=null;
            System.out.println(stat);
        });
        //启动监听
        nodeCache.start(true);
        while (true){

        }
    }

    //监听节点的子节点
    @Test
    public void testWatch2() throws Exception {
        //创建监听对象
//        PathChildrenCache pathChildrenCache = new PathChildrenCache(Client, "/", true);
        //添加监听事件
        PathChildrenCache pathChildrenCache = new PathChildrenCache(Client, "/metaServer", true);
        //添加监听事件
        //监听器监听是否有主节点/metaServer
        pathChildrenCache.getListenable().addListener((client, event)->{
            //获取/metaServer下所有子节点,这些子节点就是metaServer信息
            //对节点列表排序,获取最小序号的节点,该节点就是当前的主节点
            //获取所有子节点
            List<ChildData> currentData = pathChildrenCache.getCurrentData();
            //System.out.println("------"+currentData);
            //拿到第一个节点的data
            ChildData childData = currentData.get(0);
            byte[] data = childData.getData();
            System.out.println("主节点"+new String(data));



            //System.out.println("主节点"+currentData.get(0).getData());

        });
        //启动监听器
        //pathChildrenCache.start();
        //启动监听点[B@3fc66071
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
        while (true){

        }
    }


    //关闭连接
    @AfterEach
    public void close(){
        if (Client!=null){
            Client.close();
        }
    }

}
