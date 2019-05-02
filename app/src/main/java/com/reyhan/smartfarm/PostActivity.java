package com.reyhan.smartfarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton selectImage;
    private EditText postTitle, postDesc;
    private Button post;
    private static final int GALERY_REQUEST = 1;
    private Uri imageUri;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Post");

        selectImage = findViewById(R.id.imagePost);
        postTitle = findViewById(R.id.judulPost);
        postDesc = findViewById(R.id.deskripsiPost);
        post = findViewById(R.id.postButton);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALERY_REQUEST);
            }
        });

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
        if (!TextUtils.isEmpty(tittle_val) && !TextUtils.isEmpty(desc_val) && imageUri != null){
            final StorageReference filepath = mStorage.child("Post_Image").child(imageUri.getLastPathSegment());
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    DatabaseReference newpost = mDatabase.push();
                    newpost.child("tittle").setValue(tittle_val);
                    newpost.child("desc").setValue(desc_val);
                    Intent post = new Intent(PostActivity.this, TimelineActivity.class);
                    startActivity(post);

                }
            });

        }else {
            Toast.makeText(this, "Posting gagal...", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERY_REQUEST && resultCode == RESULT_OK){
            imageUri = data.getData();
            selectImage.setImageURI(imageUri);
        }
    }
}
