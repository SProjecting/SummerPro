package com.example.eat.personal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eat.R;
import com.example.eat.personal.Comment_record;

import java.util.List;

/**
 * Created by 丁东 on 2016/7/8.
 */
public class CommentAdapter extends ArrayAdapter<Comment_record> {
    private int resourceId;
    public CommentAdapter(Context context, int resource, List<Comment_record> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Comment_record comment_record=getItem(position);
        View view;
        if (convertView == null) {
            view=LayoutInflater.from(getContext()).inflate(resourceId,null);
        } else {
            view = convertView;
        }

        ImageView friendImage=(ImageView)view.findViewById(R.id.friend_image);
        TextView friendName = (TextView)view.findViewById(R.id.friend_name);
        TextView commentText=(TextView)view.findViewById(R.id.comment_info);
        friendImage.setImageResource(comment_record.getImage_id());
        friendName.setText(comment_record.getUser_name());
        commentText.setText(comment_record.getComment());
        return view;
    }
}
