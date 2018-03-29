keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650

DOCKER_OPTS="--insecure-registry hub.witcloud.huawei.com -H unix:///var/run/docker.sock -H 0.0.0.0:2375"

Docker安装使用指导
1、环境准备
1）Ubuntu 14.04 100.99.50.126

2）配置Docker的apt源
vi /etc/apt/sources.list
将deb http://rnd-mirrors-langfang.huawei.com/docker/apt/repo ubuntu-trusty main添加至文件，然后保存退出

3）拷贝docker-gpg-key到/root目录下
执行如下命令：
gpg --import docker-gpg-key
gpg --fingerprint
gpg --sign-key docker-gpg-key
apt-key add docker-gpg-key

 
4）拷贝archives.tar.gz 到/var/cache/apt目录
 解压archives.tar.gz 到archives目录，如下：
 
 
5）执行apt-get update
2、安装
apt-get install docker-engine
注：默认安装完成，docker服务启动
执行docker –v查看是否安装成功，正常情况下输出如下内容：
root@CTU1000105312:/# docker -v
Docker version 17.05.0-ce, build 89658be
3、配置
1) 先停止docker服务
/etc/init.d/docker stop 或 service docker stop
2) 配置镜像仓库、远程访问
vi /etc/default/docker
将-H unix:///var/run/docker.sock -H 0.0.0.0:2375添加至DOCKER_OPTS配置项中，此配置为支持远程访问
将--insecure-registry hub.witcloud.huawei.com添加至DOCKER_OPTS配置项中，此配置为设定默认镜像仓库
3) 修改Docker默认存储
修改的方式如下几种。
方式一：
在已安装Docker后，要修改存储目录，需先卸载，再删除默认目录/var/lib/docker，然后指定新的目录，最后重新安装
apt-get remove docker-engine –purge
rm –rf /var/lib/docker/ （注：在删除时如果报Device or Resource busy错误，需要先umount /var/lib/docker后，在执行删除命令）
ln –s /docker /var/lib/docker
apt-get install docker-engine

方式二：
在/etc/default/docker中的DOCKER_OPTS配置项中添加-g /docker，/docker为指定目录，重启系统后生效，此方式为解决方式一中在删除/var/lib/docker不能删除，报Device or Resource busy错误时的办法（注：后经确认需要先umount /var/lib/docker）。

方式三：(未验证)
1）备份fstab文件
cp /etc/fstab /etc/fstab.bak
2）停止docker服务，并把原目录下的内容拷贝到新目录下
service docker stop
mkdir -p /data/docker
cp -r /var/lib/docker /data/docker
3）修改fstab，添加如下行，让其挂载到新目录下
echo "/data/docker /var/lib/docker none bind 0 0" >> /etc/fstab
4）重新挂载
mount -a
重新导入镜像，建立容器后，即可看到docker已经挂载到新目录下了
5）重启docker
service docker start

最终配置如下(/etc/default/docker)：
DOCKER_OPTS="-g /docker --insecure-registry hub.witcloud.huawei.com -H unix:///var/run/docker.sock -H 0.0.0.0:2375"
4、Build镜像
1）通过Maven插件docker-maven-plugin实现在开发IDE中实现工程的Docker镜像的Build以及发布。
需要在本地配置一个系统参数，
DOCKER_HOST=tcp://100.99.50.126:2375

2）pom.xml配置
<build>
	<plugins>
		<plugin>
			<groupId>com.spotify</groupId>
			<artifactId>docker-maven-plugin</artifactId>
			<configuration>
			  <imageName>${project.name}:${project.version}</imageName>				  <dockerDirectory>${project.basedir}/docker</dockerDirectory>
			  <skipDockerBuild>false</skipDockerBuild>
			  <resources>
				<resource>
				  <directory>${project.build.directory}</directory>						  <include>${project.build.finalName}.jar</include>
				 </resource>
			   </resources>
			 </configuration>
			</plugin>
		</plugins>
	</build>
      说明：需要在工程根目录下创建docker文件夹，并将Dockerfile和startService.sh放置于此文件中。如果dockerDirectory指定为${project.baseDir}则插件默认会将工程根目录下的所有目录或文件拷贝至target/docker目录中，包括target本身。

注：对于不需要Build的工程也需要配置此插件，只需将skipDockerBuild设置成true，如下：
<plugin>
	<groupId>com.spotify</groupId>
	<artifactId>docker-maven-plugin</artifactId>
	<configuration>
		<skipDockerBuild>true</skipDockerBuild>
	</configuration>
</plugin>
3）Dockerfile编写
FROM hub.witcloud.huawei.com/official/tomcat:7-jre8
COPY tdt-testplan-service-0.1.jar /usr/local/
COPY startService.sh /opt
ENV TZ Asia/Shanghai

EXPOSE 8050
CMD ["sh","/opt/startService.sh"]

4）启动文件startService.sh
#!/bin/sh
mkdir -p /usr/local/tdt/testplan/log

java -jar /usr/local/tdt-testplan-service-0.1.jar --spring.profiles.active=pro > /usr/local/tdt/testplan/log/tdt-testplan-service.log
		注意：此Shell文件必须确保是UNIX格式，否则会出问题。
5、操作
1）停止docker
docker stop tdt-project-service-8040
docker stop tdt-gateway-service-9000
docker stop tdt-testplan-service-8050
docker stop tdt-synchronize-service-8090
docker stop tdt-web-service-8020
docker stop tdt-authenticate-service-8010
docker stop tdt-cloudtest-data-service-8070
docker stop tdt-config-service-8030
docker stop tdt-eureka-service-8000
2）删除docker
docker rm tdt-project-service-8040
docker rm tdt-synchronize-service-8090
docker rm tdt-web-service-8020
docker rm tdt-authenticate-service-8010
docker rm tdt-cloudtest-data-service-8070
docker rm tdt-config-service-8030
docker rm tdt-eureka-service-8000
docker rm tdt-gateway-service-9000
docker rm tdt-testplan-service-8050
3）删除镜像
docker rmi tdt-synchronize-service:0.1
docker rmi tdt-web-service:0.1
docker rmi tdt-testplan-service:0.1
docker rmi tdt-project-service:0.1
docker rmi tdt-cloudtest-data-service:0.1
docker rmi tdt-gateway-service:0.1
docker rmi tdt-config-service:0.1
docker rmi tdt-authenticate-service:0.1
docker rmi tdt-eureka-service:0.1
4）创建容器并运行
 
说明：-v参数可以将容器中的指定目录挂载至宿主机对应目录，可以将程序日志通过此种方式挂载至宿主机上方便查看。
 

5）手动Build镜像
docker build . -t tdt-config-service:0.1
6）删除类型为<none>的镜像
docker images|grep none|awk '{print $3 }'|xargs docker rmi
6、FAQ
1）删除/var/lib/docker报Device or Resource busy错误
umount /var/lib/docker
rm –rf /var/lib/docker

2）redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
需在代码中配置Redis资源池，参见类RedisCacheConfiguration。

3）添加新的磁盘
fdisk /dev/xvde (注：创建成主分区，若为扩展分区则不能格式化)
mkfs –t ext4 /dev/xvde1
mount /dev/xvde1 /docker
7、资源
1、http://www.runoob.com/docker/docker-resources.html

