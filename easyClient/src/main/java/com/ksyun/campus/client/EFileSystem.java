package com.ksyun.campus.client;

import com.ksyun.campus.client.domain.ClusterInfo;
import com.ksyun.campus.client.domain.StatInfo;
import org.apache.hc.client5.http.classic.HttpClient;

import java.util.List;

public class EFileSystem extends FileSystem {
    private HttpClient httpClient;

    private String fileName = "default";

    public EFileSystem() {
    }

    public EFileSystem(String fileName) {
        this.fileName = fileName;
    }

    public FSInputStream open(String path) {
        return new FSInputStream();
    }

    public FSOutputStream create(String path) {
        try {
            callRemote(path, "create");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new FSOutputStream();
    }

    public boolean mkdir(String path) {
        //构建请求
//        new HttpPost()
//        //发送请求
//        httpClient.execute(, )
        return true;
    }

    public boolean delete(String path) {

        return true;
    }

    public StatInfo getFileStats(String path) {
        StatInfo statInfo = new StatInfo();
        statInfo.setPath(path);
        return statInfo;
    }

    public List<StatInfo> listFileStats(String path) {
        return null;
    }

    public ClusterInfo getClusterInfo() {

        ClusterInfo clusterInfo = new ClusterInfo();
        return clusterInfo;
    }
}