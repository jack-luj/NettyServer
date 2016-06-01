package com.reflect;

import java.util.List;

/**
 * Created by jackl on 2016/6/1.
 */
public class UserBean {
    private Integer id;
    private Integer age;
    private String name;
    private String address;
    private List<String> phoneList;

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public UserBean(){
        System.out.println("实例化");
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }



}
