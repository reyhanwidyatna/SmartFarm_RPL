package com.reyhan.smartfarm;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class BlogAdapter extends ArrayAdapter<Blog> {

    private Activity context;
    private List<Blog>blogList;

    public BlogAdapter(Activity context, List<Blog>blogList){
        super(context, R.layout.activity_feed,blogList);
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.blog_list_items, null, true);

        TextView judul = listview.findViewById(R.id.post_title);
        TextView desc = listview.findViewById(R.id.post_desc);

        Blog blog = blogList.get(position);
        judul.setText(blog.getJudul());
        desc.setText(blog.getDesc());

        return listview;
    }
}
