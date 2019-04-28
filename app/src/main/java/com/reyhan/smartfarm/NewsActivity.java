package com.reyhan.smartfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {

    TextView textView;
    TextView textNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        textView = findViewById(R.id.textitem);
        textNews = findViewById(R.id.textnews);

        String string = getIntent().getStringExtra("Listviewclickvalue");
        String stringnews = getIntent().getStringExtra("Listnewsclickvalue");

        textView.setText(string);
        textNews.setText(stringnews);
    }
}
