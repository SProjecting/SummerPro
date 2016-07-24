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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Memont;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 丁东 on 2016/7/20.
 */
public class CommentPage extends Activity {
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private Button takePhoto;
    private ImageView picture;
    private Uri imageUri;
    private Memont memont;
    private EditText editText;
    private Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page);

        takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);

        commit = (Button)findViewById(R.id.finishMenu);

        commit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editText = (EditText) findViewById(R.id.edit_text);
                memont = new Memont();
                memont.setContent(editText.getText().toString());
                int i = Commit();
                if (i == 1) {
                    Toast.makeText(CommentPage.this, "分享成功", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CommentPage.this, MainActivity.class));
                }
                else {
                    Toast.makeText(CommentPage.this, "重新尝试", Toast.LENGTH_LONG).show();
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

        String url = User.getURL() + "/publish";
        String post = "username=" + User.getName() + "&content=" + memont.getContent();
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

}
