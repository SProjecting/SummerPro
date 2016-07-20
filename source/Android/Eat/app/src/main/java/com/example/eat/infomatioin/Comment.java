package com.example.eat.infomatioin;

/**
 * Created by Gust on 16/7/20.
 */
public class Comment {

    int id;
    String content;
    String time;
    int star;
    String username;
    int rest_id;


    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public int getRest_id() {
        return rest_id;
    }

    public int getStar() {
        return star;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }


}
