#!/bin/sh
mkdir -p /usr/local/tdt/authenticate/log

/usr/local/jdk1.8.0_162/bin/java -jar /usr/local/tdt-authenticate-service-0.1.jar > /usr/local/tdt/authenticate/log/tdt-authenticate-service.log
