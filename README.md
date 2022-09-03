分别用 100 个字以上的一段话，加上一幅图（架构图或脑图），总结自己
对下列技术的关键点思考和经验认识:

## JVM

- JVM 核心技术的三个核心核心知识点：字节码技术、类加载器、内存模型。
- JVM 启动参数有 6 种。
- JDK 内置命令行工具，图形化工具。
- Java 一共支持 7 类 GC，串行 GC Serial GC：单线程执行，应用需要暂停；并行 GC ParNew Parallel Scavenge Parallel Old：多线程并行的执行垃圾回收。CMS Concurrent Makr Sweep：多线程并发标记和清除，关注与降低延迟。G1 G First：通过划分多个内存区域做增量整理和回收，进一步降低延迟。ZGC Z Garbage Collector：通过着色指针和读屏障，实现几乎全部的并发执行，几毫秒级别的延迟，线性可扩展。Epsilon：实验性的 GC，供性能分-析使用。Shenandedoah：G1 的改进版，跟 ZGC 类似。
- GC 日志解读与分析。
- JVM 线程堆栈数据分析。
- 内存分析相关工具。
- JVM 分析调优经验。
- JVM 疑难情况问题分析。

![JVM 脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/JVM.png)

## NIO

- Java Socket 编程
- IO 处理过程分析
- IO 模型与 NIO
- Netty 介绍与示例
- 什么是高性能
- Netty 如何实现高性能
- Netty 网络优化
- 典型应用：API 网关
- 自己动手实现 API 网关

![NIO 脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/NIO.png)

## 并发编程

- Java 多线程基础
- Java 多线程
- Java 线程安全
- 线程池原理与应用
- Java 并发包
- 什么是锁
- 并发原子类
- 并发工具类
- 常见线程安全类型
- 并发编程相关内容
- 并发编程经验总结


![并发编程 脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/Concurrent.png)

## Spring 和 ORM 等框架

- Spring 发展与框架
- Spring AOP：怎么用 AOP 来对类进一步包装
- Spring Bean：Bean 怎么管理的，Bean 加载过程，Bean 加载的干预
- Spring XML 配置
- Spring JMS 示例
- Spring Boot
- Hibernate
- MyBatis
- Spring ORM
- Java8 Lambda/Stream
- Lombok/Guava
- 设计模式与设计原则
- 单元测试与编程经验

![Spring 和 ORM 等框架脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/Framework.png)

## MySQL 数据库和 SQL

- 性能与关系数据库
- MySQL 与 SQL
- 数据库原理
- 参数优化与设计优化
- MySQL事务与锁
- DB 与 SQL 优化
- 常见场景分析
    - 怎么实现主键
    - 高效分页
    - 乐观锁与悲观锁

![MySQL 数据库和 SQL 脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/MySQL.png)

## 分库分表

- 单机数据库的技术演进：单机数据库，到集群分布式
- MySQL 主从复制：异步复制， 半同步复制，MGR 复制
- MySQL 读写分离：框架，中间件
- MySQL 的高可用：什么是高可用，为什么要高可用，高可用能用来做什么，高可用方案：手动、自动、MySQL 内置的方案，Orchestrator 图形化界面
- 为什么要分库分表
- 数据库水平拆分/垂直拆分
- 相关的框架和中间件
- 如何做数据迁移


![分库分表脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/分库分表.png)

## RPC 和微服务

- RPC 技术原理
- PRC 技术框架
- PRC 框架设计
- 分布式服务化
- 微服务架构发展历程
- 微服务使用场景与最佳实践
- Spring Cloud 技术体系
- 微服务相关技术与工具

![RPC 和微服务脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/RPC%20%E5%92%8C%E5%BE%AE%E6%9C%8D%E5%8A%A1.png)

## 分布式缓存

- 从数据库到缓存
- 本地与远程缓存
- 缓存策略与使用
- 缓存的常见问题：缓存穿透，缓存的击穿，雪崩
- Redis 基本功能：数据结构，单线程还是多线程
- Redis 六大场景
- Java、Spring 整合
- Redis 高级功能
- Redis 的集群与高可用
- Redisson
- Hazelcast

![分布式缓存脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/%E5%88%86%E5%B8%83%E5%BC%8F%E7%BC%93%E5%AD%98.png)

## 分布式消息队列

- 系统间的通信方式，讲到 MQ
- 从队列到消息服务 从内存里的一个进程内部的一个数据结构 Queue 出发，讲到消息服务
- 消息模式与消息协议
- Active MQ 消息中间件，通过 JMS 编程的方式开对 ActiveMQ 进行收发消息的 Demo
- Kafka 入门
- Kafka 简单使用
- Kafka 集群部署 多 Partition 多副本因子
- Kafka 高级特性 生产者消费者高级特性
- RabbitMQ Pivotal Erlang
- RocketMQ Apache Alibaba
- Pulsar Apache
- EIP/Camel
- 动手做 MQ

![分布式消息队列脑图](https://github.com/ZhichengChen/JavaCodeCamp/raw/main/assets/%E5%88%86%E5%B8%83%E5%BC%8F%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97.png)
