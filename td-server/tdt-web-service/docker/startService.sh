#!/bin/sh
mkdir -p /usr/local/tdt/web/log

java -jar /usr/local/tdt-web-service-0.1.jar > /usr/local/tdt/web/log/tdt-web-service.log
