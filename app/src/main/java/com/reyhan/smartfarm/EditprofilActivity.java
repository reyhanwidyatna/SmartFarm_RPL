package com.reyhan.smartfarm;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditprofilActivity extends AppCompatActivity {

    EditText nama,alamat,pendidikan;
    Button buatprofil;

    private static String URL_PROFIL = "http://192.168.0.13/rest_api/biodata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

        nama = findViewById(R.id.namaEditText);
        alamat = findViewById(R.id.alamatEditText);
        pendidikan = findViewById(R.id.pendidikanEditText);

        buatprofil = findViewById(R.id.submitProfil);
        buatprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfil();
            }
        });
    }

    private void editProfil(){
        final String nama = this.nama.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();
        final String pendidikan = this.pendidikan.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PROFIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("Success");
                            if (success.equals("1")){
                                Toast.makeText(EditprofilActivity.this,"Profil berasil dibuat",Toast.LENGTH_LONG).show();
                                Intent intentprofil = new Intent(EditprofilActivity.this, TimelineActivity.class);
                                intentprofil.putExtra("nama", nama);
                                startActivity(intentprofil);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditprofilActivity.this,"Profil berhasil dibuat" ,Toast.LENGTH_LONG).show();
                            Intent intentprofil = new Intent(EditprofilActivity.this, TimelineActivity.class);
                            intentprofil.putExtra("nama", nama);
                            startActivity(intentprofil);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditprofilActivity.this,"Register Failed",Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("nama", nama);
                params.put("alamat", alamat);
                params.put("pendidikan", pendidikan);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
