package com.example.fa.practica3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fa.practica3.Adapters.CuponDescAdapter;
import com.example.fa.practica3.Models.Cupones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuponDescActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Cupones> cupones_list1=new ArrayList<Cupones>();
    RequestQueue requestQueue;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupon_desc);
        context = CuponDescActivity.this;
        requestQueue = Volley.newRequestQueue(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewListCupones);
        load_cupones();
    }

    private void load_cupones()
    {
        //String url = "http://"+Access.getDireccion()+"/api/clientes/"+Access.getIdCliente()+"/cupones";
        String url  = "http://"+Access.getDireccion()+"/api/cupon/";
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
                        Toast.makeText(CuponDescActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
                cupones_list1.add(new Cupones(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description"),
                        jsonObject.getString("start_date"),
                        jsonObject.getString("end_date"),
                        jsonObject.getInt("percentage")
                ));
                list.add(jsonObject.getString("name"));
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        recyclerView.setAdapter(new CuponDescAdapter(CuponDescActivity.this, cupones_list1));
        recyclerView.setLayoutManager(new LinearLayoutManager(CuponDescActivity.this));
    }

}
