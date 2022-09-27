package com.chenzhicheng;

/**
 * 枚举式
 * 避免了反射和反序列化方式破坏单例
 */
public class EnumSingleton {

  private enum Enum {
    INSTANCE;
    private EnumSingleton instance;

    Enum() {
      instance = new EnumSingleton();
    }

    public EnumSingleton getInstance() {
      return instance;
    }
  }

  public static EnumSingleton getInstance() {
    return Enum.INSTANCE.getInstance();
  }

}
