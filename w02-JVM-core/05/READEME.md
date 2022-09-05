# 运行课上的例子，以及 Netty 的例子，分析相关现象

## Java 实现一个最简的 HTTP 服务 01

1. 创建一个 ServerSocket
2. 绑定 8080 端口
3. 当有客户端请求时通过 accept 方法拿到 Socket，进而可以进行处理
4. 模拟输出 HTTP 报文头和 hello
5. 关闭 socket

设置 -Xmx 512 启动 `java -Xmx512m HttpServer01`

压测 `sb -u http://localhost:8801 -c 40 -N 30`

```bash
Starting at 2022/9/4 10:53:03
[Press C to stop the test]
150667  (RPS: 4420.3)
---------------Finished!----------------
Finished at 2022/9/4 10:53:37 (took 00:00:34.1209649)
Status 200:    149953
Status 303:    722

RPS: 4849.8 (requests/second)
Max: 92ms
Min: 0ms
Avg: 2.6ms

  50%   below 1ms
  60%   below 1ms
  70%   below 2ms
  80%   below 6ms
  90%   below 8ms
  95%   below 9ms
  98%   below 11ms
  99%   below 16ms
99.9%   below 30ms
```

单线程执行

## Java 实现一个最简的 HTTP 服务 02

绑定端口 8802

每个客户端请求进来时创建一个线程

设置 -Xmx 512 启动 `java -Xmx512m HttpServer02`

压测 `sb -u http://localhost:8802 -c 40 -N 30`

```bash
Starting at 2022/9/4 15:07:26
[Press C to stop the test]
112775  (RPS: 3271.9)
---------------Finished!----------------
Finished at 2022/9/4 15:08:01 (took 00:00:34.6021987)
Status 200:    112745
Status 303:    34

RPS: 3620.7 (requests/second)
Max: 174ms
Min: 0ms
Avg: 1.4ms

  50%   below 1ms
  60%   below 1ms
  70%   below 1ms
  80%   below 1ms
  90%   below 2ms
  95%   below 4ms
  98%   below 9ms
  99%   below 15ms
99.9%   below 37ms
```

多线程版本比单线程版本 RPS 低，是多线程上下文切换占用系统资源

## Java 实现一个最简的 HTTP 服务 03

绑定 8803 端口

创建一个固定大小的线程池来处理

设置 -Xmx 512 启动 `java -Xmx512m HttpServer03`

压测 `sb -u http://localhost:8803 -c 40 -N 30`

```bash
Starting at 2022/9/4 15:25:37
[Press C to stop the test]
112369  (RPS: 3255.4)
---------------Finished!----------------
Finished at 2022/9/4 15:26:12 (took 00:00:34.6718130)
Status 200:    109596
Status 303:    2773

RPS: 3604.2 (requests/second)
Max: 151ms
Min: 0ms
Avg: 0.7ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 1ms
  95%   below 4ms
  98%   below 11ms
  99%   below 17ms
99.9%   below 31ms
```

线程池版本比多线程版本时延低，是线程可以复用，节约了创建销毁线程的开销

## Netty 实现一个最简的 HTTP 服务

压测 `sb -u http://localhost:8808/test -c 40 -N 30`

```bash
Starting at 2022/9/5 11:49:09
[Press C to stop the test]
222430  (RPS: 6310.2)
---------------Finished!----------------
Finished at 2022/9/5 11:49:44 (took 00:00:35.3195258)
Status 200:    222438

RPS: 7155.3 (requests/second)
Max: 130ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 0ms
99.9%   below 6ms
```

Netty 版本的服务器使用 NIO，性能最优