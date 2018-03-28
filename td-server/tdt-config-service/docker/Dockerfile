FROM hub.witcloud.huawei.com/official/tomcat:7-jre8
COPY tdt-config-service-0.1.jar /usr/local
COPY startService.sh /opt
ENV TZ Asia/Shanghai

EXPOSE 8030

CMD ["sh","/opt/startService.sh"]
