package com.ksyun.campus.client.util;

import javax.annotation.PostConstruct;

public class ZkUtil {
    @PostConstruct
    public void postCons() throws Exception {
        // todo 初始化，与zk建立连接，注册监听路径，当配置有变化随时更新
        // todo 从zk获取配置，更新到本地

    }
}
