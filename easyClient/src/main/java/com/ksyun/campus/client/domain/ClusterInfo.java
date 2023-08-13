package com.ksyun.campus.client.domain;

import java.util.List;

public class ClusterInfo {
    private MetaServerMsg masterMetaServer;
    private MetaServerMsg slaveMetaServer;
    private List<DataServerMsg> dataServer;

    public MetaServerMsg getMasterMetaServer() {
        return masterMetaServer;
    }

    public void setMasterMetaServer(MetaServerMsg masterMetaServer) {
        this.masterMetaServer = masterMetaServer;
    }

    public MetaServerMsg getSlaveMetaServer() {
        return slaveMetaServer;
    }

    public void setSlaveMetaServer(MetaServerMsg slaveMetaServer) {
        this.slaveMetaServer = slaveMetaServer;
    }

    public List<DataServerMsg> getDataServer() {
        return dataServer;
    }

    public void setDataServer(List<DataServerMsg> dataServer) {
        this.dataServer = dataServer;
    }

    public class MetaServerMsg{
        private String host;
        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        @Override
        public String toString() {
            return "MetaServerMsg{" +
                    "host='" + host + '\'' +
                    ", port=" + port +
                    '}';
        }
    }
    public class DataServerMsg{
        private String host;
        private int port;
        //文件总数
        private int fileTotal;
        //容量
        private int capacity;
        //已使用容量
        private int useCapacity;

        public DataServerMsg() {
        }
        public DataServerMsg(String host, int port, int fileTotal, int capacity, int useCapacity) {
            this.host = host;
            this.port = port;
            this.fileTotal = fileTotal;
            this.capacity = capacity;
            this.useCapacity = useCapacity;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getFileTotal() {
            return fileTotal;
        }

        public void setFileTotal(int fileTotal) {
            this.fileTotal = fileTotal;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getUseCapacity() {
            return useCapacity;
        }

        public void setUseCapacity(int useCapacity) {
            this.useCapacity = useCapacity;
        }

        @Override
        public String toString() {
            return "DataServerMsg{" +
                    "host='" + host + '\'' +
                    ", port=" + port +
                    ", fileTotal=" + fileTotal +
                    ", capacity=" + capacity +
                    ", useCapacity=" + useCapacity +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ClusterInfo{" +
                "masterMetaServer=" + masterMetaServer +
                ", slaveMetaServer=" + slaveMetaServer +
                ", dataServer=" + dataServer +
                '}';
    }
}
