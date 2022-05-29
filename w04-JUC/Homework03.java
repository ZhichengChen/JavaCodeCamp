import java.util.concurrent.CountDownLatch;

public class Homework03 {

  static int result = 0;


  public static void main(String[] args) throws InterruptedException{

    //t1
    long start=System.currentTimeMillis();

    Thread t1 = new Thread(()->{
      result = sum();
    });

    t1.start();
    t1.join();

    System.out.println(result);

    System.out.println((System.currentTimeMillis()-start) + " ms");

    //t2
    start=System.currentTimeMillis();
    result = 0;
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        result = sum();
      }
    });
    t2.start();
    t2.join();

    System.out.println(result);

    System.out.println((System.currentTimeMillis()-start) + " ms");

    //t3
    start=System.currentTimeMillis();
    result = 0;
    Thread t3 = new T3();
    t3.start();
    t3.join();

    System.out.println(result);

    System.out.println((System.currentTimeMillis()-start) + " ms");

  }

  static class T3 extends Thread{
    @Override
    public void run() {
      result = sum();
    }
  }

  private static int sum() {
    return fibo(36);
  }

  private static int fibo(int a) {
    if ( a < 2)
      return 1;
    return fibo(a-1) + fibo(a-2);
  }
}