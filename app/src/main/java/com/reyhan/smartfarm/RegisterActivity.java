package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button buatakun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buatakun = findViewById(R.id.newaccButton);
        buatakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newaccintent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(newaccintent);
            }
        });

    }
}
