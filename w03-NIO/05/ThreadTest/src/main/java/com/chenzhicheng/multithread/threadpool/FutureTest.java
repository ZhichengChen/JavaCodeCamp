package com.chenzhicheng.multithread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest {

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, TimeoutException {
    Callable<Integer> task = new CallableTest();
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    Future<Integer> future1 = executor.submit(task);
    Future<Integer> future2 = executor.submit(task);
    Integer result1 = future1.get(1, TimeUnit.SECONDS);
    Integer result2 = future2.get(1, TimeUnit.SECONDS);
    System.out.println("result1=" +result1);
    System.out.println("result2=" +result2);
  }

}
