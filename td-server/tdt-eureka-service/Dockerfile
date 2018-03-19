FROM hub.witcloud.huawei.com/official/tomcat:7-jre8
COPY target/tdt-eureka-service-0.1.jar /usr/local/
COPY startService.sh /opt
ENV TZ Asia/Shanghai

EXPOSE 8000

CMD ["sh","/opt/startService.sh"]
