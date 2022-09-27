package com.chenzhicheng.dao;

import com.chenzhicheng.pojo.User;
import com.chenzhicheng.utils.MyBatisUtils;
import com.chenzhicheng.utils.StringArrayTypeHandler;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Test;

public class UserDaoTest {

  @Test
  public void test() {
    SqlSession sqlSession = MyBatisUtils.getSqlSession();

//    UserDao userDao = sqlSession.getMapper(UserDao.class);
//    List<User> userList = userDao.getUserList();

    TypeHandlerRegistry typeHandlerRegistry = sqlSession.getConfiguration().getTypeHandlerRegistry();
    typeHandlerRegistry.register(List.class, StringArrayTypeHandler.class);
        UserDao userDao = sqlSession.getMapper(UserDao.class);

    User user = userDao.getUser();

      System.out.println(user);

    sqlSession.close();
  }

}
