package com.example.eat.personal;

/**
 * Created by 丁东 on 2016/7/8.
 */
public class Comment_record {
    private String user_name;
   // private int res_id;
   // private int dish_id;
    private int image_id;
    private String comment;
   // private String date;

    public Comment_record(String user_name, int image_id, String comment){
        this.user_name=user_name;
        //this.res_id=res_id;
        //this.dish_id=dish_id;
        this.image_id=image_id;
        this.comment=comment;
       // this.date=date;

    }
    public String getUser_name() { return user_name; }
   // public int getRes_id() { return res_id; }
   // public int getDish_id() { return dish_id; }
    public int getImage_id() { return image_id; }
    public String getComment() { return comment; }
    //public String getDate(){return date;}
}
