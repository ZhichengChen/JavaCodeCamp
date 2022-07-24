# 配置 redis 的 sentinel 高可用

0. 将 `sentinel1`、`sentinel2`、`sentinel3` 文件夹内的 `sentinel.conf` 文件内的 `192.168.16.144` 替换成本机的 ip 地址。

1. 构建镜像

```bash
docker build --pull --rm -f "sentinel1\Dockerfile" -t sentinel "sentinel1"
docker build --pull --rm -f "sentinel2\Dockerfile" -t sentinel2 "sentinel2" 
docker build --pull --rm -f "sentinel3\Dockerfile" -t sentinel3 "sentinel3" 
```

2. 运行 sentinel 和 redis 实例

```bash
docker-compose up -d
```

3. 查看 log

```bash
docker-compose logs -f
```

4. 删除之前运行的 redis 主节点

```bash
docker container stop redis-master
```