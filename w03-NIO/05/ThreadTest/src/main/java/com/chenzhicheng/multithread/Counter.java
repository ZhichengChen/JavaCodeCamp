package com.chenzhicheng.multithread;

/**
 * 由于 Counter incr 方法不是线程安全的，多个线程调用时会产生竞态，导致不可预期的结果
 */
public class Counter {

  private int sum = 0;
  public synchronized void incr() { sum = sum+1;}
  public int getSum() {return sum;}

  public static void main(String[] args) throws InterruptedException {
    int loop = 100000;
    Counter counter = new Counter();
    for (int i=0;i<loop;i++) {
      counter.incr();
    }
    System.out.println("single thread: " + counter.getSum());

    final Counter counter2 = new Counter();
    Thread t1 = new Thread(()->{
      for (int i=0;i<loop/2;i++) {
        counter2.incr();
      }
    });
    Thread t2 = new Thread(()-> {
      for (int i=0;i<loop/2;i++) {
        counter2.incr();
      }
    });
    t1.start();
    t2.start();
    Thread.sleep(1000);

    System.out.println("multiple threads: " +counter2.getSum());
  }

}
