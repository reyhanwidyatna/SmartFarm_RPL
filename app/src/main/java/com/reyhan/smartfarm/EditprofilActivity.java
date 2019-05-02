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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class EditprofilActivity extends AppCompatActivity {

    private EditText nama,alamat,pendidikan;
    private Button buatprofil;
    private ImageView imageProf;
    private DatabaseReference mDatabase;

    static int PReqcode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

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

            }
        });
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
}
