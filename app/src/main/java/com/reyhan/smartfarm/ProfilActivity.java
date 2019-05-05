package com.reyhan.smartfarm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {

    private TextView user_profil, user_alamat, user_pendidikan;
    private DatabaseReference databaseReference;
    private TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user_profil = findViewById(R.id.profil_nama);
        user_alamat = findViewById(R.id.profil_alamat);
        user_pendidikan = findViewById(R.id.profil_pendidikan);
        button = findViewById(R.id.sync_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Profil");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String nama = dataSnapshot.child("nama").getValue().toString();
                        String alamat = dataSnapshot.child("alamat").getValue().toString();
                        String pendidikan = dataSnapshot.child("pendidikan").getValue().toString();
                        user_profil.setText(nama);
                        user_alamat.setText(alamat);
                        user_pendidikan.setText(pendidikan);
                        button.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
