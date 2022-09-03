[下载 jar](https://cdn.chenzhicheng.com/assets/zip/gateway-server-0.0.1-SNAPSHOT.jar)

使用 G1 参数运行 `java -jar gateway-server-0.0.1-SNAPSHOT.jar -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+UseG1GC GCLogAnalysis -Xms1500M -Xmx1500M -XX:MaxGCPauseMillis=30 -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -verbose:gc`

查看 id `jps`

```bash
8916 gateway-server-0.0.1-SNAPSHOT.jar
13480 Jps
```

使用 gstat 显示 gc 相关信息 `jstat -gc 8916`

```bash
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT    CGC    CGCT     GCT
 0.0   12288.0  0.0   12288.0 151552.0 131072.0  96256.0      0.0     36096.0 34908.3 4480.0 4013.2      4    0.028   0      0.000   4      0.012    0.040
```

使用 gstack 打印进程堆栈信息 `gstack 8916`

```bash
2022-09-03 09:27:54
Full thread dump OpenJDK 64-Bit Server VM (11.0.15+9-LTS mixed mode):

Threads class SMR info:
_java_thread_list=0x0000023b097fa890, length=27, elements={
0x0000023b00644800, 0x0000023b00ef2000, 0x0000023b00f5c800, 0x0000023b00f5d800,
0x0000023b00f5e800, 0x0000023b00f64000, 0x0000023b00f65000, 0x0000023b00f6b800,
0x0000023b010fc000, 0x0000023b036f5800, 0x0000023b05f2b800, 0x0000023b044a8800,
0x0000023b0783b000, 0x0000023b0707b000, 0x0000023b07958800, 0x0000023b086fa000,
0x0000023b02de1000, 0x0000023b02de1800, 0x0000023b02de2800, 0x0000023b02ddf000,
0x0000023b02de0000, 0x0000023b02de3800, 0x0000023b02ddd800, 0x0000023b02dde800,
0x0000023b02de4000, 0x0000023b05fc7800, 0x0000023b05fc8800
}

"Reference Handler" #2 daemon prio=10 os_prio=2 cpu=15.63ms elapsed=736.83s tid=0x0000023b00644800 nid=0x4c8c waiting on condition  [0x000000d13ecfe000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.15/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.15/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.15/Reference.java:213)

"Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=736.83s tid=0x0000023b00ef2000 nid=0x287c in Object.wait()  [0x000000d13edff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.15/Native Method)
        - waiting on <0x000000070f0b4778> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.15/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000070f0b4778> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.15/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.15/Finalizer.java:170)

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=736.81s tid=0x0000023b00f5c800 nid=0xc0c runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #5 daemon prio=5 os_prio=2 cpu=0.00ms elapsed=736.81s tid=0x0000023b00f5d800 nid=0x3b9c waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Service Thread" #6 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=736.81s tid=0x0000023b00f5e800 nid=0x2aa0 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #7 daemon prio=9 os_prio=2 cpu=1234.38ms elapsed=736.81s tid=0x0000023b00f64000 nid=0x884 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C1 CompilerThread0" #15 daemon prio=9 os_prio=2 cpu=328.13ms elapsed=736.81s tid=0x0000023b00f65000 nid=0x2a64 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Sweeper thread" #19 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=736.81s tid=0x0000023b00f6b800 nid=0x3aac runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #20 daemon prio=8 os_prio=1 cpu=46.88ms elapsed=736.79s tid=0x0000023b010fc000 nid=0xc48 in Object.wait()  [0x000000d13f6fe000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.15/Native Method)
        - waiting on <0x000000070f0b4f08> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.15/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000070f0b4f08> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.15/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.15/InnocuousThread.java:161)

"ContainerBackgroundProcessor[StandardEngine[Tomcat]]" #26 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=734.26s tid=0x0000023b036f5800 nid=0x3c9c waiting on condition  [0x000000d1408ff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.15/Native Method)
        at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.run(ContainerBase.java:1357)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"container-0" #27 prio=5 os_prio=0 cpu=0.00ms elapsed=734.26s tid=0x0000023b05f2b800 nid=0xd84 waiting on condition  [0x000000d1409fe000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.15/Native Method)
        at org.apache.catalina.core.StandardServer.await(StandardServer.java:427)
        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer$1.run(TomcatWebServer.java:182)

"NioBlockingSelector.BlockPoller-1" #28 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.72s tid=0x0000023b044a8800 nid=0x4ae8 runnable  [0x000000d140aff000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(java.base@11.0.15/Native Method)
        at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(java.base@11.0.15/WindowsSelectorImpl.java:357)
        at sun.nio.ch.WindowsSelectorImpl.doSelect(java.base@11.0.15/WindowsSelectorImpl.java:182)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.15/SelectorImpl.java:124)
        - locked <0x000000070b14c7d8> (a sun.nio.ch.Util$2)
        - locked <0x000000070b14c5d8> (a sun.nio.ch.WindowsSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.15/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"http-nio-8088-exec-1" #29 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b0783b000 nid=0x11a8 waiting on condition  [0x000000d140bfe000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-2" #30 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b0707b000 nid=0x4268 waiting on condition  [0x000000d140cfe000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-3" #31 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b07958800 nid=0x3bbc waiting on condition  [0x000000d140dfe000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-4" #32 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b086fa000 nid=0x3b98 waiting on condition  [0x000000d140eff000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-5" #33 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02de1000 nid=0xeec waiting on condition  [0x000000d140ffe000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-6" #34 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02de1800 nid=0x4678 waiting on condition  [0x000000d1410ff000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-7" #35 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02de2800 nid=0x43bc waiting on condition  [0x000000d1411ff000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-8" #36 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02ddf000 nid=0x28c0 waiting on condition  [0x000000d1412fe000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-9" #37 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02de0000 nid=0x4f28 waiting on condition  [0x000000d1413fe000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-exec-10" #38 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02de3800 nid=0x43e0 waiting on condition  [0x000000d1414ff000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.15/Native Method)
        - parking to wait for  <0x000000070b1954f8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.15/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.15/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.15/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.15/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.15/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.15/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-ClientPoller-0" #39 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02ddd800 nid=0x176c runnable  [0x000000d1415ff000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(java.base@11.0.15/Native Method)
        at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(java.base@11.0.15/WindowsSelectorImpl.java:357)
        at sun.nio.ch.WindowsSelectorImpl.doSelect(java.base@11.0.15/WindowsSelectorImpl.java:182)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.15/SelectorImpl.java:124)
        - locked <0x000000070b0a1018> (a sun.nio.ch.Util$2)
        - locked <0x000000070b0a0e98> (a sun.nio.ch.WindowsSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.15/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:798)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-ClientPoller-1" #40 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02dde800 nid=0x2c2c runnable  [0x000000d1416ff000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(java.base@11.0.15/Native Method)
        at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(java.base@11.0.15/WindowsSelectorImpl.java:357)
        at sun.nio.ch.WindowsSelectorImpl.doSelect(java.base@11.0.15/WindowsSelectorImpl.java:182)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.15/SelectorImpl.java:124)
        - locked <0x000000070b0a52e0> (a sun.nio.ch.Util$2)
        - locked <0x000000070b0a5160> (a sun.nio.ch.WindowsSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.15/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:798)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-Acceptor-0" #41 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b02de4000 nid=0x3bdc runnable  [0x000000d1417fe000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.ServerSocketChannelImpl.accept0(java.base@11.0.15/Native Method)
        at sun.nio.ch.ServerSocketChannelImpl.accept(java.base@11.0.15/ServerSocketChannelImpl.java:533)
        at sun.nio.ch.ServerSocketChannelImpl.accept(java.base@11.0.15/ServerSocketChannelImpl.java:285)
        at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:455)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"http-nio-8088-AsyncTimeout" #42 daemon prio=5 os_prio=0 cpu=0.00ms elapsed=733.71s tid=0x0000023b05fc7800 nid=0x4794 waiting on condition  [0x000000d1418ff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.15/Native Method)
        at org.apache.coyote.AbstractProtocol$AsyncTimeout.run(AbstractProtocol.java:1143)
        at java.lang.Thread.run(java.base@11.0.15/Thread.java:829)

"DestroyJavaVM" #44 prio=5 os_prio=0 cpu=2078.13ms elapsed=733.69s tid=0x0000023b05fc8800 nid=0x2f0c waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM Thread" os_prio=2 cpu=31.25ms elapsed=736.84s tid=0x0000023b00643000 nid=0x3b5c runnable

"GC Thread#0" os_prio=2 cpu=15.63ms elapsed=736.85s tid=0x0000023b5be07800 nid=0x2060 runnable

"GC Thread#1" os_prio=2 cpu=15.63ms elapsed=736.41s tid=0x0000023b025f9000 nid=0x2ea8 runnable

"GC Thread#2" os_prio=2 cpu=15.63ms elapsed=736.41s tid=0x0000023b02a1e800 nid=0x44a4 runnable

"GC Thread#3" os_prio=2 cpu=0.00ms elapsed=736.41s tid=0x0000023b022a5800 nid=0x217c runnable

"GC Thread#4" os_prio=2 cpu=15.63ms elapsed=736.41s tid=0x0000023b01fdf800 nid=0x2d44 runnable

"GC Thread#5" os_prio=2 cpu=15.63ms elapsed=736.41s tid=0x0000023b01f2c800 nid=0x3d54 runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=736.85s tid=0x0000023b5be66800 nid=0x45c0 runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=736.85s tid=0x0000023b5be6a800 nid=0x47f0 runnable

"G1 Conc#1" os_prio=2 cpu=0.00ms elapsed=735.90s tid=0x0000023b0741b800 nid=0x4574 runnable

"G1 Conc#2" os_prio=2 cpu=0.00ms elapsed=735.90s tid=0x0000023b0526c800 nid=0x46b0 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=736.85s tid=0x0000023b0051f800 nid=0xcf0 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=736.85s tid=0x0000023b00522800 nid=0x2544 runnable
"VM Periodic Task Thread" os_prio=2 cpu=15.63ms elapsed=736.79s tid=0x0000023b010fb000 nid=0x107c waiting on condition

JNI global refs: 27, weak refs: 0
```

使用 jmap 查看堆内存使用情况 `jmap -histo 8916`

```bash
 num     #instances         #bytes  class name (module)
-------------------------------------------------------
   1:        652329       82670264  [B (java.base@11.0.15)
   2:         32345       11721864  [I (java.base@11.0.15)
   3:        349269        8382456  java.lang.String (java.base@11.0.15)
   4:         18308        4615496  [C (java.base@11.0.15)
   5:         58370        3735680  java.net.URL (java.base@11.0.15)
   6:         42350        3726800  java.lang.reflect.Method (java.base@11.0.15)
   7:         13586        2626336  [J (java.base@11.0.15)
   8:         40748        2596320  [Ljava.lang.Object; (java.base@11.0.15)
   9:         82394        1797256  [Ljava.lang.Class; (java.base@11.0.15)
  10:         53924        1725568  org.springframework.boot.loader.jar.StringSequence
  11:         13662        1363568  [Ljava.util.HashMap$Node; (java.base@11.0.15)
  12:         53924        1294176  org.springframework.boot.loader.jar.JarURLConnection$JarEntryName
  13:         35638        1140416  java.util.HashMap$Node (java.base@11.0.15)
  14:          7552         898696  java.lang.Class (java.base@11.0.15)
  15:         26409         845088  java.util.concurrent.ConcurrentHashMap$Node (java.base@11.0.15)
  16:         18334         733360  java.util.LinkedHashMap$Entry (java.base@11.0.15)
  17:          8266         657144  [S (java.base@11.0.15)
  18:         14475         590712  [Ljava.lang.String; (java.base@11.0.15)
  19:          8045         514880  org.springframework.asm.Label
  20:         10546         506208  java.util.HashMap (java.base@11.0.15)
  21:         20429         490296  java.lang.StringBuilder (java.base@11.0.15)
  22:          6869         384664  org.springframework.asm.Item
  23:         11738         375616  java.lang.ref.WeakReference (java.base@11.0.15)
  24:          5562         355968  java.nio.DirectByteBufferR (java.base@11.0.15)
  25:          7320         351360  org.springframework.asm.Frame
  26:          8715         348600  java.util.StringJoiner (java.base@11.0.15)
  27:          4325         346000  org.springframework.boot.loader.jar.JarURLConnection
  28:         15558         337040  [Ljava.lang.reflect.Type; (java.base@11.0.15)
  29:          5787         324072  java.util.LinkedHashMap (java.base@11.0.15)
  30:         12756         306144  java.util.ArrayList (java.base@11.0.15)
  31:          9365         299680  java.util.ArrayList$Itr (java.base@11.0.15)
  32:          3520         290696  [Ljava.lang.reflect.Method; (java.base@11.0.15)
  33:         17735         283760  java.lang.Object (java.base@11.0.15)
  34:          5872         281856  org.springframework.core.ResolvableType
  35:          6607         264280  java.lang.ref.SoftReference (java.base@11.0.15)
  36:           376         248832  [Ljava.util.concurrent.ConcurrentHashMap$Node; (java.base@11.0.15)
  37:          7054         225728  java.util.LinkedHashMap$LinkedValueIterator (java.base@11.0.15)
  38:          8756         210144  java.lang.PublicMethods$MethodList (java.base@11.0.15)
  39:          6832         197480  [Ljava.lang.annotation.Annotation; (java.base@11.0.15)
  40:          4912         196480  java.util.TreeMap$Entry (java.base@11.0.15)
  41:          2717         195624  java.lang.reflect.Field (java.base@11.0.15)
  42:          6083         194656  java.util.Hashtable$Entry (java.base@11.0.15)
  43:          3391         189896  java.util.concurrent.ConcurrentHashMap$KeyIterator (java.base@11.0.15)
  44:          2915         163240  java.beans.MethodDescriptor (java.desktop@11.0.15)
  45:          8554         154728  [Ljava.lang.reflect.TypeVariable; (java.base@11.0.15)
  46:          6423         154152  java.beans.MethodRef (java.desktop@11.0.15)
  47:          9444         151104  java.lang.Integer (java.base@11.0.15)
  48:          1255         150600  org.springframework.boot.loader.jar.JarEntry
  49:          6238         149712  java.lang.PublicMethods$Key (java.base@11.0.15)
  50:          6205         148920  org.springframework.asm.Edge
  51:          2555         143080  jdk.internal.org.objectweb.asm.Item (java.base@11.0.15)
  52:          4247         135904  org.springframework.asm.Type
  53:          2981         119240  java.lang.ClassNotFoundException (java.base@11.0.15)
  54:          1468         117440  java.lang.reflect.Constructor (java.base@11.0.15)
  55:          4795         115080  org.springframework.core.annotation.AnnotatedElementUtils$MergedAnnotationAttributesProcessor
  56:          2376         114048  jdk.internal.ref.CleanerImpl$PhantomCleanableRef (java.base@11.0.15)
  57:          7004         112064  java.util.HashSet (java.base@11.0.15)
  58:          2203         105744  org.springframework.util.ConcurrentReferenceHashMap$SoftEntryReference
  59:          3010          96320  java.util.LinkedList (java.base@11.0.15)
  60:          1991          95568  java.lang.invoke.MemberName (java.base@11.0.15)
  61:          2320          92800  org.springframework.util.ConcurrentReferenceHashMap$Segment
  62:          2534          87600  [Lorg.springframework.util.ConcurrentReferenceHashMap$Reference;
  63:          3633          87192  java.util.LinkedList$Node (java.base@11.0.15)
  64:          1170          85648  [Ljava.util.Hashtable$Entry; (java.base@11.0.15)
  65:          2668          85376  java.util.LinkedHashMap$LinkedEntryIterator (java.base@11.0.15)
  66:          1297          83008  java.lang.Class$ReflectionData (java.base@11.0.15)
  67:          3408          81792  java.util.Collections$UnmodifiableCollection$1 (java.base@11.0.15)
  68:          2484          79488  java.lang.ref.ReferenceQueue (java.base@11.0.15)
  69:            81          79440  [Ljdk.internal.org.objectweb.asm.Item; (java.base@11.0.15)
  70:          1221          78144  java.util.zip.Inflater (java.base@11.0.15)
  71:           116          78088  [Lorg.springframework.asm.Item;
  72:          2424          77568  java.util.concurrent.locks.ReentrantLock$NonfairSync (java.base@11.0.15)
  73:          2377          76064  java.lang.invoke.LambdaForm$Name (java.base@11.0.15)
  74:          1173          75072  org.springframework.core.MethodParameter
  75:          1323          74088  sun.util.calendar.ZoneInfo (java.base@11.0.15)
  76:          4546          72736  jdk.internal.reflect.ConstantPool (java.base@11.0.15)
  77:          1786          71440  java.lang.invoke.MethodType (java.base@11.0.15)
  78:          2961          71064  java.util.Arrays$ArrayItr (java.base@11.0.15)
  79:          1478          70944  sun.util.locale.LocaleObjectCache$CacheEntry (java.base@11.0.15)
  80:           976          70272  java.beans.PropertyDescriptor (java.desktop@11.0.15)
  81:          2898          69552  sun.reflect.generics.tree.SimpleClassTypeSignature (java.base@11.0.15)
  82:           710          68160  org.springframework.beans.GenericTypeAwarePropertyDescriptor
  83:          2085          66720  java.lang.invoke.MethodType$ConcurrentWeakInternSet$WeakEntry (java.base@11.0.15)
  84:          1541          61640  java.util.HashMap$KeyIterator (java.base@11.0.15)
  85:          1218          58464  org.springframework.boot.loader.jar.ZipInflaterInputStream
  86:          3167          56992  [Lsun.reflect.generics.tree.TypeArgument; (java.base@11.0.15)
  87:          2526          56512  [Lorg.springframework.asm.Type;
  88:           780          56160  org.springframework.core.annotation.AnnotationAttributes
  89:          2320          55680  org.springframework.util.ConcurrentReferenceHashMap$ReferenceManager
  90:          1123          53904  java.util.Hashtable (java.base@11.0.15)
  91:          2241          53784  java.util.Arrays$ArrayList (java.base@11.0.15)
  92:          2191          52584  java.util.zip.Inflater$InflaterZStreamRef (java.base@11.0.15)
  93:           231          51744  org.springframework.asm.MethodWriter
  94:          3119          49904  org.springframework.core.ResolvableType$DefaultVariableResolver
  95:          2008          48192  org.springframework.cglib.core.Signature
  96:          1483          47456  java.util.LinkedList$ListItr (java.base@11.0.15)
  97:          1158          46320  java.util.HashMap$ValueIterator (java.base@11.0.15)
  98:           200          44800  jdk.internal.org.objectweb.asm.MethodWriter (java.base@11.0.15)
  99:          1377          44064  java.util.LinkedHashMap$LinkedKeyIterator (java.base@11.0.15)
 100:           908          43584  org.apache.tomcat.util.modeler.AttributeInfo
 ...
```