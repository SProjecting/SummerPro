package com.example.eat.infomatioin;

import java.io.Serializable;

/**
 * Created by Gust on 16/7/14.
 */
public class Dish implements Serializable {

    int id;
    String name;
    String price;
    String flavour;
    String major_material;
    String introduction;
    String minor_material;
    String method;
    String time;
    String process;
    String tips;
    String avoid;



    public Dish() {}

    public void setId(int n) {
        id = n;
    }

    public void setName(String n) {
        name = n;
    }

    public void setPrice(String n) {
        price = n;
    }

    public void settype(String n) {
        flavour = n;
    }

    public void setMajor_material(String n) {
        major_material = n;
    }

    public void setIntroduction(String n) {
        introduction = n;
    }

    public void setMinor_material(String n) {
        minor_material = n;
    }

    public void setMethod(String n) {
        method = n;
    }

    public void setProcess(String n) {
        process = n;
    }

    public void setTips(String n) {
        tips = n;
    }

    public void setTime(String n) {
        time = n;
    }

    public void setAvoid(String n) {
        avoid = n;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAvoid() {
        return avoid;
    }

    public String getFlavour() {
        return flavour;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getMajor_material() {
        return major_material;
    }

    public String getMethod() {
        return method;
    }

    public String getMinor_material() {
        return minor_material;
    }

    public String getPrice() {
        return price;
    }

    public String getProcess() {
        return process;
    }

    public String getTime() {
        return time;
    }

    public String getTips() {
        return tips;
    }

}
