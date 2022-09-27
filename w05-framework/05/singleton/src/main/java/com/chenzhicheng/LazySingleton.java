package com.chenzhicheng;

/*
 * 懒汉式
 * 在使用时才进行初始化，线程不安全
 */
public class LazySingleton {

  private static LazySingleton instance;

  private LazySingleton() {};

  public static LazySingleton getInstance() {
    if (instance == null) {
      instance = new LazySingleton();
    }
    return instance;
  }

}
