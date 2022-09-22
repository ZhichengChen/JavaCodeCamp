package com.chenzhicheng;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter {
  private int sum = 0;
  private Semaphore readSemaphore = new Semaphore(100, true);
  private Semaphore writeSemaphore = new Semaphore(1);
  public int incrAndGet() {
    try {
      writeSemaphore.acquireUninterruptibly();
      return ++sum;
    } finally {
      writeSemaphore.release();
    }
  }
  public int getSum() {
    try {
      readSemaphore.acquireUninterruptibly();
      return sum;
    } finally {
      readSemaphore.release();
    }
  }

  public static void main(String[] args) {
    SemaphoreCounter semaphoreCounter = new SemaphoreCounter();
    new Thread(()->{
      for (int i=0;i<10000;i++) {
        System.out.println(semaphoreCounter.getSum());
      }
    }).start();
    new Thread(()->{
      for (int i=0;i<10000;i++) {
        System.out.println(semaphoreCounter.incrAndGet());
      }
    }).start();
  }

}
