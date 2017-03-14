package com.github.bean;

import java.io.Serializable;

/**
 * Created by xiedan on 2017/2/20.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1168241500527934398L;
    private int id;

    private int age;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
