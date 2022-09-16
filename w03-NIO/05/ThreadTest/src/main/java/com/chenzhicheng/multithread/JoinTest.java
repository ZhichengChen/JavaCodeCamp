package com.chenzhicheng.multithread;

/**
 * wait 和 join 的演示
 * <p>
 * 如果想要保证 thread1 在主线程的 synchronized 前面执行，可以在 synchronized 里调用 thread1 join 方法
 * <p>
 * join 当前线程不会释放已经持有的对象锁，t.join 因为内部调用了 t.wait，所以会释放掉 t 这个对象上的同步锁
 * wait 放弃锁，wait 是 object 对象的方法
 * sleep 不释放对象锁 是 Thread 线程对象的方法
 * yield 不释放锁资源，放弃获取的 CPU 时间片，由运行状态改为就绪状态，让 OS 再次选择线程
 * <p>
 * 为什么要使用同一个锁
 * <p>
 * 可以不使用同一个锁，使用同一个锁是为了引出下一个问题
 * 为什么使用 Object 当做锁会死锁，使用 thread1 当做锁不会死锁
 * wait 是 object 上的方法，调用 t.wait() 会释放 thread1 上的锁，而不会释放 lock
 * 如果使用 thread1 则会释放主线程上的锁
 */
public class JoinTest {

  public static void main(String[] args) {
    Object lock = new Object();
    MyThread thread1 = new MyThread("thread1 --");
    lock = thread1;
    thread1.setLock(lock);
    thread1.start();

    synchronized (lock) {
      for (int i = 0; i < 100; i++) {
        if (i == 20) {
          try {
//            thread1.join();
            lock.wait(0);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + " -- " + i);
        }
      }
    }
  }
}

class MyThread extends Thread {

  private String name;
  private Object lock;

  public void setLock(Object lock) {
    this.lock = lock;
  }

  public MyThread(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    synchronized (lock) {
      for (int i = 0; i < 100; i++) {
        System.out.println(name + i);
      }
    }
  }
}
