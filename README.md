# 建设项目信息管理系统

## 项目结构

```mermaid
graph TB
project[SpringBootCpims项目] --> src(src源码目录)
src --> main[main程序主函数]
src --> test[test单元测试]
main --> java(java)
main --> resources(resources)
java --> com(com.javakc.springbootcpims)
com --> common(common-公共配置目录)
com --> modules(modules-项目模块目录)
com --> application(SpringbootCpimsApplication项目启动)
resources --> mapper(mybatis映射文件)
resources --> application.yml(springboot核心配置文件)
mapper --> XXXmapper(XXXMapper.xml)
mapper --> YYYmapper(YYYmapper.xml)
```



## 项目框架

├── spring全家桶
│   ├── spring-framework
│   ├── spring-boot
│   ├── spring-data
│   └── spring-security
├── 数据库相关
│   ├── mybatis
│   └── druid
└── 。。。。。。



## 热部署配置

_____________

热部署配置
File - Settings - Build - Compiler - Build project automatically(勾选)
ctrl + shift + alt + /, 选择Registry, Compiler autoMake allow when app running(勾选) 

_____________

## 项目访问地址
*druid:* http://localhost:8080/druid/index.html  
*swagger:* http://localhost:8080/swagger-ui/index.html  
