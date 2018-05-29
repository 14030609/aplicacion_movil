package com.example.fa.practica3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fa.practica3.Access;
import com.example.fa.practica3.CarritoActivity;
import com.example.fa.practica3.Models.Producto;
import com.example.fa.practica3.Models.ordenCompra;
import com.example.fa.practica3.ProInCompraActivity;
import com.example.fa.practica3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niluxer on 11/12/17.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.ComprasHolder> {

    Context context;
    List<ordenCompra> comprasList = new ArrayList<>();

    public ComprasAdapter(Context context, List<ordenCompra> compraList){
        this.context = context;
        this.comprasList = compraList;
    }

    @Override
    public ComprasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_compras, parent, false);
        ComprasHolder comprasHolder = new ComprasHolder(view);
        return comprasHolder;
    }

    @Override
    public void onBindViewHolder(ComprasHolder holder, int position) {
        ordenCompra compra = comprasList.get(position);
        holder.tvFecha.setText("Fecha de compra: "+compra.getFecha());
        holder.tvSubtotal.setText("Subtotal: $ "+compra.getTotal());
        holder.tvTotal.setText("Total: $ "+compra.getTotal() + "");
    }

    @Override
    public int getItemCount() {
        return comprasList.size();
    }

    class ComprasHolder extends RecyclerView.ViewHolder{

        TextView tvFecha, tvSubtotal, tvTotal;
        public ComprasHolder(View itemView) {
            super(itemView);
            tvFecha = (TextView)itemView.findViewById(R.id.tvFecha);
            tvTotal = (TextView)itemView.findViewById(R.id.tvTotal);
            tvSubtotal = (TextView)itemView.findViewById(R.id.tvSubtotal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    int id = comprasList.get(position).getId();
                    Access.setIdCompraInProducts(Integer.toString(id));
                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Intent intent = new Intent(context, ProInCompraActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}
