package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimelineActivity extends AppCompatActivity {

    private FloatingActionButton addPostBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        firebaseAuth = FirebaseAuth.getInstance();

        addPostBtn = findViewById(R.id.add_post_btn);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newPostIntent = new Intent(TimelineActivity.this, PostActivity.class);
                startActivity(newPostIntent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout_btn) {
            logOut();
        }
        if (item.getItemId() == R.id.action_news){
            startActivity(new Intent(TimelineActivity.this, SetActivity.class));
        }
        if (item.getItemId() == R.id.action_profil){

            startActivity(new Intent(TimelineActivity.this, ProfilActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void logOut() {

        firebaseAuth.signOut();
        Toast.makeText(this, "Sign Out Berhasil", Toast.LENGTH_LONG).show();
        Intent intentlogout = new Intent(this, LoginActivity.class);
        startActivity(intentlogout);

    }
}
