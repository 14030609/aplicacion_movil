package com.example.fa.practica3;

import android.content.Intent;
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
import com.example.fa.practica3.Adapters.ComprasAdapter;
import com.example.fa.practica3.Adapters.ProductAdapter;
import com.example.fa.practica3.Models.Producto;
import com.example.fa.practica3.Models.ordenCompra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComprasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<ordenCompra> compras_list1=new ArrayList<ordenCompra>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewListCompras);
        loadCompras();
    }

    private void loadCompras()
    {
        String url = "http://"+Access.getDireccion()+"/api/order/"+Access.getIdCliente();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    public void onResponse(JSONArray response){
                        Toast.makeText(ComprasActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        genera_lista(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(ComprasActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
                compras_list1.add(new ordenCompra(
                        jsonObject.getInt("id"),
                        jsonObject.getString("order_date"),
                        jsonObject.getDouble("total")
                ));
                list.add(jsonObject.getString("name"));
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        recyclerView.setAdapter(new ComprasAdapter(ComprasActivity.this, compras_list1));
        recyclerView.setLayoutManager(new LinearLayoutManager(ComprasActivity.this));
    }

}
