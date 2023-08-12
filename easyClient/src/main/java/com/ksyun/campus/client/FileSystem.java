package com.ksyun.campus.client;

import com.ksyun.campus.client.domain.ClusterInfo;
import com.ksyun.campus.client.util.ZkUtil;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public abstract class FileSystem {
    private String fileSystem;
    private static HttpClient httpClient;

    protected void callRemote(String path,String contorller) throws Exception {
        //构建请求
        ZkUtil zkUtil = new ZkUtil();
        ClusterInfo clusterInfo = zkUtil.getClusterInfo();
        String host = clusterInfo.getMasterMetaServer().getHost();
        int port = clusterInfo.getMasterMetaServer().getPort();
        //发送请求
        //构造请求体
        HttpPost httpPost = new HttpPost("http://" + host + ":" + port + "/" + contorller);
        httpPost.setEntity(new StringEntity(path, ContentType.APPLICATION_JSON));
        //发送请求
        HttpResponse execute = httpClient.execute(httpPost);
//解析响应



//        httpClient.execute();
    }

}
