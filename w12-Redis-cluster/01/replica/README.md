# 配置 redis 的主从复制

## 手动方式

1. 安装 Docker

2. 在 Docker 中创建定义桥接网络

```bash
docker network create skynet
```

3. 拉取 redis 镜像

```bash
docker pull redis
```

4. 运行主节点实例

```bash
docker container run --name redis-master --network skynet -d redis
```

5. 运行从节点实例

```bash
docker container run --name redis-slave --network skynet -d redis
```

6. 进入从节点终端

```bash
docker exec -it redis-slave /bin/sh
```

7. 进入 redis 

```bash
redis-cli
```

8. 连接主节点

```redis
slaveof redis-master 6379
```

## Docker Compose 方式

```bash
docker-compose up -d
```