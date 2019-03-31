package com.reyhan.smartfarm;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashscreenActivity extends AppCompatActivity {

    private ProgressBar proggresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        proggresBar = findViewById(R.id.progress_bar);
        updateProggres();
    }

    private void updateProggres() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int progress = proggresBar.getProgress();
                proggresBar.setProgress(progress + 10);

                if (progress < 100){
                    updateProggres();
                }
                else {
                    Intent splashintent = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    startActivity(splashintent);
                    finish();
                }
            }
        },300);
    }
}
