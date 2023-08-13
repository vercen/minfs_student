#!/bin/env bash
# Start the application
# This script is called by the Dockerfile
#一键启动脚本
#启动metaserver 日志输出到log 添加zookeeper -Dzookeeper.addr=10.243.137.17
java -Dzookeeper.addr=10.243.137.17 -jar ../metaServer/metaServer-1.0.jar > "../metaServer/log" 2>&1 &
#启动从metaserver指定8001端口--server.port=8001  -Dzookeeper.addr=10.243.137.17

java -Dzookeeper.addr=10.243.137.17 -jar ../metaServer/metaServer-1.0.jar --server.port=8001 > "../metaServer/log1" 2>&1 &
#启动dataserver 9000 -9003
java -Dzookeeper.addr=10.243.137.17 -jar ../dataServer/dataServer-1.0.jar --server.port=9000 > "../dataServer/log" 2>&1 &
java -Dzookeeper.addr=10.243.137.17 -jar ../dataServer/dataServer-1.0.jar --server.port=9001 > "../dataServer/log1" 2>&1 &
java -Dzookeeper.addr=10.243.137.17 -jar ../dataServer/dataServer-1.0.jar --server.port=9002 > "../dataServer/log2" 2>&1 &
java -Dzookeeper.addr=10.243.137.17 -jar ../dataServer/dataServer-1.0.jar --server.port=9003 > "../dataServer/log3" 2>&1 &
