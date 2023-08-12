package com.ksyun.campus.metaserver.domain;

import java.util.List;

//function: 用于存储文件信息
public class StatInfo
{
    //文件路径
    public String path;
    //文件大小
    public long size;
    //文件修改时间
    public long mtime;
    //文件类型
    public FileType type;
    //文件副本信息
    private List<ReplicaData> replicaData;
    public StatInfo() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

}
