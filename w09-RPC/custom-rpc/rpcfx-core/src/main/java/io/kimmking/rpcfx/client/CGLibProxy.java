package io.kimmking.rpcfx.client;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {
  // CGLib需要代理的目标对象
  private Object targetObject;

  public Object createProxyObject(Object obj) {
    this.targetObject = obj;
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(obj.getClass());
    enhancer.setCallback(this);
    Object proxyObj = enhancer.create();
    return proxyObj;
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    Object obj = null;
    //切面逻辑（advise），此处是在目标类代码执行之前
    System.out.println("---before intercept----");
    obj = method.invoke(targetObject, objects);
    System.out.println("---after intercept----");
    return obj;
  }
}
