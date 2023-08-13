//package com.ksyun.campus.client.util;
//
//import com.ksyun.campus.client.domain.ClusterInfo;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//public class ZkUtilTest {
//
//    @Test
//    public void testZkUtil() throws Exception {
//        ZkUtil zkUtil = new ZkUtil();
//        zkUtil.postCons();
//        while (true) {
//            Thread.sleep(1000);
//            ClusterInfo clusterInfo = zkUtil.getClusterInfo();
//            System.out.println(clusterInfo);
//        }
//    }
//}