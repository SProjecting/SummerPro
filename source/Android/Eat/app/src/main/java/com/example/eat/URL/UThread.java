package com.example.eat.URL;

/**
 * Created by Gust on 16/7/16.
 */
public class UThread extends Thread implements Runnable {

    String url;
    String post;
    String result;
    public UThread(String u, String p) {
        url = u;
        post = p;
    }
    @Override
    public void run() {
        String str = PostUtils.sendPost(url, post);
        result = str;
        System.out.println("这是得到的请求  " + str);
    }

    public String getResult() {
        return result;
    }
}
