# maven/spring 的 profile 机制，都有什么用法？

## maven 的 profile 机制

### 使用 -P 指定 profile

```bash
mvn groupId:artifactId:goal -P profile-1,profile-2,?profile-3
```

### \<activeProfiles\> 标签中列出的配置文件将默认在每次项目使用它时被激活

```xml
<settings>
  ...
  <activeProfiles>
    <activeProfile>profile-1</activeProfile>
  </activeProfiles>
  ...
</settings>
```

### jdk 代表配置仅在指定版本的 java 被激活

```xml
<profiles>
  <profile>
    <activation>
      <jdk>1.4</jdk>
    </activation>
    ...
  </profile>
</profiles> 
```

### os 代表配置仅在指定的系统被激活

```xml
<profiles>
  <profile>
    <activation>
      <os>
        <name>Windows XP</name>
        <family>Windows</family>
        <arch>x86</arch>
        <version>5.1.2600</version>
      </os>
    </activation>
    ...
  </profile>
</profiles>
```

### property 内的 name 属性表示当系统属性有值时才被激活

```xml
<profiles>
  <profile>
    <activation>
      <property>
        <name>debug</name>
      </property>
    </activation>
    ...
  </profile>
</profiles>
```

! 表示没有定义时才被激活

```xml
<profiles>
  <profile>
    <activation>
      <property>
        <name>!debug</name>
      </property>
    </activation>
    ...
  </profile>
</profiles>
```

value 表示有值并且值为 value 内的值才会被激活

<profiles>
  <profile>
    <activation>
      <property>
        <name>environment</name>
        <value>test</value>
      </property>
    </activation>
    ...
  </profile>
</profiles>

激活的示例如下

```bash
mvn groupId:artifactId:goal -Denvironment=test
```

value 里的 ! 表示没有定义或者值为 false 时才激活 

```xml
<profiles>
  <profile>
    <activation>
      <property>
        <name>debug</name>
        <value>!true</value>
      </property>
    </activation>
    ...
  </profile>
</profiles>
```

指定值的命令如下

```bash
mvn groupId:artifactId:goal
mvn groupId:artifactId:goal -Ddebug=false
```

### 当缺失某文件时才激活

```xml
<profiles>
  <profile>
    <activation>
      <file>
        <missing>target/generated-sources/axistools/wsdl2java/org/apache/maven</missing>
      </file>
    </activation>
    ...
  </profile>
</profiles>
```

### 默认即激活

```xml
<profiles>
  <profile>
    <id>profile-1</id>
    <activation>
      <activeByDefault>true</activeByDefault>
    </activation>
    ...
  </profile>
</profiles>
```

### 不激活指定 profile

```bash
mvn groupId:artifactId:goal -P !profile-1,!profile-2,!?profile-3
```

或者 

```bash
mvn groupId:artifactId:goal -P -profile-1,-profile-2,-?profile-3
```

## spring 的 profile 机制

### 通过 @Profile 注解来使用不同的 profiles

```java
@Configuration
@Profile("production")
public class ProductionConfiguration {

    // ...

}
```

### 可以通过 spring.profiles.active 激活不同的 profiles

```bash
spring.profiles.active=dev
```

### 通过 spring.profiles.include 包含 profiles

```properties
---
my.property: fromyamlfile
---
spring.profiles: prod
spring.profiles.include: proddb,prodmq
```

### 可以在程序启动前使用 SpringApplication.setAdditionalProfiles(...) 或者 ConfigurableEnvironment 接口来设置 profile


### profile 文件可以是 application.properties 或者 application.yml

## 参考：

https://maven.apache.org/guides/introduction/introduction-to-profiles.html
https://docs.spring.io/spring-boot/docs/1.2.0.M1/reference/html/boot-features-profiles.html