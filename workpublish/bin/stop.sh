#!/bin/bash
#一键停止脚本

#停止metaserver
pkill -f "metaServer-1.0.jar"
#停止dataserver
pkill -f "dataServer-1.0.jar"
