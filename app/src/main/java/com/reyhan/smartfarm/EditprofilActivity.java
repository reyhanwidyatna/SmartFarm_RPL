package com.reyhan.smartfarm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.support.v4.os.LocaleListCompat.create;

public class EditprofilActivity extends AppCompatActivity {

    private EditText nama,alamat,pendidikan;
    private Button buatprofil,gotoprofil;
    private ImageView imageProf;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    static int PReqcode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Profil");

        nama = findViewById(R.id.namaEditText);
        alamat = findViewById(R.id.alamatEditText);
        pendidikan = findViewById(R.id.pendidikanEditText);

        imageProf = findViewById(R.id.imageProfile);
        imageProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22){
                    checkAndRequestPerm();
                }else {
                    openGallery();
                }

            }
        });


        buatprofil = findViewById(R.id.submitProfil);
        buatprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProfil();
            }
        });
    }

    private void startProfil() {
        String nama_user = nama.getText().toString();
        String alamat_user = alamat.getText().toString();
        String pendidikan_user = pendidikan.getText().toString();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            if (!TextUtils.isEmpty(nama_user) && !TextUtils.isEmpty(alamat_user) && !TextUtils.isEmpty(pendidikan_user)){

                DatabaseReference postdb = mDatabase.push();
                postdb.child("nama").setValue(nama_user);
                postdb.child("alamat").setValue(alamat_user);
                postdb.child("pendidikan").setValue(pendidikan_user);
                postdb.child("uid").setValue(user);

                Toast.makeText(this, "Profil berhasil dibuat...", Toast.LENGTH_SHORT).show();
                Intent postintent = new Intent(EditprofilActivity.this, ProfilActivity.class);
                startActivity(postintent);
                create();

            }
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESTCODE);
    }

    private void checkAndRequestPerm(){

        if (ContextCompat.checkSelfPermission(EditprofilActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(EditprofilActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(EditprofilActivity.this, "Please accept",Toast.LENGTH_LONG).show();

            }else {
                ActivityCompat.requestPermissions(EditprofilActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqcode);
            }
        }
        else {
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){
            pickedImgUri = data.getData();
            imageProf.setImageURI(pickedImgUri);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference user = mDatabase.getDatabase().getReference().child("Profil");

        if (user != null){
            startProfil();
        }else {
            Intent newintent = new Intent(EditprofilActivity.this, TimelineActivity.class);
            startActivity(newintent);
        }

    }
}
