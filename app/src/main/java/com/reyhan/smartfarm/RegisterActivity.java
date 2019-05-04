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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private Button buatakun;
    private EditText username,password,no_hp,email;
    private ProgressBar loading;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

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
        final String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        final String nomor = no_hp.getText().toString().trim();
        final String mail = email.getText().toString().trim();

        if (TextUtils.isEmpty(user)){
            Toast.makeText(RegisterActivity.this, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(RegisterActivity.this, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(nomor)){
            Toast.makeText(RegisterActivity.this, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(mail)){
            Toast.makeText(RegisterActivity.this, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            DatabaseReference regisdb = databaseReference.push();
                            regisdb.child("username").setValue(user);
                            regisdb.child("nomor").setValue(nomor);
                            regisdb.child("email").setValue(mail);
                            create();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Register gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void create(){
        Toast.makeText(RegisterActivity.this, "Register berhasil", Toast.LENGTH_LONG).show();
        Intent loginintent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginintent);
    }
}
