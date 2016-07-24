package com.example.eat.personal;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.eat.LeftFragment;
import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Comment;
import com.example.eat.infomatioin.Memont;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.Restaurant;
import com.example.eat.infomatioin.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer_layout;
    private FrameLayout fly_content;
    private View topbar;
    private Button btn_right;
    private LeftFragment fg_left_menu;
    private FragmentManager fManager;
    private List<Comment_record> commentList=new ArrayList<Comment_record>();
    //private Person person;
    private ArrayList<Memont> memonts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);

        GetMemont();

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fly_content = (FrameLayout) findViewById(R.id.fly_content);
        topbar = findViewById(R.id.topbar);
        btn_right = (Button) topbar.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);
        fManager = getSupportFragmentManager();
        fg_left_menu = (LeftFragment) fManager.findFragmentById(R.id.fg_left_menu);

        initComment_recrod();
        CommentAdapter adapter1=new CommentAdapter(
                MainActivity.this,R.layout.comment_info_activity,commentList);
        ListView listView1=(ListView)findViewById(R.id.comment_list);
        listView1.setAdapter(adapter1);


        initViews();
    }
    private void initViews() {
        //设置右面的侧滑菜单只能通过编程来打开
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.LEFT);
        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {
            }
            @Override
            public void onDrawerOpened(View view) {
            }
            @Override
            public void onDrawerClosed(View view) {
                drawer_layout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);
            }
            @Override
            public void onDrawerStateChanged(int i) {
            }
        });

        fg_left_menu.setDrawerLayout(drawer_layout);
    }
    @Override
    public void onClick(View v) {
        drawer_layout.openDrawer(Gravity.LEFT);
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.LEFT);    //解除锁定
    }

    private void initComment_recrod(){
//        Comment_record c1=new Comment_record("comment_list",R.mipmap.ic_launcher,"qwerqwerqerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwrqwerqwerqwerqwerqwerqwerqwerqwerqwerqwe");
//        commentList.add(c1);
//        Comment_record c2=new Comment_record("qqq",R.mipmap.ic_launcher,"qwerqwerqwer");
//        commentList.add(c2);
//        Comment_record c3=new Comment_record("eee",R.mipmap.ic_launcher,"恶趣味妇女节奶粉事件地方企鹅佛教iejfv ");
//        commentList.add(c3);
//        Comment_record c4=new Comment_record("rrr",R.mipmap.ic_launcher,"sdv nxvjsdfoipwejpgr卡佛没地方吗的23324343333333333423345234523452345234523452345");
//        commentList.add(c4);
//        Comment_record c5=new Comment_record("ttt",R.mipmap.ic_launcher,"啊受到法律解释呢vihmwircwwevko我，儿童节");
//        commentList.add(c5);
//        Comment_record c6=new Comment_record("yyy",R.mipmap.ic_launcher,"微软，ipowrmwjiweportpmiervtwopertkwverjitwertkvwerigjeivjgrvmeork，长跑冠军佩蓉看vwer");
//        commentList.add(c6);
//        Comment_record c7=new Comment_record("uuu",R.mipmap.ic_launcher,"1345678987654322134567890-0987654342113435678797145646260564*-+");
//        commentList.add(c7);
//        Comment_record c8=new Comment_record("uuu",R.mipmap.ic_launcher,"sdfsdjfm温柔vwiemojcferifwejrivejroiwrmcervwertjwc，片刻我曾认为");
//        commentList.add(c8);
//        Comment_record c9=new Comment_record("iii",R.mipmap.ic_launcher,"rwmejrtwcjir,gerjtcowr9i,ejr9cwj,9je-tjer9jgwerktg-wevrjyieerkgegrkikertvkg");
//        commentList.add(c9);
//        Comment_record c10=new Comment_record("ooo",R.mipmap.ic_launcher,"werotjmv,oigjpcwejtrvo,erjgeprcgjw.;rkfergwogrm.e");
//        commentList.add(c10);
//        Comment_record c11=new Comment_record("ppp",R.mipmap.ic_launcher,"wkec,wrtjw9tjg0e8tw0ie,[rko wopgnpgm,eergk w-0er9t9 0w,eter[vyergevrfujprl5py46ul6pkort");
//        commentList.add(c11);

        for (int i = 0; i < memonts.size(); i++) {
            Memont mm = memonts.get(i);
            Comment_record cc = new Comment_record(mm.getName(), R.drawable.user, mm.getContent() + "\n "+ mm.getTime());
            commentList.add(cc);
        }

    }


    public void GetMemont() {
        String url = User.getURL() + "/moment";
        String post = "username=" + User.getName();

        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是得到的请求  " + th.getResult());

        JSONSolve(th.getResult());

    }

    public void JSONSolve(String str) {

        memonts = new ArrayList<Memont>();
        try {
            JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++) {
                Memont rest = new Memont();
                JSONObject job = jsonArray.getJSONObject(i);
                rest.setId(job.optInt("id"));
                rest.setContent(job.optString("content"));
                rest.setTime(job.optString("time"));
                rest.setName(job.optString("username"));

                memonts.add(rest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
