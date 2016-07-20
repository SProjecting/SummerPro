package com.example.eat.infomatioin;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gust on 16/7/14.
 */
public class Person implements Serializable {

    int id = -1;
    String name;
    String password;
    String phonenum;
    String address;
    double livingjing;
    double livingwei;
    String email;
    String prefer;
    String sign;
    String head;
    File header;
    ArrayList<Restaurant> restaurants = null;
    int restnum = 0;

    public Person(){}

    public void setName(String n) {
        name = n;
    }

    public void setPassword(String n) {
        password = n;
    }

    public void setid(int n) {
        id = n;
    }

    public void setPhonenum(String n) {
        phonenum = n;
    }

    public void setAddress(String n) {
        address = n;
    }

    public void setLiving(double jingdu, double weidu) {
        livingjing = jingdu;
        livingwei = weidu;
    }

    public void setEmail(String n) {
        email = n;
    }

    public void setPrefer(String n) {
        prefer = n;
    }

    public void setSign(String n) { sign = n ; }

    public void setHead(String n, File m) {
        head = n;
        header = m;
    }

    public void addRestaurants(Restaurant n) {
        if (restaurants == null) {
            restnum = 0;
            restaurants = new ArrayList<Restaurant>();
        }

        restaurants.add(n);
        restnum++;
    }

    public String getName() {
        return this.name;
    }

    public String getHead() {
        return head;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getPrefer() {
        return prefer;
    }

    public String getSign() {
        return sign;
    }

    public File getHeader() {
        return header;
    }

    public double getLivingjing() {
        return livingjing;
    }

    public double getLivingwei() {
        return livingwei;
    }

    public Restaurant getRestaurant(int in) {
        return restaurants.get(in);
    }

    public int getRestnum() {
        return restnum;
    }
}
