[下载 jar](https://cdn.chenzhicheng.com/assets/zip/gateway-server-0.0.1-SNAPSHOT.jar)

压测工具 sb 压测 `sb -u "http://localhost:8088/api/hello" -c 10 -n 100`

```bash
Starting at 2022/9/4 8:24:17
[Press C to stop the test]
100     (RPS: 1204.8)
---------------Finished!----------------
Finished at 2022/9/4 8:24:18 (took 00:00:00.2540008)
Status 200:    100

RPS: 81.5 (requests/second)
Max: 35ms
Min: 0ms
Avg: 4.1ms

  50%   below 0ms
  60%   below 1ms
  70%   below 1ms
  80%   below 1ms
  90%   below 34ms
  95%   below 35ms
  98%   below 35ms
  99%   below 35ms
99.9%   below 35ms
```