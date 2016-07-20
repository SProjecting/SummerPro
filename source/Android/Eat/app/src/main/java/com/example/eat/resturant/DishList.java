package com.example.eat.resturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.eat.R;
import com.example.eat.URL.UThread;
import com.example.eat.infomatioin.Dish;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.Restaurant;
import com.example.eat.personal.CommentAdapter;
import com.example.eat.personal.Comment_record;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丁东 on 2016/7/18.
 */
public class DishList extends Activity {
    private List<Comment_record> dlist=new ArrayList<Comment_record>();
    private Restaurant rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);

        Intent Rintent = getIntent();
        rest = (Restaurant)Rintent.getSerializableExtra("rest");
        getDish();

        initComment_recrod();
        CommentAdapter adapter1=new CommentAdapter(
               DishList.this, R.layout.comment_info_activity,dlist);
        ListView listView1=(ListView)findViewById(R.id.dish_list);
        listView1.setAdapter(adapter1);


        //Bundle b=new Bundle();
        //b= Rintent.getBundleExtra("data");
//        final String resName = Rintent.getStringExtra("resName");

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dish dish = rest.getDish(i);
                Intent Rintent = new Intent(DishList.this,DishInfo.class);
                Rintent.putExtra("dish", dish);
                startActivity(Rintent);
            }
        });
    }
    private void initComment_recrod(){
//        Comment_record c1=new Comment_record("Dishlist", R.mipmap.ic_launcher,"qwerqwerqerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwrqwerqwerqwerqwerqwerqwerqwerqwerqwerqwe");
//        dlist.add(c1);
//        Comment_record c2=new Comment_record("qqq", R.mipmap.ic_launcher,"qwerqwerqwer");
//        dlist.add(c2);
//        Comment_record c3=new Comment_record("eee", R.mipmap.ic_launcher,"恶趣味妇女节奶粉事件地方企鹅佛教iejfv ");
//        dlist.add(c3);
//        Comment_record c4=new Comment_record("rrr", R.mipmap.ic_launcher,"sdv nxvjsdfoipwejpgr卡佛没地方吗的23324343333333333423345234523452345234523452345");
//        dlist.add(c4);
//        Comment_record c5=new Comment_record("ttt", R.mipmap.ic_launcher,"啊受到法律解释呢vihmwircwwevko我，儿童节");
//        dlist.add(c5);
//        Comment_record c6=new Comment_record("yyy", R.mipmap.ic_launcher,"微软，ipowrmwjiweportpmiervtwopertkwverjitwertkvwerigjeivjgrvmeork，长跑冠军佩蓉看vwer");
//        dlist.add(c6);
//        Comment_record c7=new Comment_record("uuu", R.mipmap.ic_launcher,"1345678987654322134567890-0987654342113435678797145646260564*-+");
//        dlist.add(c7);
//        Comment_record c8=new Comment_record("uuu", R.mipmap.ic_launcher,"sdfsdjfm温柔vwiemojcferifwejrivejroiwrmcervwertjwc，片刻我曾认为");
//        dlist.add(c8);
//        Comment_record c9=new Comment_record("iii", R.mipmap.ic_launcher,"rwmejrtwcjir,gerjtcowr9i,ejr9cwj,9je-tjer9jgwerktg-wevrjyieerkgegrkikertvkg");
//        dlist.add(c9);
//        Comment_record c10=new Comment_record("ooo", R.mipmap.ic_launcher,"werotjmv,oigjpcwejtrvo,erjgeprcgjw.;rkfergwogrm.e");
//        dlist.add(c10);
//        Comment_record c11=new Comment_record("ppp", R.mipmap.ic_launcher,"wkec,wrtjw9tjg0e8tw0ie,[rko wopgnpgm,eergk w-0er9t9 0w,eter[vyergevrfujprl5py46ul6pkort");
//        dlist.add(c11);

        for (int i = 0; i < rest.getDishnum(); i++) {
            Dish dish = rest.getDish(i);
            Comment_record cc = new Comment_record(dish.getName(), R.mipmap.ic_launcher, dish.getPrice());
            dlist.add(cc);
        }
    }


    public void getDish() {
        String url="http://10.0.2.2:8001/android/getMenu";
        String post="id=" + rest.getId();

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
                Dish dish = new Dish();
                JSONObject job = jsonArray.getJSONObject(i);
                dish.setId(job.optInt("id"));
                dish.setName(job.optString("name"));
                dish.setIntroduction(job.optString("introduction"));
                dish.setMajor_material(job.optString("major_material"));
                dish.setMinor_material(job.optString("minor_material"));
                dish.setMethod(job.optString("method"));
                dish.setTime(job.optString("time"));
                dish.setProcess(job.optString("process"));
                dish.settype(job.optString("flavour"));
                dish.setAvoid(job.optString("avoid"));
                dish.setPrice(job.optString("price"));
                dish.setTips(job.optString("tips"));

                rest.adddish(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
