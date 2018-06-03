FROM centos_jdk8
COPY tdt-testplan-service-0.1.jar /usr/local/
COPY startService.sh /opt
ENV TZ Asia/Shanghai

EXPOSE 8000

CMD ["sh","/opt/startService.sh"]
