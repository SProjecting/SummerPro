package com.example.eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eat.Persional_info.PersionInfo;
import com.example.eat.friend_list.FriendList;
import com.example.eat.infomatioin.Person;
import com.example.eat.infomatioin.User;
import com.example.eat.personal.MainActivity;
import com.example.eat.resturant.ResturantList;

/**
 * Created by 丁东 on 2016/7/13.
 */
public class LeftFragment extends Fragment implements View.OnClickListener {

    private DrawerLayout drawer_layout;
    private Person person;
    // private FragmentManager fManager;
    private TextView name;
    private TextView sign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_left, container, false);
        view.findViewById(R.id.btn_one).setOnClickListener(this);
        view.findViewById(R.id.btn_two).setOnClickListener(this);
        view.findViewById(R.id.btn_three).setOnClickListener(this);
        view.findViewById(R.id.btn_four).setOnClickListener(this);
        view.findViewById(R.id.btn_five).setOnClickListener(this);

        name = (TextView)view.findViewById(R.id.left_name);
        name.setText(User.getName());
        sign = (TextView)view.findViewById(R.id.left_info);
        sign.setText("");
        // fManager = getActivity().getSupportFragmentManager();

        //person = new MainActivity().getPerson();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
//                ContentFragment cFragment1 = new ContentFragment("1.点击了右侧菜单项一",R.color.blue);
//                fManager.beginTransaction().replace(R.id.fly_content,cFragment1).commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("per", person);
                getActivity().startActivity(intent);
                drawer_layout.closeDrawer(Gravity.START);
                break;
            case R.id.btn_two:
//                ContentFragment cFragment2 = new ContentFragment("2.点击了右侧菜单项二",R.color.red);
//                fManager.beginTransaction().replace(R.id.fly_content,cFragment2).commit();
                Intent intent1 = new Intent(getActivity(), ResturantList.class);
                intent1.putExtra("per", person);
                getActivity().startActivity(intent1);
                drawer_layout.closeDrawer(Gravity.START);
                break;
            case R.id.btn_three:
//                ContentFragment cFragment3 = new ContentFragment("3.点击了右侧菜单项三",R.color.yellow);
//                fManager.beginTransaction().replace(R.id.fly_content,cFragment3).commit();
                Intent intent2 = new Intent(getActivity(), FriendList.class);
                intent2.putExtra("per", person);
                getActivity().startActivity(intent2);
                drawer_layout.closeDrawer(Gravity.START);
                break;

            case R.id.btn_four:
//                ContentFragment cFragment3 = new ContentFragment("3.点击了右侧菜单项三",R.color.yellow);
//                fManager.beginTransaction().replace(R.id.fly_content,cFragment3).commit();
                Intent intent3 = new Intent(getActivity(), PersionInfo.class);
                intent3.putExtra("per", person);
                getActivity().startActivity(intent3);
                drawer_layout.closeDrawer(Gravity.START);
                break;

            case R.id.btn_five:
//                ContentFragment cFragment3 = new ContentFragment("3.点击了右侧菜单项三",R.color.yellow);
//                fManager.beginTransaction().replace(R.id.fly_content,cFragment3).commit();
                Intent intent4 = new Intent(getActivity(), CommentPage.class);
                getActivity().startActivity(intent4);
                drawer_layout.closeDrawer(Gravity.START);
                break;
        }
    }

    public void setDrawerLayout(DrawerLayout drawer_layout) {
        this.drawer_layout = drawer_layout;
    }

}