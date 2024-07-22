package com.example.th2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name, desc, date, tinhtrang, congtac;


    public Item() {
    }

    public Item(String name, String desc, String date, String tinhtrang, String congtac) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public Item(int id, String name, String desc, String date, String tinhtrang, String congtac) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getCongtac() {
        return congtac;
    }

    public void setCongtac(String congtac) {
        this.congtac = congtac;
    }
}
