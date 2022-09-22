package com.chenzhicheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
  private int sum = 0;
      private ReadWriteLock lock = new ReentrantReadWriteLock(true);
  public int incrAndGet() {
    try {
      lock.writeLock().lock();
      return ++sum;
    } finally {
      lock.writeLock().unlock();
    }
  }
  public int getSum() {
    try {
      lock.readLock().lock();
      return ++sum;
    } finally {
      lock.readLock().unlock();
    }
  }

  public static void main(String[] args) {
    LockSupport
    ReadWriteLockCounter readWriteLockCounter = new ReadWriteLockCounter();
    new Thread(()->{
      for (int i=0;i<100_0000;i++) {
        System.out.println(readWriteLockCounter.incrAndGet());
      }
    }).start();
    new Thread(()->{
      for (int i=0;i<100_0000;i++) {
        System.out.println(readWriteLockCounter.getSum());
      }
    }).start();
  }

}
