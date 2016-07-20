package com.example.eat.resturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Comment;
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
 * Created by 丁东 on 2016/7/20.
 */
public class ResturantComment extends Activity {
    private List<Comment_record> flist = new ArrayList<Comment_record>();
    private Restaurant rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resturant_comment);

        Intent in = getIntent();
        rest = (Restaurant)in.getSerializableExtra("rest");
        GetRest();

        initComment_recrod();

        CommentAdapter adapter1 = new CommentAdapter(
                ResturantComment.this, R.layout.comment_info_activity, flist);
        ListView listView1 = (ListView) findViewById(R.id.res_comment);
        listView1.setAdapter(adapter1);

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

        for (int i = 0; i < rest.getlunnum(); i++) {
            Comment cc = rest.getComment(i);
            Comment_record c = new Comment_record(cc.getUsername() + " " + cc.getStar(), R.mipmap.ic_launcher, cc.getContent());
            flist.add(c);
        }
    }


    public void GetRest() {
        String url = "http://10.0.2.2:8001/android/getComment";
        String post = "id=" + rest.getId();

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

//        person = new Person();
        try {
            JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++) {
                Comment r = new Comment();
                JSONObject job = jsonArray.getJSONObject(i);
                r.setId(job.optInt("id"));
                r.setContent(job.optString("content"));
                r.setRest_id(job.optInt("restaurant_id"));
                r.setStar(job.optInt("star"));
                r.setTime(job.optString("time"));
                r.setUsername(job.optString("username"));

                rest.addComment(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
