package com.chenzhicheng.pojo;

import java.util.List;

public class User {

  private int id;
  private String name;
  private String pwd;

  private List<String> tags;

  public User(int id, String name, String pwd, List<String> tags) {
    this.id = id;
    this.name = name;
    this.pwd = pwd;
    this.tags = tags;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
