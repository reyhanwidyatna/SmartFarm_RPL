package com.reyhan.smartfarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    public List<Blog> blog_list;
    public Context context;

    private DatabaseReference firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    public BlogAdapter(List<Blog> blog_list){
        this.blog_list = blog_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blog_list_items, viewGroup, false);

        context = viewGroup.getContext();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Post");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.setIsRecyclable(false);
        String blogPostId = blog_list.get(i).BlogPostId;
        String currentUserId = firebaseAuth.getCurrentUser().getUid();

        final String desc_data = blog_list.get(i).getDesc();
        viewHolder.setDescText(desc_data);

        String title_data = blog_list.get(i).getJudul();
        viewHolder.setTitleText(title_data);

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String judul = dataSnapshot.getValue().toString();
                    String desc = dataSnapshot.getValue().toString();

                    viewHolder.setTitleText(judul);
                    viewHolder.setDescText(desc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return blog_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView desc;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDescText (String descText){
            desc = mView.findViewById(R.id.post_desc);
            desc.setText(descText);
        }

        public void setTitleText (String titleText){
            title = mView.findViewById(R.id.post_title);
            title.setText(titleText);
        }
    }

}
