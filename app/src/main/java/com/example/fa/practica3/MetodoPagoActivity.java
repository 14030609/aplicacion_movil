package com.example.fa.practica3;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fa.practica3.Adapters.CuponDescAdapter;
import com.example.fa.practica3.Adapters.MetodoPagoAdapter;
import com.example.fa.practica3.Models.Cupones;
import com.example.fa.practica3.Models.MetodoPago;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetodoPagoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<MetodoPago> metodoPago_list=new ArrayList<MetodoPago>();
    RequestQueue requestQueue;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        context = MetodoPagoActivity.this;
        requestQueue = Volley.newRequestQueue(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewListMetodoPago);
        load_metodosPago();
    }

    private void load_metodosPago()
    {
        String url = "http://"+Access.getDireccion()+"/api/payment/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    public void onResponse(JSONArray response){
                        genera_lista(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(MetodoPagoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
    public void genera_lista(JSONArray response)
    {
        List<String> list =new ArrayList<String>();
        for (int i=0;i<response.length();i++)
        {
            try
            {
                JSONObject jsonObject =response.getJSONObject(i);
                metodoPago_list.add(new MetodoPago(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description")
                ));
                list.add(jsonObject.getString("name"));
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        recyclerView.setAdapter(new MetodoPagoAdapter(MetodoPagoActivity.this, metodoPago_list));
        recyclerView.setLayoutManager(new LinearLayoutManager(MetodoPagoActivity.this));
    }

}
