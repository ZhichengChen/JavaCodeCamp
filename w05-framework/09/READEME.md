# 学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型

## MyBatis-generator 用法

1. 适当地创建并填写配置文件（参见下面的示例）

```xml
<!DOCTYPE generatorConfiguration PUBLIC
 "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
 "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
  <context id="dsql" targetRuntime="MyBatis3DynamicSql">
    <jdbcConnection driverClass="org.hsqldb.jdbcDriver"
        connectionURL="jdbc:hsqldb:mem:aname" />

    <javaModelGenerator targetPackage="example.model" targetProject="src/main/java"/>

    <javaClientGenerator targetPackage="example.mapper" targetProject="src/main/java"/>

    <table tableName="FooTable" />
  </context>
</generatorConfiguration>
```

2. 将文件保存在某个方便的位置（如 \temp\generatorConfig.xml）

3. 使用这样的命令从命令行运行 MBG

```bash
java -jar mybatis-generator-core-x.x.x.jar -configfile \\temp\\generatorConfig.xml -overwrite
```


##  MyBatis-generator 原理


MyBatis Generator (MBG) 是 MyBatis MyBatis 的代码生成器。它为所有版本的 MyBatis 生成代码。它将反查数据库表，并生成可用于访问表的文件。包括与数据库表交互对象和配置文件。MBG 对大部分简单 CRUD（创建、检索、更新、删除）的数据库操作生成配置。对于联接查询或存储过程仍需人工编写 SQL 和对象代码。


## 示例自定义 TypeHandler 处理复杂类型

见代码


## 参考

https://mybatis.org/generator/quickstart.html
https://juejin.cn/post/6844903982582743048
https://mybatis.org/generator/
https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers
https://juejin.cn/post/7018458631855144968
https://blog.csdn.net/chuobenggu7592/article/details/100978939
