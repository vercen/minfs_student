#!/bin/env bash
# Start the application
# This script is called by the Dockerfile
#一键启动脚本
#启动metaserver
java -jar ../metaserver/target/metaServer-1.0.jar &
#启动从metaserver
java -jar ../metaserver/target/metaServer-1.0.jar &