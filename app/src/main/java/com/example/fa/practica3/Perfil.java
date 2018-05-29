package com.example.fa.practica3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Perfil extends AppCompatActivity {

    private final String urlClient="http://"+Access.getDireccion()+"/api/bill/"+Access.getIdCliente();
    private static TextView tvNombre, tvApellidos, tvRfc, tvTelefono, tvCorreo;
    ListView lvDatosCliente;
    String nombre;
    private static Button back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tvNombre = (TextView) findViewById(R.id.textView_nombre);
        tvApellidos = (TextView) findViewById(R.id.textView_apellido);
        tvRfc = (TextView) findViewById(R.id.textView_rfc);
        tvTelefono = (TextView) findViewById(R.id.text);
        tvCorreo = (TextView) findViewById(R.id.textView_correo);
        //Toast.makeText(Perfil.this, Access.getIdCliente(), Toast.LENGTH_LONG).show();
        loadClientes();
    }
    /*public void BackpButton(){
        back_button = (Button)findViewById(R.id.button_regresar);
        back_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }*/

    private void loadClientes()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(Perfil.this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, urlClient, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(Perfil.this, response.toString(), Toast.LENGTH_SHORT).show();
                                System.out.println(response.toString());
                                mostrarClient(response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Perfil.this,"error al recuperar datos", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("id", Access.getIdCliente().toString());
                        return headers;
                    }};
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
                tvApellidos.setText("Apellidos :"+jsonObject.getString("email"));
                tvRfc.setText("RFC :"+jsonObject.getString("phone"));
                tvTelefono.setText("Teléfono :"+jsonObject.getString("city"));
                tvCorreo.setText("Correo :"+jsonObject.getString("state"));
            }
            catch(JSONException e)
            {
                Toast.makeText(Perfil.this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
