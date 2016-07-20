package com.example.eat.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eat.LoginActivity;
import com.example.eat.R;
import com.example.eat.URL.PostUtils;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Person;
import com.example.eat.personal.MainActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by 丁东 on 2016/7/13.
 */
public class Register3 extends AppCompatActivity {
    private Button Rnext;
    private Map m1 = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register3);

        Rnext=(Button) findViewById(R.id.register3_next);

        Intent in = getIntent();
        final Person person = (Person)in.getSerializableExtra("per");
        Rnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("这是最后" + person.getName());


                String url = "http://10.0.2.2:8001/android/register";//URL地址
                String post = "name=" + person.getName() + "&password=" + person.getPassword()
                        + "&email=" + person.getEmail() + "&phone=" + person.getPhonenum() +
                        "&address=" + getPNum(person.getAddress()) + "&prefer=" +person.getPrefer() +
                        "&sign=" + person.getSign();//请求内容

                UThread th = new UThread(url, post);//新建线程
                th.start();//启动
                try {
                    th.join();//主线程需要等待子线程,于是用 jion() 方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("这是得到的请求  " + th.getResult() );

                if (th.getResult().equals("success")) {
                    startActivity(new Intent(Register3.this, LoginActivity.class));
                }
                else {
                    if (th.getResult().equals("unsuccess")) {
                        Toast.makeText(Register3.this, "用户名已存在", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Register3.this, "注册失败", Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(Register3.this, Register1.class));
                }
            }
        });
    }

    public String getPNum(String str) {
        PD();
        Iterator it = m1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();
            if (en.getValue().equals(str)) {
                System.out.println(en.getKey());
                return (String)en.getKey();
            }
        }
        return "";
    }

    public void PD() {
        m1.put("110000", "北京市");
        m1.put("120000", "天津市");
        m1.put("130000", "河北省");
        m1.put("140000", "山西省");
        m1.put("150000", "内蒙古自治区");
        m1.put("210000", "辽宁省");
        m1.put("220000", "吉林省");
        m1.put("230000", "黑龙江省");
        m1.put("310000", "上海市");
        m1.put("320000", "江苏省");
        m1.put("330000", "浙江省");
        m1.put("340000", "安徽省");
        m1.put("350000", "福建省");
        m1.put("360000", "江西省");
        m1.put("370000", "山东省");
        m1.put("410000", "河南省");
        m1.put("420000", "湖北省");
        m1.put("430000", "湖南省");
        m1.put("440000", "广东省");
        m1.put("450000", "广西壮族自治区");
        m1.put("460000", "海南省");
        m1.put("500000", "重庆市");
        m1.put("510000", "四川省");
        m1.put("520000", "贵州省");
        m1.put("530000", "云南省");
        m1.put("540000", "西藏自治区");
        m1.put("610000", "陕西省");
        m1.put("620000", "甘肃省");
        m1.put("630000", "青海省");
        m1.put("640000", "宁夏回族自治区");
        m1.put("650000", "新疆维吾尔自治区");
        m1.put("710000", "台湾省");
        m1.put("810000", "香港特别行政区");
        m1.put("820000", "澳门特别行政区");

    }

}
