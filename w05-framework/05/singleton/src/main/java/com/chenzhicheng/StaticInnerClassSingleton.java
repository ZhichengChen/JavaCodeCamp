package com.chenzhicheng;

/**
 * 静态内部类式
 * 通过无锁的方式实现单例，效率更高
 */
public class StaticInnerClassSingleton {

  private StaticInnerClassSingleton() {}
  private static class SingletonInstance {
    private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
  }
  public static synchronized StaticInnerClassSingleton getInstance() {
    return SingletonInstance.INSTANCE;
  }

}
