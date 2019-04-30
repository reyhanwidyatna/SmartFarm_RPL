package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    private Button buatakun;
    private EditText username,password,no_hp,email;
    private ProgressBar loading;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        loading = findViewById(R.id.loading);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        no_hp = findViewById(R.id.no_hpEditText);
        email = findViewById(R.id.emailEditText);

        buatakun = findViewById(R.id.newaccButton);
        buatakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Register();
            }
        });
    }

    private void Register(){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String nomor = no_hp.getText().toString().trim();
        String mail = email.getText().toString().trim();

        if (TextUtils.isEmpty(user)){
            Toast.makeText(RegisterActivity.this, "Username tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(RegisterActivity.this, "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(nomor)){
            Toast.makeText(RegisterActivity.this, "No.Hp tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(mail)){
            Toast.makeText(RegisterActivity.this, "Email tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register berhasil", Toast.LENGTH_LONG).show();
                            Intent loginintent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(loginintent);
                        }else {
                            Toast.makeText(RegisterActivity.this, "Register gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
