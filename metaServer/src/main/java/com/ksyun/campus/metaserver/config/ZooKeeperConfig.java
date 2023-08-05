package com.ksyun.campus.metaserver.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author vercen
 * @version 1.0
 */
@Configuration
@Slf4j
public class ZooKeeperConfig {
    @Autowired
    private ZooKeeperProperties zooKeeperProperties;

    @Bean
    public CuratorFramework curatorFramework(){

        String connectString = zooKeeperProperties.getAddr();
        System.out.println("zookeeper addr: " + connectString);
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(connectString);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zooKeeperProperties.getBaseSleepTimeMs(), zooKeeperProperties.getMaxRetries());
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(zooKeeperProperties.getSessionTimeoutMs())
                .connectionTimeoutMs(zooKeeperProperties.getConnectionTimeoutMs())
                .namespace(zooKeeperProperties.getNamespace())
                .retryPolicy(retryPolicy)
                .build();

        curatorFramework.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                if(newState == ConnectionState.CONNECTED){
                    log.info("zookeeper 连接成功");
                }
            }
        });

        curatorFramework.start();
        return curatorFramework;
    }

}
