#!/bin/sh
mkdir -p /usr/local/tdt/testplan/log

java -jar /usr/local/tdt-testplan-service-0.1.jar > /usr/local/tdt/testplan/log/tdt-testplan-service.log
