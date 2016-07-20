package com.example.eat.infomatioin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gust on 16/7/14.
 */
public class Restaurant implements Serializable {
    int id;
    String name;
    String address;
    String phone;
    String email;
    String instroduction;
    String buss_hour;
    String managename;
    String label;
    String star;

    int lunsnum;
    ArrayList<Comment> comments = null;
    ArrayList<Dish> dishs = null;
    int dishnum;

    public Restaurant() {}

    public void setIid(int n) {
        id = n;
    }

    public void setNname(String n) {
        name = n;
    }

    public void setaddress(String n) {
        address = n;
    }

    public void setphone(String n) {
        phone = n;
    }

    public void setemail(String n) { email = n; }

    public void setInstroduction(String n) { instroduction = n; }

    public void setBuss_hour(String n) {    buss_hour = n; }

    public void setManagename(String n) {  managename = n; }

    public void setLabel(String n) { label = n; }

    public void setStar( String n) { star = n; }


    public void adddish(Dish n) {
        if (dishs == null) {
            dishnum = 0;
            dishs = new ArrayList<Dish>();
        }
        dishnum++;
        dishs.add(n);
    }

    public int getDishnum() {
        return dishnum;
    }

    public int getId() {
        return id;
    }

    public int getLunsnum() {
        return lunsnum;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Dish getDish(int in) {
        return dishs.get(in);
    }

    public String getEmail() {
        return email;
    }

    public String getBuss_hour() {
        return buss_hour;
    }

    public String getInstroduction() {
        return instroduction;
    }

    public String getLabel() {
        return label;
    }

    public String getManagename() {
        return managename;
    }

    public String getStar() {
        return star;
    }

    public void addComment(Comment n) {
        if (comments == null) {
            comments = new ArrayList<Comment>();
            lunsnum = 0;
        }

        comments.add(n);
        lunsnum++;
    }

    public Comment getComment(int i) {
        return comments.get(i);
    }

    public int getlunnum() {
        return lunsnum;
    }
}
