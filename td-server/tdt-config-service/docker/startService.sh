#!/bin/sh
mkdir -p /usr/local/tdt/config/log

java -jar /usr/local/tdt-config-service-0.1.jar > /usr/local/tdt/config/log/tdt-config-service.log
