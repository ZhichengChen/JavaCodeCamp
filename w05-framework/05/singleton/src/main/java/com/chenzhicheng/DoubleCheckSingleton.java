package com.chenzhicheng;

/**
 * Double Check 方式
 * 线程安全
 */
public class DoubleCheckSingleton {
  private static volatile DoubleCheckSingleton instance;
  private DoubleCheckSingleton() {}
  public static synchronized DoubleCheckSingleton getInstance() {
    if (instance == null) {
      synchronized (DoubleCheckSingleton.class) {
        if (instance == null) {
          instance = new DoubleCheckSingleton();
        }
      }
    }
    return instance;
  }
}
