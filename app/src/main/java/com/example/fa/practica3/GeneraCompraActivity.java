package com.example.fa.practica3;

import android.content.Context;
import android.content.Intent;
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
import com.example.fa.practica3.Adapters.CuponDescAdapter;
import com.example.fa.practica3.Adapters.MetodoPagoAdapter;
import com.example.fa.practica3.Adapters.ProductAdapter;
import com.example.fa.practica3.Models.Cupones;
import com.example.fa.practica3.Models.MetodoPago;
import com.example.fa.practica3.Models.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneraCompraActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    private String idCompra;
    private CarritoActivity productos = new CarritoActivity();
    private String url = "http://"+Access.getDireccion()+"/api/order/";
    private List<Producto> p = productos.getProducts();
    List<Producto> productosComprar = ProductAdapter.getProductListSelect();
    int num=productosComprar.size();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_compra);
        context = GeneraCompraActivity.this;
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

        comprar(MetodoPagoAdapter.getIdPago(),num);
        //Intent intent = new Intent(context, NuevosProductosActivity.class);
        //context.startActivity(intent);


    }

    public double getSubtotal()
    {
        double subTotal = 0;
        for (Producto item: productosComprar)
        {
            subTotal = subTotal + item.getPrice();
            num++;
        }
        return subTotal;
    }
    public void comprar( final int id_metodopago,final int num)
    {
        final double subTotal = getSubtotal();
        final double total  = subTotal - (subTotal*CuponDescAdapter.get_id().getPercentage()/100);
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        insertProducts(response);

                    }
                },  new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(GeneraCompraActivity.this,"Username and password is NOT correct",
                        Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("order_date", getFecha().toString());
                parametros.put("id_customer", Access.getIdCliente());
                parametros.put("id_ship", Integer.toString(1));
                parametros.put("subtotal",Double.toString(subTotal));
                parametros.put("description","Venta por internet");
                parametros.put("total",Double.toString(total));
                parametros.put("quantity",String.valueOf(num));
                parametros.put("id_discount_cupon", Integer.toString(CuponDescAdapter.get_id().getId()));
                parametros.put("id_payment_method", Integer.toString(id_metodopago));
                return parametros;
            }
        };

        requestQueue.add(stringRequest);
    }
    public void  insertProducts(final String response)
    {
        final String id_order=response;
        String urlPro = "http://"+Access.getDireccion()+"/api/order/add";

        for (final Producto item: productosComprar)
        {
            stringRequest = new StringRequest(Request.Method.POST, urlPro,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    },  new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(GeneraCompraActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams()  {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("id_order", id_order);
                    parametros.put("id_product", String.valueOf(item.getId()));
                    return parametros;
                }
            };

            requestQueue.add(stringRequest);
        }
        Toast.makeText(GeneraCompraActivity.this, "Se compra se ha realizado con exito", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MenuUsuario.class);
        startActivity(intent);
    }

    private String getFecha()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }


}
