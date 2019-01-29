# 使用微服务扩展宠物诊所应用
根据 Spring 官方的宠物诊所的例子，在它之上扩充微服务应用。
## 目的
在现有 Spring Boot 的应用下，如何用较小代价添加微服务的应用。
## 方法
1. 在当前程序中添加 Eureka-client、Feign 和 Ribbon，添加需要获取数据的 Bean文件。具体修改请[在此查看](https://github.com/zeahoo/spring-petclinic/commit/9b3cfbf64f7a004d934d1f803bcca5f8fe55d1e9)。
2. 新增一个微服务应用，即本例中的 pet 应用。
3. 新增一个 Eureka 服务，即本例中的 eureka-naming-server 应用。
## 启动步骤
```
git clone https://github.com/zeahoo/spring-petclinic.git
cd eureka-naming-server/
mvn package
cd ../pet/
mvn package
cd ../petclinic/
mvn package
java -jar eureka-naming-server/target/*.jar
java -jar pet/target/*.jar --server.port=8100
java -jar pet/target/*.jar --server.port=8101
java -jar petclinic/target/*.jar
```
按照以上步骤，我们分别启动了一个 Eureka 服务，一个宠物诊所的应用，和两个宠物信息查询的微服务。访问 http://localhost:8761/ 可以查看当前 Eureka 发现的应用。

## 测试

http://localhost:8080/ 为宠物诊所的主服务，在没有修改其功能的基础上，添加了一个请求地址 http://localhost:8080/pet/1 拿来测试微服务是否成功，在浏览器输入该地址，结果如下：

```
{
  "id": 1,
  "name": "Leo",
  "birthDate": "2000-09-07",
  "type": {
    "id": 1,
    "name": "cat",
    "new": false
  },
  "owner": {
    "address": "110 W. Liberty St.",
    "city": "Madison",
    "telephone": "6085551023",
    "firstName": "George",
    "lastName": "Franklin",
    "id": 1
  },
  "port": 8100
}
```
这时的端口号指向 8100，代表该应用从 8100 端口的微服务调用数据请求。此时继续输入该地址，结果如下：
```
{
  "id": 1,
  "name": "Leo",
  "birthDate": "2000-09-07",
  "type": {
    "id": 1,
    "name": "cat",
    "new": false
  },
  "owner": {
    "address": "110 W. Liberty St.",
    "city": "Madison",
    "telephone": "6085551023",
    "firstName": "George",
    "lastName": "Franklin",
    "id": 1
  },
  "port": 8101
}
```
端口指向 8101，说明 Ribbon 的负载均衡起了作用。

## 结束
