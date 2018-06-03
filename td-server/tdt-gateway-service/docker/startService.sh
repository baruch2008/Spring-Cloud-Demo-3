#!/bin/sh
mkdir -p /usr/local/tdt/gateway/log

java -jar /usr/local/tdt-gateway-service-0.1.jar > /usr/local/tdt/gateway/log/tdt-gateway-service.log
