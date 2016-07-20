package com.example.eat.register;

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

import com.example.eat.R;
import com.example.eat.infomatioin.Person;

/**
 * Created by 丁东 on 2016/7/13.
 */
public class Register2 extends AppCompatActivity {
    private Button Rnext;
    private Spinner spinner1;
    private Spinner spinner2;
    private TextView mytextview;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private EditText rphone;
    private EditText remail;

    private String[] city = {"北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "台湾省"
            , "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省"
            , "湖北省", "湖南省", "广东省", "甘肃省", "四川省", "贵州省", "海南省", "云南省", "青海省",
            "陕西省", "广西壮族自治区", "西藏自治区 ", "宁夏回族自治区", "新疆维吾尔自治区",
            "内蒙古自治区 ", "澳门特别行政区 ", "香港特别行政区 "};
    private String[] type = {"甜点饮品", "火锅", "自助餐", "小吃快餐", "东北菜", "川湘菜",
            "西餐", "日韩料理", "烧烤烤肉", "汤粥", "粤港菜", "香锅烤鱼", "西北菜", "云贵菜", "海鲜"
            , "咖啡酒吧茶馆", "京菜鲁菜", "素食", "江浙菜", "东南亚菜", "新疆菜", "其它美食"};


    private String citychose;
    private String typechose;
    private Person person;
    private String phone;
    private String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);

        addtype();
        addcity();

        Rnext = (Button) findViewById(R.id.register2_next);


        Intent pro = getIntent();
        person = (Person) pro.getSerializableExtra("per");

        Rnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rphone = (EditText) findViewById(R.id.register2_phone);
                remail = (EditText) findViewById(R.id.register2_email);
                phone = rphone.getText().toString();
                email = remail.getText().toString();


                person.setPhonenum(phone);
                person.setEmail(email);
                person.setPrefer(typechose);
                person.setAddress(citychose);

                Intent intent = new Intent(Register2.this, Register3.class);
                intent.putExtra("per", person);
                startActivity(intent);
            }
        });


    }

    private void addcity() {

        spinner1 = (Spinner) findViewById(R.id.register2_address);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
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
        spinner2 = (Spinner) findViewById(R.id.register2_favorite);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
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
}
