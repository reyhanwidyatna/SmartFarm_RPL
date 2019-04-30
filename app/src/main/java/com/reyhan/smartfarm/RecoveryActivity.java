package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryActivity extends AppCompatActivity {

    private TextView email;
    private Button recov;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        firebaseAuth.getInstance();
        email = findViewById(R.id.emailRecov);

        recov = findViewById(R.id.submitButton);
        recov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recintent = new Intent(RecoveryActivity.this, LoginActivity.class);
                startActivity(recintent);
            }
        });
    }
}
