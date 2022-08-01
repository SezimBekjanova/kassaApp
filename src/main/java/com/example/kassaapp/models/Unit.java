package com.example.kassaapp.models;

public class Unit {
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public  String toString(){
        return getName();
    }
}
