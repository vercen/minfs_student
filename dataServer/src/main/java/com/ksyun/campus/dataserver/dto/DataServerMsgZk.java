package com.ksyun.campus.dataserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vercen
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataServerMsgZk {
    // todo 将本实例信息注册至zk中心，包含信息 ip、port、capacity、rack、zone
    private String ip;
    private int port;
    private int capacity;
    private String rack;
    private String zone;
}
