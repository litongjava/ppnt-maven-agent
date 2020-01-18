#bill-maven-agent

安装
1.下载项目
2.打包
``
mvn pckage -DskipTests
``
生成文件bill-maven-agent-1.0-release.tar.gz
3.安装
```
mkdir /opt/spring-boot/
cd /opt/spring-boot/
upload bill-maven-agent-1.0-release.tar.gz to here
tar -xf bill-maven-agent-1.0-release.tar.gz
cd bill-maven-agent-1.0
```
4.启动项目,端口使用10016
```
./springboot.sh start
```
安装完成,访问下面的连接,下载jar包
```
http://127.0.0.1:10016/maven2/com/alibaba/fastjson/1.2.60/fastjson-1.2.60.jar
```
jar包保存到本地,下次下载jar包时不会不会请求远程服务器

5.整合nginx
```
  location /maven2{
    proxy_pass http://127.0.0.1:10016;
  }
  location /bill-maven-agent{
    autoindex on;  # 开启目录文件列表
    charset utf-8,gbk;  # 避免中文乱码
    root /opt/spring-boot/bill-maven-agent-1.0/static;
  }
```

maven默认的远程库地址是
https://repo.maven.apache.org/maven2
可以在nginx中添加https
然后修改本地的dns指向
