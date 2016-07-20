package com.example.eat.register;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eat.R;
import com.example.eat.infomatioin.Person;

/**
 * Created by 丁东 on 2016/7/13.
 */
public class Register1 extends AppCompatActivity {

    private Button Rnext;
    private EditText name;
    private EditText pass;
    private EditText rpass;
    private String rname;
    private String pword;
    private String rpword;
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);

        Rnext = (Button) findViewById(R.id.register1_next);


        Rnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = (EditText) findViewById(R.id.register1_username);
                pass = (EditText) findViewById(R.id.register1_password);
                rpass = (EditText) findViewById((R.id.register1_rpassword));

                System.out.println("阿斯顿发生地方" + name.getText());
                rname = name.getText().toString();
                pword = pass.getText().toString();
                rpword = rpass.getText().toString();

                System.err.println("哈哈放的很舒服" + rname + pword + rpword);
                if (rname.equals("") || pword.equals("") || rpword.equals("")) {
                    Toast.makeText(Register1.this, "用户名或密码为空", Toast.LENGTH_LONG).show();

                    name.setText("");
                    pass.setText("");
                    rpass.setText("");
                }
                else if (!pword.equals(rpword)) {
                    Toast.makeText(Register1.this, "密码不一致", Toast.LENGTH_LONG).show();

                    name.setText("");
                    pass.setText("");
                    rpass.setText("");
                }
                else {
                    person = new Person();
                    person.setName(rname);
                    person.setPassword(pword);

                    Intent intent = new Intent(new Intent(Register1.this, Register2.class));
                    intent.putExtra("per", person);
                    startActivity(intent);
                }
            }
        });
    }
}
