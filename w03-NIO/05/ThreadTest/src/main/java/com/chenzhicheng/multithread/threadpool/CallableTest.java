package com.chenzhicheng.multithread.threadpool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableTest implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    Integer sleep = new Random().nextInt(10000);
    TimeUnit.MICROSECONDS.sleep(sleep);
    return sleep;
  }

  public static void main(String[] args) throws Exception {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    Future<Integer> future = executor.submit(new CallableTest());
    System.out.println(future.get());
  }
}
