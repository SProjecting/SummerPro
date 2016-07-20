package com.example.eat;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by 丁东 on 2016/7/14.
 */
public class BackMenu extends LinearLayout{
    public BackMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.just_back, this);
        Button Back = (Button) findViewById(R.id.btn_back);
        Back.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {

                ((Activity) getContext()).finish();
            }
        });
    }
}
