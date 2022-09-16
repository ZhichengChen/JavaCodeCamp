package com.chenzhicheng.multithread.threadpool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPollExecutorTest {

  public static void main(String[] args) {
    int coreSize = Runtime.getRuntime().availableProcessors();
    int maxSize = Runtime.getRuntime().availableProcessors() * 2;
    System.out.println(maxSize);
    BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(500);
    CustomThreadFactory customThreadFactory = new CustomThreadFactory();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, maxSize, 1, TimeUnit.MINUTES,
        workQueue, customThreadFactory);
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      executor.execute(() -> {
        System.out.println(Thread.currentThread().getName() + " ==== " + finalI);
      });
    }
  }
}
