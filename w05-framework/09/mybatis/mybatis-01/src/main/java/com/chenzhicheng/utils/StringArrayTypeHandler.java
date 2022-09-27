package com.chenzhicheng.utils;

import com.mysql.cj.util.StringUtils;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringArrayTypeHandler extends BaseTypeHandler<List<String>> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings,
      JdbcType jdbcType) throws SQLException {
    if (jdbcType == null) {
      preparedStatement.setString(i, String.join(",", strings));
    } else {
      preparedStatement.setObject(i, strings, jdbcType.TYPE_CODE);
    }
  }

  @Override
  public List<String> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
    String s = resultSet.getString(columnName);
    return s == null ? null : new ArrayList<>(Arrays.asList(s.split(",")));
  }

  @Override
  public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    String s = resultSet.getString(i);
    return s == null ? null : new ArrayList<>(Arrays.asList(s.split(",")));
  }

  @Override
  public List<String> getNullableResult(CallableStatement callableStatement, int i)
      throws SQLException {
    String s = callableStatement.getString(i);
    return s == null ? null : new ArrayList<>(Arrays.asList(s.split(",")));
  }
}
