package com.chenzhicheng;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicCounter {
  private AtomicInteger sum = new AtomicInteger(0);

//  AtomicLong

  LongAdder
  public int incrAndGet() {
    return sum.incrementAndGet();
  }
  public int getSum() {
    return sum.get();
  }

  public static void main(String[] args) {
    AtomicCounter atomicCounter = new AtomicCounter();
    new Thread(()->{
      for (int i=0;i<100_0000;i++) {
        System.out.println(atomicCounter.incrAndGet());
      }
    }).start();
    new Thread(()->{
      for (int i=0;i<100_0000;i++) {
        System.out.println(atomicCounter.getSum());
      }
    }).start();
  }

}
