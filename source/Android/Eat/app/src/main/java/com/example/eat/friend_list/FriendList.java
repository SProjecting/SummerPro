package com.example.eat.friend_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.eat.LeftFragment;
import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.Restaurant;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.CommentAdapter;
import com.example.eat.personal.Comment_record;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 丁东 on 2016/7/14.
 */
public class FriendList extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawer_layout;
    private FrameLayout fly_content;
    private View topbar;
    private Button btn_right;
    private LeftFragment fg_left_menu;
    private FragmentManager fManager;
    private List<Comment_record> flist = new ArrayList<Comment_record>();
    private ArrayList<Person> friend;
    private Button search;
    private Map m1 = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frient_list_activity);
        drawer_layout = (DrawerLayout) findViewById(R.id.friend_drawer_layout);
        fly_content = (FrameLayout) findViewById(R.id.fly_content);
        topbar = findViewById(R.id.topbar);
        btn_right = (Button) topbar.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);
        fManager = getSupportFragmentManager();
        fg_left_menu = (LeftFragment) fManager.findFragmentById(R.id.fg_left_menu);
        search = (Button)findViewById(R.id.friend_list_find);



        getfriend();

        initComment_recrod();
        CommentAdapter adapter1 = new CommentAdapter(
                FriendList.this, R.layout.comment_info_activity, flist);
        ListView listView1 = (ListView) findViewById(R.id.friend_list);
        listView1.setAdapter(adapter1);

        initViews();


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FriendList.this, FindFriend.class));
            }
        });
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

    private void initComment_recrod() {
//        Comment_record c1=new Comment_record("friendlist",R.mipmap.ic_launcher,"qwerqwerqerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwrqwerqwerqwerqwerqwerqwerqwerqwerqwerqwe");
//        flist.add(c1);
//        Comment_record c2=new Comment_record("qqq",R.mipmap.ic_launcher,"qwerqwerqwer");
//        flist.add(c2);
//        Comment_record c3=new Comment_record("eee",R.mipmap.ic_launcher,"恶趣味妇女节奶粉事件地方企鹅佛教iejfv ");
//        flist.add(c3);
//        Comment_record c4=new Comment_record("rrr",R.mipmap.ic_launcher,"sdv nxvjsdfoipwejpgr卡佛没地方吗的23324343333333333423345234523452345234523452345");
//        flist.add(c4);
//        Comment_record c5=new Comment_record("ttt",R.mipmap.ic_launcher,"啊受到法律解释呢vihmwircwwevko我，儿童节");
//        flist.add(c5);
//        Comment_record c6=new Comment_record("yyy",R.mipmap.ic_launcher,"微软，ipowrmwjiweportpmiervtwopertkwverjitwertkvwerigjeivjgrvmeork，长跑冠军佩蓉看vwer");
//        flist.add(c6);
//        Comment_record c7=new Comment_record("uuu",R.mipmap.ic_launcher,"1345678987654322134567890-0987654342113435678797145646260564*-+");
//        flist.add(c7);
//        Comment_record c8=new Comment_record("uuu",R.mipmap.ic_launcher,"sdfsdjfm温柔vwiemojcferifwejrivejroiwrmcervwertjwc，片刻我曾认为");
//        flist.add(c8);
//        Comment_record c9=new Comment_record("iii",R.mipmap.ic_launcher,"rwmejrtwcjir,gerjtcowr9i,ejr9cwj,9je-tjer9jgwerktg-wevrjyieerkgegrkikertvkg");
//        flist.add(c9);
//        Comment_record c10=new Comment_record("ooo",R.mipmap.ic_launcher,"werotjmv,oigjpcwejtrvo,erjgeprcgjw.;rkfergwogrm.e");
//        flist.add(c10);
//        Comment_record c11=new Comment_record("ppp",R.mipmap.ic_launcher,"wkec,wrtjw9tjg0e8tw0ie,[rko wopgnpgm,eergk w-0er9t9 0w,eter[vyergevrfujprl5py46ul6pkort");
//        flist.add(c11);
        PD();
        for (int i = 0; i < friend.size(); i++) {
            Person p = friend.get(i);
            String address;
            if (p.getAddress() == null) {
                address = "";
            }
            else {
                address = (String)m1.get(p.getAddress());
            }
            Comment_record c = new Comment_record(p.getName(), R.drawable.user, p.getPhonenum() + "  " + address );
            flist.add(c);
        }
    }


    public void getfriend() {
        String url = User.getURL() + "/getFriendList";
        String post = "name=" + User.getName();
        System.out.println(post);
        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(th.getResult());

        JSONSolve(th.getResult());

    }

    public void JSONSolve(String str) {

        friend = new ArrayList<Person>();

        try {
            JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++) {
                Person person = new Person();
                JSONObject job = jsonArray.getJSONObject(i);
                person.setid(job.optInt("id"));
                person.setName(job.optString("username"));
                person.setAddress(job.optString("province"));
                person.setPrefer(job.optString("prefer"));
                person.setEmail(job.optString("e_mail"));
                person.setSign(job.optString("sign"));
                person.setPhonenum(job.optString("phone"));
                friend.add(person);
                System.out.print("个人信息" + person.getId());
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
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

