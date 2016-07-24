package com.example.eat.resturant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁东 on 2016/7/15.
 */
public class ResturantList extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawer_layout;
    private FrameLayout fly_content;
    private View topbar;
    private Button btn_right;
    private LeftFragment fg_left_menu;
    private FragmentManager fManager;
    private List<Comment_record> flist = new ArrayList<Comment_record>();
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);
        drawer_layout = (DrawerLayout) findViewById(R.id.resturant_drawer_layout);
        topbar = findViewById(R.id.topbar);
        btn_right = (Button) topbar.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);
        fManager = getSupportFragmentManager();
        fg_left_menu = (LeftFragment) fManager.findFragmentById(R.id.fg_left_menu);

        GetRest();

        initComment_recrod();
        CommentAdapter adapter1 = new CommentAdapter(
                ResturantList.this, R.layout.comment_info_activity, flist);
        ListView listView1 = (ListView) findViewById(R.id.resturant_list);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Comment_record c = flist.get(i);
                Intent Rintent = new Intent(ResturantList.this, ResturantInfo.class);
                Restaurant rest = person.getRestaurant(i);
                Rintent.putExtra("rest", rest);
//                Bundle B = new Bundle();
//                B.putString("resName", c.getUser_name());
//                B.putInt("resImage", c.getImage_id());
//                B.putString("resAddress", c.getComment());
//                Rintent.putExtra("data", B);
                startActivity(Rintent);
            }
        });


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

    private void initComment_recrod() {
//        Comment_record c1 = new Comment_record("resturantList", R.mipmap.ic_launcher, "qwerqwerqerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwrqwerqwerqwerqwerqwerqwerqwerqwerqwerqwe");
//        flist.add(c1);
//        Comment_record c2 = new Comment_record("qqq", R.mipmap.ic_launcher, "qwerqwerqwer");
//        flist.add(c2);
//        Comment_record c3 = new Comment_record("eee", R.mipmap.ic_launcher, "恶趣味妇女节奶粉事件地方企鹅佛教iejfv ");
//        flist.add(c3);
//        Comment_record c4 = new Comment_record("rrr", R.mipmap.ic_launcher, "sdv nxvjsdfoipwejpgr卡佛没地方吗的23324343333333333423345234523452345234523452345");
//        flist.add(c4);
//        Comment_record c5 = new Comment_record("ttt", R.mipmap.ic_launcher, "啊受到法律解释呢vihmwircwwevko我，儿童节");
//        flist.add(c5);
//        Comment_record c6 = new Comment_record("yyy", R.mipmap.ic_launcher, "微软，ipowrmwjiweportpmiervtwopertkwverjitwertkvwerigjeivjgrvmeork，长跑冠军佩蓉看vwer");
//        flist.add(c6);
//        Comment_record c7 = new Comment_record("uuu", R.mipmap.ic_launcher, "1345678987654322134567890-0987654342113435678797145646260564*-+");
//        flist.add(c7);
//        Comment_record c8 = new Comment_record("uuu", R.mipmap.ic_launcher, "sdfsdjfm温柔vwiemojcferifwejrivejroiwrmcervwertjwc，片刻我曾认为");
//        flist.add(c8);
//        Comment_record c9 = new Comment_record("iii", R.mipmap.ic_launcher, "rwmejrtwcjir,gerjtcowr9i,ejr9cwj,9je-tjer9jgwerktg-wevrjyieerkgegrkikertvkg");
//        flist.add(c9);
//        Comment_record c10 = new Comment_record("ooo", R.mipmap.ic_launcher, "werotjmv,oigjpcwejtrvo,erjgeprcgjw.;rkfergwogrm.e");
//        flist.add(c10);
//        Comment_record c11 = new Comment_record("ppp", R.mipmap.ic_launcher, "wkec,wrtjw9tjg0e8tw0ie,[rko wopgnpgm,eergk w-0er9t9 0w,eter[vyergevrfujprl5py46ul6pkort");
//        flist.add(c11);
        for (int i = 0; i < person.getRestnum(); i++) {
            Restaurant rest = person.getRestaurant(i);
            Comment_record cc = new Comment_record(rest.getName(), R.drawable.shangjia, rest.getAddress());
            flist.add(cc);
        }
    }

    public void GetRest() {
        String url = User.getURL() + "/nearby";
        String post = "name=" + User.getName();

        UThread th = new UThread(url, post);//新建线程
        th.start();//启动
        try {
            th.join();//主线程需要等待子线程,于是用 jion() 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是饭店的所有信息  " + th.getResult());

        JSONSolve(th.getResult());

    }

    public void JSONSolve(String str) {

        person = new Person();
        try {
            JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++) {
                Restaurant rest = new Restaurant();
                JSONObject job = jsonArray.getJSONObject(i);
                rest.setIid(job.optInt("id"));
                rest.setNname(job.optString("res_name"));
//                rest.setLabel(job.optString("label"));
                rest.setemail(job.optString("email"));
                rest.setaddress(job.optString("address"));
                rest.setphone(job.optString("phone"));
                rest.setBuss_hour(job.optString("business_hour"));
                rest.setInstroduction(job.optString("introduction"));
                rest.setManagename(job.optString("manager_name"));
                rest.setStar(job.optString("star"));

                person.addRestaurants(rest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
