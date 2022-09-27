package com.chenzhicheng;

import com.chenzhicheng.bean.School;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class XbeanTest {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:xbean.xml");
    School school = (School) context.getBean("school");
    System.out.println(school.getClass1());
    System.out.println(school.getStudent100());
  }

}
