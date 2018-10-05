#!/bin/sh
mkdir -p /usr/local/tdt/zipkin/log

/usr/local/jdk1.8.0_162/bin/java -jar /usr/local/tdt-zipkin-service-0.1.jar > /usr/local/tdt/zipkin/log/tdt-zipkin-service.log
