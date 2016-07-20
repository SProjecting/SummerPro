package com.example.eat.resturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eat.R;
import com.example.eat.infomatioin.Dish;

/**
 * Created by 丁东 on 2016/7/18.
 */
public class DishInfo extends Activity {
    private ImageView dishImage;
    private TextView dishName;
    private TextView dishType;
    private TextView dishStar;
    private TextView dishJian;
    private TextView dishPrice;
    private TextView dishSource;

    private Dish dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishinfo);

        dishImage=(ImageView)findViewById(R.id.dishimage);
        dishName=(TextView)findViewById(R.id.dishname);
        dishType=(TextView)findViewById(R.id.dishtype);
        dishStar=(TextView)findViewById(R.id.dishstar);
        dishJian=(TextView)findViewById(R.id.dishjian);
        dishPrice=(TextView)findViewById(R.id.dishprice);
        dishSource=(TextView)findViewById(R.id.dishsource);

        Intent Rintent = getIntent();
        dish = (Dish)Rintent.getSerializableExtra("dish");


        dishName.setText(dish.getName());
        dishType.setText(dish.getFlavour());
        dishStar.setText(dish.getTime());
        dishJian.setText(dish.getAvoid());
        dishPrice.setText(dish.getPrice());
        dishSource.setText(dish.getMajor_material() + "\n" + dish.getMinor_material() + "\n" + dish.getProcess() + "\n" + dish.getTips());
    }
}
