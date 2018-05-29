package com.example.fa.practica3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class perfilUsuario extends AppCompatActivity {

    private final String urlClient="http://"+Access.getDireccion()+"/api/bill/"+Access.getIdCliente();
    private static TextView tvNombre, tvApellidos, tvRfc, tvTelefono, tvCorreo;
    ListView lvDatosCliente;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_perfil_usuario);
        tvNombre = (TextView) findViewById(R.id.textView_nombre);
        tvApellidos = (TextView) findViewById(R.id.textView_apellido);
        tvRfc = (TextView) findViewById(R.id.textView_rfc);
        tvTelefono = (TextView) findViewById(R.id.text);
        tvCorreo = (TextView) findViewById(R.id.textView_correo);
        Toast.makeText(perfilUsuario.this, Access.getIdCliente(), Toast.LENGTH_LONG).show();
        loadClientes();

    }
    private void loadClientes()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlClient, null,
                new Response.Listener<JSONArray>(){

                    public void onResponse(JSONArray response){
                        Toast.makeText(perfilUsuario.this, response.toString(), Toast.LENGTH_SHORT).show();
                        mostrarClient(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(perfilUsuario.this, error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public  void mostrarClient (JSONArray response)
    {
        for (int i=0;i<response.length();i++)
        {
            try
            {
                JSONObject jsonObject =response.getJSONObject(i);
                tvNombre.setText("Nombre :"+jsonObject.getString("name"));
                tvApellidos.setText("Email :"+jsonObject.getString("email"));
                //tvTelefono.setText("Teléfono :"+jsonObject.getString("address"));
                tvRfc.setText("Ciudad :"+jsonObject.getString("city"));
                tvCorreo.setText("Estado :"+jsonObject.getString("state"));
            }
            catch(JSONException e)
            {
                Toast.makeText(perfilUsuario.this, "Esta maaaaaal", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

}
