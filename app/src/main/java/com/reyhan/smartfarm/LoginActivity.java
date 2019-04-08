package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button login;
    Button register;
    Button lupapassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userEditText);
        password = findViewById(R.id.passEditText);

        
        login = findViewById(R.id.signinButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().equalsIgnoreCase("")){
                    username.setError("Username harus diisi !!!");
                }
                else if (password.getText().toString().trim().equalsIgnoreCase("")){
                    password.setError("Password harus diisi !!!");
                }
                else {
                    Intent loginintent = new Intent(view.getContext(), TimelineActivity.class);
                    view.getContext().startActivity(loginintent);
                }
            }
        });

        register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisintent = new Intent(view.getContext(), RegisterActivity.class);
                view.getContext().startActivity(regisintent);
            }
        });

        lupapassword = findViewById(R.id.lupapasswordButton);
        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recoveryintent = new Intent(view.getContext(), RecoveryActivity.class);
                view.getContext().startActivity(recoveryintent);
            }
        });

    }
}
