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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persional_info);

        getinfo();

        nname = (TextView)findViewById(R.id.infonname);
        nname.setText(person.getName());
        name = (TextView)findViewById(R.id.infoname);
        name.setText(person.getName());
        phone = (TextView)findViewById(R.id.infophone);
        phone.setText(person.getPhonenum());
        address = (TextView)findViewById(R.id.infoaddress);
        address.setText(person.getAddress());
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
        String url = "http://10.0.2.2:8001/android/getinfo";//URL地址
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
            System.out.print("等等  " + jsonArray.length()+ "  asdsadas  "+ jsonArray.getJSONObject(0));
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
}
