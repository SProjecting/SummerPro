package com.example.eat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Comment;
import com.example.eat.infomatioin.Memont;
import com.example.eat.infomatioin.Restaurant;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 丁东 on 2016/7/20.
 */
public class CommentPage_rest extends Activity {
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private Button takePhoto;
    private ImageView picture;
    private Uri imageUri;
    private Comment comment;
    private EditText editText;
    private String[] star = {"1", "2" , "3", "4", "5"};
    private Spinner spinner1;
    private ArrayAdapter<String> adapter;
    private String starchose;
    private Restaurant rest;
    private Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page_rest);

        Intent in = getIntent();
        rest = (Restaurant)in.getSerializableExtra("rest");

        getStar();
        takePhoto = (Button) findViewById(R.id.take_photo1);
        picture = (ImageView) findViewById(R.id.picture1);
        editText = (EditText) findViewById(R.id.edit_text_rest);
        commit = (Button)findViewById(R.id.finishMenu_rest);

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment = new Comment();
                comment.setContent(editText.getText().toString());
                int i = Commit();
                if (i == 1) {
                    Toast.makeText(CommentPage_rest.this, "评论成功", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CommentPage_rest.this, MainActivity.class));
                }
                else {
                    Toast.makeText(CommentPage_rest.this, "重新尝试", Toast.LENGTH_LONG).show();
                }

            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建File对象，用于存储拍照后的图片
                File outputImage = new File(Environment.
                        getExternalStorageDirectory(), "tempImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO); // 启动相机程序
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver() .openInputStream(imageUri));
                         picture.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                         } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    public int Commit() {

        String url = "http://10.0.2.2:8001/android/addComment";
        String post = "username=" + User.getName() + "&content=" + comment.getContent()
                + "&restaurant_id=" + rest.getId() + "&star=" + starchose;

        System.out.println(post);
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
//            User.setName(uname);
            return 1;
        }
        return 0;
    }

    public void getStar() {
        spinner1 = (Spinner)findViewById(R.id.reststar);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, star);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
//                myTextView.setText("您选择的是："+ adapter.getItem(arg2));
                starchose = adapter.getItem(arg2);
                System.err.println(starchose);
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
