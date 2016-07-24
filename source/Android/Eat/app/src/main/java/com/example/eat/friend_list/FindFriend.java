package com.example.eat.friend_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gust on 16/7/20.
 */
public class FindFriend extends AppCompatActivity {

    private Button findbtn;
    private Button addfriend;
    private EditText fname;
    private ImageView head;
    private TextView uname;
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friend);

        findbtn = (Button)findViewById(R.id.find_btn);
        addfriend = (Button)findViewById(R.id.add_btn);
        fname = (EditText)findViewById(R.id.find_name);
        head = (ImageView)findViewById(R.id.find_friend_image);
        uname = (TextView)findViewById(R.id.friend_name);
        head.setImageResource(R.drawable.user);

        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person = new Person();
                int flag = search();
                if (flag == 1) {
                    Toast.makeText(FindFriend.this, "已找到 ", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(FindFriend.this, "未找到 ", Toast.LENGTH_LONG).show();
                }
            }
        });

        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = addfriend();
                if (flag == 1) {
                    Toast.makeText(FindFriend.this, "添加成功 ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FindFriend.this, FriendList.class));
                }
                else {
                    Toast.makeText(FindFriend.this, "请重新尝试", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public int search() {
        String url = User.getURL() + "/searchUser";
        String post = "name="+ fname.getText().toString();
        System.out.println(post);
        person.setName(fname.getText().toString());


        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(th.getResult());

        getPerson(th.getResult());

        if (person.getId() != -1) {
            uname.setText(person.getName());
            return 1;
        }
        else {
            return 0;
        }
    }

    public void getPerson(String str) {
        System.out.println("这是得到的请求  " + str );

        //person.setName(User.getName());
        try {
            JSONArray jsonArray = new JSONArray(str);
//            System.out.print("等等  " + jsonArray.length()+ "  asdsadas  "+ jsonArray.getJSONObject(0));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject job = jsonArray.getJSONObject(i);
                person.setid(job.getInt("id"));
//                person.setName(job.getString("name"));
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

    public int addfriend() {
        String url = User.getURL() + "/addFriend";
        String post = "id=" + person.getId() + "&name=" + User.getName();
        System.out.println(post);

        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(th.getResult());
        if (th.getResult().equals("success")) {
            return 1;
        }
        return 0;
    }
}
