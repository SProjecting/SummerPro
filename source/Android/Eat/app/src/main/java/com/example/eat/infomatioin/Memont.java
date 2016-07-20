package com.example.eat.infomatioin;

/**
 * Created by Gust on 16/7/20.
 */
public class Memont {
    int id;
    String name;
    String time;
    String content;

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String  getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
