package com.chenzhicheng.multithread;

/**
 * 思考一下输出的结果是什么
 *
 * 因为 thread 是守护线程，守护线程在主线程结束后会退出，所以不会打印
 */
public class ThreadTest {
  public static void main(String[] args) {
    Runnable task = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Thread t = Thread.currentThread();
        System.out.println("当前线程：" + t.getName());
      }
    };
    Thread thread = new Thread(task);
    thread.setName("test-thread-1");
    thread.setDaemon(true);
    thread.start();
  }
}
