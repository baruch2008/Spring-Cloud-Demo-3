#!/bin/sh
mkdir -p /usr/local/tdt/config/log

/usr/local/jdk1.8.0_162/bin/java -jar /usr/local/tdt-config-service-0.1.jar > /usr/local/tdt/config/log/tdt-config-service.log
