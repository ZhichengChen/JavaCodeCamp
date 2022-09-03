[下载 jar](https://cdn.chenzhicheng.com/assets/zip/gateway-server-0.0.1-SNAPSHOT.jar)

使用 G1 参数运行 `java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar gateway-server-0.0.1-SNAPSHOT.jar`

查看刚刚刚刚运行的进程的 ID `jps -l`

```bash
3828 gateway-server-0.0.1-SNAPSHOT.jar
10328 jdk.jcmd/sun.tools.jps.Jps
```

`jhsdb jmap --heap --pid 3828` 查看 GC 情况

```bash
Attaching to process ID 3828, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 11.0.15+9-LTS

using thread-local object allocation.
Garbage-First (G1) GC with 13 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 1073741824 (1024.0MB)
   NewSize                  = 1363144 (1.2999954223632812MB)
   MaxNewSize               = 643825664 (614.0MB)
   OldSize                  = 5452592 (5.1999969482421875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 1048576 (1.0MB)

Heap Usage:
G1 Heap:
   regions  = 1024
   capacity = 1073741824 (1024.0MB)
   used     = 24334336 (23.20703125MB)
   free     = 1049407488 (1000.79296875MB)
   2.2663116455078125% used
G1 Young Generation:
Eden Space:
   regions  = 4
   capacity = 205520896 (196.0MB)
   used     = 4194304 (4.0MB)
   free     = 201326592 (192.0MB)
   2.0408163265306123% used
Survivor Space:
   regions  = 16
   capacity = 16777216 (16.0MB)
   used     = 16777216 (16.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation:
   regions  = 4
   capacity = 851443712 (812.0MB)
   used     = 3362816 (3.20703125MB)
   free     = 848080896 (808.79296875MB)
   0.39495458743842365% used
```