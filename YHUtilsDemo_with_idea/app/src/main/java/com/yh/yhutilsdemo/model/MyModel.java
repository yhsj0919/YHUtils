package com.yh.yhutilsdemo.model;

/**
 * Created by LOVE on 2015/4/23 023.
 */
public class MyModel {

    private int id;
    private String name;
    private String data;


    public MyModel() {
    }

    public MyModel(String name, String data) {
        this.name = name;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
