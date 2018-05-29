package com.example.fa.practica3;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fa.practica3.Adapters.ProductAdapter;
import com.example.fa.practica3.Models.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuevosProductosActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    private String urlOrdenProd = "http://"+Access.getDireccion()+"/api/ordenProductos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevos_productos);
        context = NuevosProductosActivity.this;
        requestQueue = Volley.newRequestQueue(context);
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
        List<Producto> productosNuevos = ProductAdapter.getProductListSelect();
        for (Producto item:productosNuevos) {
            ordenProductos(Integer.parseInt(Access.getIdCompra()), item);
            System.out.println("ES ID "+Access.getIdCompra());
        }
    }

    public void  ordenProductos (final int id_compra, final Producto productoN)
    {
        stringRequest = new StringRequest(Request.Method.POST, urlOrdenProd,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(NuevosProductosActivity.this,"¡Productos comprados correctamente!",Toast.LENGTH_SHORT).show();
                    }
                },  new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
                Toast.makeText(NuevosProductosActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("id_producto",Integer.toString(productoN.getId()));
                parametros.put("id_compra", Integer.toString(id_compra));
                parametros.put("cantidad","1");
                return parametros;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String auth = "Bearer " + Access.getToken();
                headers.put("Authorization", auth);
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

}
