package com.ksyun.campus.client;

import com.ksyun.campus.client.domain.ClusterInfo;
import com.ksyun.campus.client.domain.StatInfo;

import java.util.List;

/**
 * 这个类是用来实现文件系统的基本操作的
 */

public class EFileSystem extends FileSystem{

    //这个是文件系统的名字
    private String fileName="default";
    //这个是文件系统的根目录
    public EFileSystem() {
    }

    //这个是文件系统的名字
    public EFileSystem(String fileName) {
        this.fileName = fileName;
    }

    //打开文件
    public FSInputStream open(String path){
        //todo 打开文件
        return null;
    }

    public FSOutputStream create(String path){
        //todo 创建文件
        return null;
    }
    //
    public boolean mkdir(String path){
        //todo 创建目录
        return false;
    }
    public boolean delete(String path){
        //todo 删除文件
        return false;
    }
    public StatInfo getFileStats(String path){
        //todo 获取文件的状态
        return null;
    }
    public List<StatInfo> listFileStats(String path){
        //todo 获取文件列表
        return null;
    }
    public ClusterInfo getClusterInfo(){
        //todo 获取集群信息
        return null;
    }
}
