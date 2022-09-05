压下博客

压测工具 sb 压测 `sb -u "https://www.chenzhicheng.com" -c 10 -n 100`

```bash
Starting at 2022/9/4 9:31:21
[Press C to stop the test]
100     (RPS: 10.3)
---------------Finished!----------------
Finished at 2022/9/4 9:31:31 (took 00:00:09.7912941)
Status 200:    100

RPS: 9.3 (requests/second)
Max: 3069ms
Min: 106ms
Avg: 905.5ms

  50%   below 716ms
  60%   below 970ms
  70%   below 1170ms
  80%   below 1495ms
  90%   below 1983ms
  95%   below 2376ms
  98%   below 2561ms
  99%   below 3069ms
99.9%   below 3069ms
```