package com.example.eat.infomatioin;

/**
 * Created by Gust on 16/7/18.
 */
public class User {

    static String name;

//    static String url = "http://10.0.2.2:8000/android";
    static String url = "http://121.43.96.8:8000/android";

    public static void setName(String n) {
        name = n;
    }

    public static String getName() {
        return name;
    }

    public static String getURL() {
        return url;
    }
}
