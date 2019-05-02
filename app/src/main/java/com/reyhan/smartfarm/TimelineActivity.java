package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            startActivity(new Intent(TimelineActivity.this, PostActivity.class));
        }
        if (item.getItemId() == R.id.action_news){
            startActivity(new Intent(TimelineActivity.this, SetActivity.class));
        }
        if (item.getItemId() == R.id.action_profil){
            startActivity(new Intent(TimelineActivity.this, EditprofilActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
