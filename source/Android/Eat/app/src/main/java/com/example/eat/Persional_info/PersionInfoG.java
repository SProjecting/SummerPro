package com.example.eat.Persional_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.MainActivity;

/**
 * Created by 丁东 on 2016/7/14.
 */
public class PersionInfoG extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private String[] city = {"北京", "天津", "上海", "重庆", "河北", "山西", "台湾"
            , "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南"
            , "湖北", "湖南", "广东", "甘肃", "四川", "贵州", "海南", "云南", "青海", "陕西", "广西", "西藏", "宁夏", "新疆", "内蒙", "澳门", "香港"};
    private String[] type = {"甜点饮品", "火锅", "自助餐", "小吃快餐", "东北菜", "川湘菜",
            "西餐", "日韩料理", "烧烤烤肉", "汤粥", "粤港菜", "香锅烤鱼","西北菜","云贵菜","海鲜"
            ,"咖啡酒吧茶馆", "京菜鲁菜", "素食", "江浙菜", "东南亚菜", "新疆菜", "其它美食" };
    private String citychose;
    private String typechose;
    private Button finish;
    private TextView name;
    private EditText email;
    private EditText phone;
    private String pphone;
    private String eemail;
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info_g);

        Intent in = getIntent();
        person = (Person)in.getSerializableExtra("per");

        finish=(Button)findViewById(R.id.person_info_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = (EditText)findViewById(R.id.changeemail);
                phone = (EditText)findViewById(R.id.changephone);
                pphone = phone.getText().toString();
                eemail = email.getText().toString();


                update();

                startActivity(new Intent(PersionInfoG.this, MainActivity.class));
            }
        });
        addtype();
        addcity();
        name = (TextView)findViewById(R.id.Username);
        name.setText(User.getName());

    }
    private void addcity() {
        spinner1 = (Spinner)findViewById(R.id.changeaddress);
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, city);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
//                myTextView.setText("您选择的是："+ adapter.getItem(arg2));
                citychose = adapter1.getItem(arg2);
                System.err.println(citychose);
                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
//                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addtype() {
        spinner2 = (Spinner)findViewById(R.id.changefavorite);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
//                myTextView.setText("您选择的是："+ adapter.getItem(arg2));
                typechose = adapter2.getItem(arg2);
                System.err.println(typechose);
                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
//                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
    }

    public void update() {

        String url = User.getURL() + "/editInfo";//URL地址
        String post = "name=" + person.getName() + "&password=" + person.getPassword()
                + "&email=" + eemail + "&phone=" + pphone +
                "&address=" + citychose + "&prefer=" + typechose +
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
            Toast.makeText(PersionInfoG.this, "修改成功", Toast.LENGTH_LONG).show();
        }
    }

}
