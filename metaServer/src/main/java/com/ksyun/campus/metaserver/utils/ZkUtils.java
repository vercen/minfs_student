package com.ksyun.campus.metaserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vercen
 * @version 1.0
 */
@Component
@Slf4j
public class ZkUtils {
    @Autowired
    private CuratorFramework zkClient;

    /**
     * 判断指定节点是否存在
     *
     * @param path
     * @param needWatch 指定是否复用zookeeper中默认的Watcher
     * @return
     */
    public boolean exists(String path, boolean needWatch) {
        try {
            zkClient.getData().forPath(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 检测结点是否存在 并设置监听事件
     * 三种监听类型： 创建，删除，更新
     *
     * @param path
     * @param watcher 传入指定的监听类
     * @return
     */


    /**
     * 创建持久化节点
     *
     * @param path
     * @param data
     */
    //设置持久化节点
    public void createPersistentNode(String path, String data) {
        try {
            zkClient.create().creatingParentsIfNeeded().forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 创建持久化节点
     *
     * @param path
     */
    public void createPersistentNode(String path) {
        try {
            zkClient.create().creatingParentsIfNeeded().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改持久化节点
     *
     * @param path
     * @param data
     */
    //修改持久化节点
    public void updatePersistentNode(String path, String data) {
        try {
            zkClient.setData().forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除持久化节点
     *
     * @param path
     */
    //删除持久化节点
    public Boolean deletePersistentNode(String path) {
        try {
            zkClient.delete().forPath(path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取当前节点的子节点(不包含孙子节点)
     *
     * @param path 父节点path
     */
    //获取当前节点的子节点(不包含孙子节点)
    public void getChildren(String path) {
        try {
            zkClient.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定节点的值
     *
     * @param path
     * @return
     */
    //获取指定节点的值
    public String getData(String path) {
        try {
            byte[] bytes = zkClient.getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取指定节点的值
     *
     * @param path
     * @return
     */


    /**
     * 创建临时节点
     *
     * @param path
     * @param data
     * @return
     */
    //创建临时节点
    public String createEphemeralNode(String path, String data) {
        try {
            return zkClient.create().creatingParentsIfNeeded().forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 创建临时节点
     *
     * @param path
     * @return
     */


}
