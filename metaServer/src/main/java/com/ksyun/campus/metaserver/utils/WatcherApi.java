//package com.ksyun.campus.metaserver.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//
///**
// * @author vercen
// * @version 1.0
// */
//@Slf4j
//public class WatcherApi implements Watcher {
//    @Override
//    public void process(WatchedEvent watchedEvent) {
//        log.info("【Watcher监听事件】={}", watchedEvent.getState());
//        log.info("【监听路径为】={}", watchedEvent.getPath());
//        log.info("【监听的类型为】={}", watchedEvent.getType()); //  三种监听类型： 创建，删除，更新
//    }
//}
