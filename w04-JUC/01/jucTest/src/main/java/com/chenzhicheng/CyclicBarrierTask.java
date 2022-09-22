package com.chenzhicheng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTask implements Runnable{
  private CyclicBarrier barrier;
  public CyclicBarrierTask(CyclicBarrier barrier) {
    this.barrier = barrier;
  }

  @Override
  public void run() {
    Integer millis = new Random().nextInt(10000);
    try {
      TimeUnit.MICROSECONDS.sleep(millis);
      this.barrier.await();
      System.out.println("开吃：" +  Thread.currentThread().getName());
      TimeUnit.MICROSECONDS.sleep(millis);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    int num = 8;
    CyclicBarrier barrier1 = new CyclicBarrier(num);
    List<CompletableFuture> list = new ArrayList<>(num);
    for (int i=0;i<num;i++) {
      CompletableFuture<Void> future = CompletableFuture.runAsync(new CyclicBarrierTask(barrier1));
      list.add(future);
    }
    for (CompletableFuture future:list) {
      future.get();
    }
    barrier1.reset();
  }
}
