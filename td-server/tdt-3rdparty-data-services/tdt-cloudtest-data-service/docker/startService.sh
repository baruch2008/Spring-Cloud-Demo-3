#!/bin/sh
mkdir -p /usr/local/tdt/authenticate/log

java -jar /usr/local/tdt-authenticate-service-0.1.jar > /usr/local/tdt/authenticate/log/tdt-authenticate-service.log
