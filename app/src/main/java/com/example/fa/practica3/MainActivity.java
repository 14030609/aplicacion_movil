package com.example.fa.practica3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String token, idUser;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;

    private String url = "http://"+Access.getDireccion()+"/api/user/";
    private static EditText username;
    private static EditText password;
    private static Button login_button;
    private static Button signup_button;
    int attempt_counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        //Intent intent = new Intent(getApplicationContext(), MenuUsuario.class);
        //startActivity(intent);

        requestQueue = Volley.newRequestQueue(context);
        LoginButton();
    }
    public void LoginButton(){
        username = (EditText)findViewById(R.id.editText_user);
        password = (EditText)findViewById(R.id.editText_password);
        login_button = (Button)findViewById(R.id.button_login);
        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login();
                    }
                }
        );
    }/*
    public void SignupButton(){
        signup_button = (Button)findViewById(R.id.button_singup);
        signup_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Signup.class);
                            startActivity(intent);

                    }
                }
        );
    }
    */
    private void login() {
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(!response.isEmpty()) {
                            try {
                                Toast.makeText(MainActivity.this, "Username and password is correct", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MenuUsuario.class);
                                startActivity(intent);
                                JSONArray obj = new JSONArray(response);
                                JSONObject jsonObject = obj.getJSONObject(0);
                                idUser = String.valueOf(jsonObject.getInt("id"));
                                Access.setIdCliente(idUser);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                 new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"Username and password is NOT correct",
                        Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("name", username.getText().toString());
                parametros.put("password", password.getText().toString());
                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void getIdCliente(String idU)
    {
        String urlClient = "http://"+Access.getDireccion()+"/api/user/"+idU;
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, urlClient, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    JSONObject jsonObject =response.getJSONObject(0);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,"Error:no recupera id_cliente", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
        requestQueue.add(jsonArrayRequest);
    }
}

