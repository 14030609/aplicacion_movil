package com.example.fa.practica3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fa.practica3.Access;
import com.example.fa.practica3.MetodoPagoActivity;
import com.example.fa.practica3.Models.Cupones;
import com.example.fa.practica3.Models.Producto;
import com.example.fa.practica3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class CuponDescAdapter extends RecyclerView.Adapter<CuponDescAdapter.CuponHolder> {

    Context context;
    List<Cupones> cupones = new ArrayList<>();
    static Cupones selectC;

    public CuponDescAdapter(Context context, List<Cupones> cuponesList){
        this.context = context;
        this.cupones = cuponesList;
    }

    @Override
    public CuponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Toast.makeText(context,"ONcREATE",Toast.LENGTH_SHORT);
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_cupones, parent, false);
        CuponHolder cuponHolder = new CuponHolder(view);
        return cuponHolder;
    }

    @Override
    public void onBindViewHolder(CuponHolder holder, int position) {
        Toast.makeText(context,"ONbIEWVIEW",Toast.LENGTH_SHORT);
        Cupones cupon = cupones.get(position);
        holder.tvNombre.setText(cupon.getName());
        holder.tvDescripcion.setText(cupon.getDescription());
        holder.tvDescuento.setText(cupon.getPercentage()+" % " + "");

        //Picasso.with(context).load("http://"+ Access.getDireccion()+"/img/cupon.png").into(holder.imImagen);

    }

    @Override
    public int getItemCount() {
        return cupones.size();
    }

    class CuponHolder extends RecyclerView.ViewHolder{

        ImageView imImagen;
        TextView tvNombre, tvDescripcion, tvDescuento;
        public CuponHolder(View itemView) {
            super(itemView);
            imImagen = (ImageView) itemView.findViewById(R.id.imImagen);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNomCupon);
            tvDescripcion = (TextView)itemView.findViewById(R.id.tvDescripCupon);
            tvDescuento = (TextView)itemView.findViewById(R.id.tvDescCupon);

            if (cupones.size() == 0)
            {
                Access.setComprar(false);
                Access.setCuponSelect(null);
                Intent intent = new Intent(context, MetodoPagoActivity.class);
                context.startActivity(new Intent(intent));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if(Access.isComprar())
                    {
                        Access.setComprar(false);
                         selectC = cupones.get(position);
                        Access.setCuponSelect(selectC);
                        Snackbar.make(v, "Click detected on item " + selectC.getName(),
                                Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        Intent intent = new Intent(context, MetodoPagoActivity.class);
                        context.startActivity(new Intent(intent));

                    }
                }
            });
        }
    }
    public static  Cupones get_id()
    {
        return selectC;
    }
}
