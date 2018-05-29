package com.example.fa.practica3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.fa.practica3.Adapters.CarritoAdapter;
import com.example.fa.practica3.Adapters.ProductAdapter;
import com.example.fa.practica3.Models.Producto;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private static Button back_button, btnComprar;
    List<Producto> seleccionados;
    public List<Producto> getProducts()
    {
        return seleccionados;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewListCarrito);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        seleccionados = ProductAdapter.getProductListSelect();
        adapter = new CarritoAdapter(CarritoActivity.this,seleccionados);
        recyclerView.setAdapter(adapter);

        back_button = (Button)findViewById(R.id.button_regresar);
        back_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        btnComprar = (Button)findViewById(R.id.buttonComprar);
        btnComprar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inicia();
                    }
                }
        );
    }
    public void BackButton(){
        back_button = (Button)findViewById(R.id.button_regresar);
        back_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }
    public void inicia()
    {
        Access.setComprar(true);
        startActivity(new Intent(this, CuponDescActivity.class));
    }

}
