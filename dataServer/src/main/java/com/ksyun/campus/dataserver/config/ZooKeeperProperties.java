package com.ksyun.campus.dataserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author vercen
 * @version 1.0
 */

@Configuration
// 配置文件信息前缀
@Data
public class ZooKeeperProperties {
    @Value("${zookeeper.addr}")
    private String addr;
    @Value("${zookeeper.namespace}")
    private String namespace;
    @Value("${zookeeper.sessionTimeout}")
    private int sessionTimeoutMs=60000;
    @Value("${zookeeper.connectionTimeoutMs}")
    private int connectionTimeoutMs=15000;
    @Value("${zookeeper.baseSleepTimeMs}")
    private int baseSleepTimeMs=1000;
    @Value("${zookeeper.maxRetries}")
    private int maxRetries=3;
}
