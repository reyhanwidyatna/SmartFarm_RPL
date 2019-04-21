package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecoveryActivity extends AppCompatActivity {

    Button recov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        recov = findViewById(R.id.submitButton);

        recov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recoverIntent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(recoverIntent);
            }
        });
    }
}
