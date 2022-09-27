package com.chenzhicheng.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
  private static SqlSessionFactory sqlSessionFactory;

  static {
    String resource = "mybatis-config.xml";

    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static SqlSession getSqlSession() {
    return sqlSessionFactory.openSession();
  }


}
