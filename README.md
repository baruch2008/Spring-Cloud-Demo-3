keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650

DOCKER_OPTS="--insecure-registry hub.witcloud.huawei.com -H unix:///var/run/docker.sock -H 0.0.0.0:2375"
