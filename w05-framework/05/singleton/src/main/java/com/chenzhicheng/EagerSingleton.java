package com.chenzhicheng;

/**
 * 饿汉式
 * 启动时即创建，浪费内存
 */
public class EagerSingleton {

  private static final EagerSingleton instance = new EagerSingleton();

  private EagerSingleton() {}

  public static EagerSingleton getInstance() {
    return instance;
  }

}
