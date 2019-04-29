package com.reyhan.smartfarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    String URL_LOGIN="http://192.168.0.13/rest_api/login.php";

    Button register;
    Button lupapassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        username = findViewById(R.id.userEditText);
        password = findViewById(R.id.passEditText);

        login = findViewById(R.id.signinButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUser = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (mUser.isEmpty() || mPass.isEmpty()){
                    username.setError("Username harus diisi");
                    password.setError("Password harus diisi");
                }else {
                    Login(mUser, mPass);
                }
            }
        });
    }
    private void Login(final String username, final String password ){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_LONG).show();
                                Intent loginintent = new Intent(LoginActivity.this, TimelineActivity.class);
                                startActivity(loginintent);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Login berhasil (gagal mendapatkan db)",Toast.LENGTH_LONG).show();
                            Intent loginintent = new Intent(LoginActivity.this, TimelineActivity.class);
                            startActivity(loginintent);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error "+error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}