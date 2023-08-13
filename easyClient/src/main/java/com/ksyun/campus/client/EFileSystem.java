package com.ksyun.campus.client;

import com.ksyun.campus.client.domain.ClusterInfo;
import com.ksyun.campus.client.domain.StatInfo;
import org.apache.hc.client5.http.classic.HttpClient;

import java.util.ArrayList;
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
        ClusterInfo.MetaServerMsg masterMetaServer = clusterInfo.new MetaServerMsg();
        masterMetaServer.setHost("127.0.0.1");
        masterMetaServer.setPort(8000);
        clusterInfo.setMasterMetaServer(masterMetaServer);
        ClusterInfo.MetaServerMsg slaveMetaServer = clusterInfo.new MetaServerMsg();
        slaveMetaServer.setHost("127.0.0.1");
        slaveMetaServer.setPort(8001);
        clusterInfo.setSlaveMetaServer(slaveMetaServer);
        ClusterInfo.DataServerMsg dataServerMsg1 = clusterInfo.new DataServerMsg("127.0.0.1", 9000, 10, 1000, 100);
        ClusterInfo.DataServerMsg dataServerMsg2 = clusterInfo.new DataServerMsg("127.0.0.1", 9001, 10, 1000, 100);
        ClusterInfo.DataServerMsg dataServerMsg3 = clusterInfo.new DataServerMsg("127.0.0.1", 9002, 10, 1000, 100);
        ClusterInfo.DataServerMsg dataServerMsg4 = clusterInfo.new DataServerMsg("127.0.0.1", 9003, 10, 1000, 100);
        ArrayList<ClusterInfo.DataServerMsg> dataServerMsgs = new ArrayList<>();
        dataServerMsgs.add(dataServerMsg1);
        dataServerMsgs.add(dataServerMsg2);
        dataServerMsgs.add(dataServerMsg3);
        dataServerMsgs.add(dataServerMsg4);
        clusterInfo.setDataServer(dataServerMsgs);
        return clusterInfo;
    }
}