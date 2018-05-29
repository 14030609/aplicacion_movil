package com.example.fa.practica3.Adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fa.practica3.Models.Producto;
import com.example.fa.practica3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niluxer on 11/12/17.
 */

public class ProductoInCompraAdapter extends RecyclerView.Adapter<ProductoInCompraAdapter.ProductHolder> {

    Context context;
    List<Producto> productList = new ArrayList<>();
    static List<Producto> productListSelect = new ArrayList<>();
    public ProductoInCompraAdapter(Context context, List<Producto> productList){
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Producto product = productList.get(position);
        holder.itemTitle.setText(product.getName());
        holder.itemPrice.setText("$ "+product.getPrice());
        //holder.itemDetail.setText(product.getDescripcion() + "");

        Picasso.with(context).load(product.getImage()).into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{

        TextView itemTitle, itemDetail, itemPrice;
        ImageView itemImage;
        public ProductHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);
            itemPrice = (TextView)itemView.findViewById(R.id.item_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                        Snackbar.make(v, "Has agregado: " + productList.get(position).getName(),
                                Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                }
            });
        }
    }
}
