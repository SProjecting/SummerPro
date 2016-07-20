package com.example.eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.MainActivity;
import com.example.eat.register.Register1;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private Person person;
    private EditText username;
    private EditText password;
    private String uname;
    private String pword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = (EditText) findViewById(R.id.user_name);
                password = (EditText) findViewById(R.id.password);
                uname = username.getText().toString();
                pword = password.getText().toString();
                int flag = ifexiting();

                //int flag = 1;
                if (flag == 0) {

                    Toast.makeText(LoginActivity.this, "用户名和密码不正确", Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                } else {

                    username.setText("");
                    password.setText("");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Register1.class));
            }
        });
    }

    public int ifexiting() {

        String url = "http://10.0.2.2:8001/android/login";
        String post = "username=" + uname + "&password=" + pword;
        //person.setName(uname);
        //person.setPassword(pword);

        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是得到的请求  " + th.getResult());

        if (th.getResult().equals("sucess")) {
            User.setName(uname);
            return 1;
        }
        return 0;
    }

//    public void getPerson(String str) {
//
//        try {
//            JSONArray jsonArray = new JSONArray(str);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject job = jsonArray.getJSONObject(i);
//                person.setid(job.getInt("id"));
//                person.setAddress(job.getString("address"));
//                person.setPrefer(job.getString("prefer"));
//                person.setEmail(job.getString("email"));
//                person.setSign(job.getString("sign"));
//                person.setPhonenum(job.getString("phone"));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
