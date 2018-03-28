#!/bin/sh
mkdir -p /usr/local/tdt/eureka/log

java -jar /usr/local/tdt-eureka-service-0.1.jar > /usr/local/tdt/eureka/log/tdt-eureka-service.log
