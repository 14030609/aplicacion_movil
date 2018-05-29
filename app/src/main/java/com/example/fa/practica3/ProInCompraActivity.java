package com.example.fa.practica3;

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
import com.example.fa.practica3.Adapters.ProductAdapter;
import com.example.fa.practica3.Models.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProInCompraActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Producto> product_list1=new ArrayList<Producto>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pro_in_compra);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewListaProdInCompra);
        load_productos();
    }

    private void load_productos()
    {
        String url = "http://"+Access.getDireccion()+"/api/order_detail/"+Access.getIdCompraInProducts();
        RequestQueue requestQueue= Volley.newRequestQueue(ProInCompraActivity.this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(ProInCompraActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                genera_lista(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ProInCompraActivity.this, "Error al obtener p in compra", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        String auth = "Bearer " + Access.getToken();
                        headers.put("Authorization", auth);
                        return headers;
                    }};
        requestQueue.add(jsonArrayRequest);
    }
    public void genera_lista(JSONArray response)
    {
        String imagen= "http://"+Access.getDireccion()+"/imgProducts/";
        List<String> list =new ArrayList<String>();
        for (int i=0;i<response.length();i++)
        {
            try
            {
                JSONObject jsonObject =response.getJSONObject(i);
                product_list1.add(new Producto(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getDouble("price"),
                        jsonObject.getString("image")
                ));
                list.add(jsonObject.getString("nombre"));
                System.out.println(imagen+jsonObject.getString("imagen"));
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        recyclerView.setAdapter(new ProductAdapter(ProInCompraActivity.this, product_list1));
        recyclerView.setLayoutManager(new LinearLayoutManager(ProInCompraActivity.this));
    }

}
