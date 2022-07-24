# 配置 redis 的 Cluster 集群

0. 将 `docker-compose.yml` 文件内的所有 `192.168.16.144` 批量替换成本机的 ip 地址。

1. 构建镜像

```bash
docker build --pull --rm -f "7000\Dockerfile" -t redis0 "7000"
docker build --pull --rm -f "7001\Dockerfile" -t redis1 "7001"
docker build --pull --rm -f "7002\Dockerfile" -t redis2 "7002"
docker build --pull --rm -f "7003\Dockerfile" -t redis3 "7003"
docker build --pull --rm -f "7004\Dockerfile" -t redis4 "7004"
docker build --pull --rm -f "7005\Dockerfile" -t redis5 "7005"
```

2. 运行

```bash
docker-compose up -d
```

3. 查看 log

```bash
docker-compose logs -f
```

4. 进入 cluster 控制台

```bash
docker exec -it cluster /bin/bash
```

5. 创建 cluster

```bash
redis-cli --cluster create 192.168.16.144:7000 192.168.16.144:7001 192.168.16.144:7002 192.168.16.144:7003 192.168.16.144:7004 192.168.16.144:7005 --cluster-replicas 1
```

在 promp 里输入 `yes` 回车 