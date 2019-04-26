package com.reyhan.smartfarm;

import android.content.Intent;
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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button buatakun;
    EditText username,password,no_hp,email;
    ProgressBar loading;

    private static String URL_REGISTER = "http://192.168.0.12/rest_api/create.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        no_hp = findViewById(R.id.no_hpEditText);
        email = findViewById(R.id.emailEditText);

        buatakun = findViewById(R.id.newaccButton);
        buatakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUsers();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    private void registerUsers() {
        loading.setVisibility(View.VISIBLE);

        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String no_hp = this.no_hp.getText().toString().trim();
        final String email = this.email.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String succes = jsonObject.getString("Success");
                        if (succes.equals("1")){
                            Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this,"Register Success" ,Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                    }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("password", password);
                params.put("no_hp", no_hp);
                params.put("email", email);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
