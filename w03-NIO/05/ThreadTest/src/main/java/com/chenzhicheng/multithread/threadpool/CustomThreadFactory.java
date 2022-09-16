package com.chenzhicheng.multithread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
  private AtomicInteger serial = new AtomicInteger(0);

  @Override
  public Thread newThread(Runnable r) {
    Thread thread = new Thread(r);
    thread.setDaemon(false);
    thread.setName("CustomThread-" +serial.getAndIncrement());
    return thread;
  }
}
