package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static android.support.v4.os.LocaleListCompat.create;

public class PostActivity extends AppCompatActivity {

    private EditText postTitle, postDesc;
    private Button post;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Post");
        firebaseAuth = FirebaseAuth.getInstance();

        postTitle = findViewById(R.id.judulPost);
        postDesc = findViewById(R.id.descPost);
        post = findViewById(R.id.postBtn);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPost();
            }
        });
    }

    private void startPost() {
        final String tittle_val = postTitle.getText().toString().trim();
        final String desc_val = postDesc.getText().toString().trim();
        FirebaseUser currentuser = firebaseAuth.getCurrentUser();
        if (currentuser != null){
            if (!TextUtils.isEmpty(tittle_val) && !TextUtils.isEmpty(desc_val)){

                DatabaseReference postdb = mDatabase.push();
                postdb.child("judul").setValue(tittle_val);
                postdb.child("desc").setValue(desc_val);
                postdb.child("uid").setValue(currentuser);

                Toast.makeText(this, "Posting berhasil...", Toast.LENGTH_LONG).show();
                Intent postintent = new Intent(PostActivity.this, SetActivity.class);
                startActivity(postintent);
                create();

            }else {
                Toast.makeText(this, "Posting gagal...", Toast.LENGTH_LONG).show();
            }
        }
    }

}
