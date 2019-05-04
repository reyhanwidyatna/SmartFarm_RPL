package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button login;
    private TextView register,lupapassword;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.mailEditText);
        password = findViewById(R.id.passEditText);

        register = findViewById(R.id.registerTv);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisintent = new Intent(view.getContext(), RegisterActivity.class);
                view.getContext().startActivity(regisintent);
            }
        });

        lupapassword = findViewById(R.id.lupapasswordTv);
        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recoveryintent = new Intent(view.getContext(), RecoveryActivity.class);
                view.getContext().startActivity(recoveryintent);
            }
        });


        login = findViewById(R.id.signinButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login(){
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Email dan Password harus diisi", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_LONG).show();
                            Intent loginintent = new Intent(LoginActivity.this, EditprofilActivity.class);
                            startActivity(loginintent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Email atau Password salah", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}