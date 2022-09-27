package io.kimmking.spring;

import org.springframework.context.ApplicationContext;

public class Student {


    private int id;
    private String name;

    private String beanName;
    private ApplicationContext applicationContext;

    public void init(){
        System.out.println("hello...........");
    }
    
    public static Student create(){
        return new Student();
    }

    public void print() {
        System.out.println(this.beanName);
        System.out.println("   context.getBeanDefinitionNames() ===>> "
                + String.join(",", applicationContext.getBeanDefinitionNames()));

    }


}
