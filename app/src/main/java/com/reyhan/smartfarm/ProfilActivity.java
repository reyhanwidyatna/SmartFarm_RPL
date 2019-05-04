package com.reyhan.smartfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilActivity extends AppCompatActivity {

    private TextView user_profil, user_alamat, user_pendidikan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user_profil = findViewById(R.id.profil_nama);
        user_alamat = findViewById(R.id.profil_alamat);
        user_pendidikan = findViewById(R.id.profil_pendidikan);

    }

}
