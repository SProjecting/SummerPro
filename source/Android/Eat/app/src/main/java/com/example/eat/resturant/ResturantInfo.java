package com.example.eat.resturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eat.CommentPage;
import com.example.eat.CommentPage_rest;
import com.example.eat.R;
import com.example.eat.infomatioin.Restaurant;

/**
 * Created by 丁东 on 2016/7/18.
 */
public class ResturantInfo extends Activity {
    private ImageView resphoto;
    private TextView resname;
    private TextView restype;
    private TextView resphone;
    private TextView resstar;
    private TextView resaddress;
    private Button viewluns;
    private Button viewdisdes;
    private Restaurant restaurant;
    private Button addcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantinfo);

        resphoto=(ImageView)findViewById(R.id.restphoto);
        resname=(TextView)findViewById(R.id.restname);
        restype=(TextView)findViewById(R.id.resttype);
        resphone=(TextView)findViewById(R.id.restphone);
        resstar=(TextView)findViewById(R.id.reststar);
        resaddress=(TextView)findViewById(R.id.restaddress);
        viewluns=(Button)findViewById(R.id.viewluns);
        viewdisdes=(Button)findViewById(R.id.viewdisdes);
        addcomment = (Button)findViewById(R.id.addcomment);
//        viewdisdes=(Button)findViewById(R.id.viewdisdes);

        Intent Rintent = getIntent();
        restaurant = (Restaurant) Rintent.getSerializableExtra("rest");

        resname.setText(restaurant.getName());
        restype.setText(restaurant.getLabel());
        resphone.setText(restaurant.getPhone());
        resstar.setText(restaurant.getStar());
        resaddress.setText(restaurant.getAddress());

        viewdisdes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Rintent = new Intent(ResturantInfo.this,DishList.class);

                Rintent.putExtra("rest", restaurant);
                startActivity(Rintent);
            }
        });

        viewluns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Rintent = new Intent(ResturantInfo.this,ResturantComment.class);
                Rintent.putExtra("rest", restaurant);
                startActivity(Rintent);
            }
        });

        addcomment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResturantInfo.this, CommentPage_rest.class);
                intent.putExtra("rest", restaurant);
                startActivity(intent);
            }
        });
    }


}
