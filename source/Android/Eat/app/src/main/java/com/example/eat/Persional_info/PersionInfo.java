package com.example.eat.Persional_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 丁东 on 2016/7/14.
 */
public class PersionInfo extends AppCompatActivity {
    private Button next;
    private Person person;

    private TextView nname;
    private TextView name;
    private TextView phone;
    private TextView address;
    private TextView email;
    private TextView prefer;
    private TextView sign;
    private Map m1 = new HashMap();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persional_info);

        getinfo();
        PD();

        nname = (TextView)findViewById(R.id.infonname);
        nname.setText(person.getName());
        name = (TextView)findViewById(R.id.infoname);
        name.setText(person.getName());
        phone = (TextView)findViewById(R.id.infophone);
        phone.setText(person.getPhonenum());
        address = (TextView)findViewById(R.id.infoaddress);
        String address1;
        address1 = (String)m1.get(person.getAddress());

        address.setText(address1);
        email = (TextView)findViewById(R.id.infoemail);
        email.setText(person.getEmail());
        prefer = (TextView)findViewById(R.id.infoprefer);
        prefer.setText(person.getPrefer());
        sign = (TextView)findViewById(R.id.infosign);
        sign.setText(person.getSign());


        next=(Button)findViewById(R.id.persion_info_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PersionInfo.this,PersionInfoG.class);
                intent.putExtra("per", person);
                startActivity(intent);
            }
        });
    }

    public void getinfo() {
        String url = User.getURL() + "/getinfo";//URL地址
        String post = "uname=" + User.getName();//请求内容

        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String str = th.getResult().replace("null", "");
        getPerson(th.getResult());
    }

    public void getPerson(String str) {
        System.out.println("这是得到的请求  " + str );
        person = new Person();
        person.setName(User.getName());
        try {
            JSONArray jsonArray = new JSONArray(str);
//            System.out.print("等等  " + jsonArray.length()+ "  asdsadas  "+ jsonArray.getJSONObject(0));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject job = jsonArray.getJSONObject(i);
                person.setid(job.getInt("id"));
                person.setAddress(job.optString("province"));
                person.setPrefer(job.optString("prefer"));
                person.setEmail(job.optString("e_mail"));
                person.setSign(job.optString("sign"));
                person.setPhonenum(job.optString("phone"));

                System.out.print("个人信息" + person.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
