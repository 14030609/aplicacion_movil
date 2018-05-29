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
import com.example.fa.practica3.CarritoActivity;
import com.example.fa.practica3.ComprasActivity;
import com.example.fa.practica3.GeneraCompraActivity;
import com.example.fa.practica3.Models.Cupones;
import com.example.fa.practica3.Models.MetodoPago;
import com.example.fa.practica3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MetodoPagoAdapter extends RecyclerView.Adapter<MetodoPagoAdapter.MetodoPagoHolder> {

    static int  id_metodopago;
    static Cupones cupon;
    Context context;
    List<MetodoPago> metodosPago = new ArrayList<>();

    public MetodoPagoAdapter(Context context, List<MetodoPago> metodoPagoList){
        this.context = context;
        this.metodosPago = metodoPagoList;
    }

    @Override
    public MetodoPagoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Toast.makeText(context,"ONcREATE",Toast.LENGTH_SHORT);
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_metodo_pago, parent, false);
        MetodoPagoHolder metodoPagoHolder = new MetodoPagoHolder(view);
        return metodoPagoHolder;
    }

    @Override
    public void onBindViewHolder(MetodoPagoHolder holder, int position) {
        Toast.makeText(context,"ONbIEWVIEW",Toast.LENGTH_SHORT);
        MetodoPago metodoPago = metodosPago.get(position);
        holder.tvNombreMp.setText(metodoPago.getNombre());
        holder.tvDescripcionMp.setText(metodoPago.getDecripcion());

    }

    @Override
    public int getItemCount() {
        return metodosPago.size();
    }

    class MetodoPagoHolder extends RecyclerView.ViewHolder{
        TextView tvNombreMp, tvDescripcionMp;
        public MetodoPagoHolder(View itemView) {
            super(itemView);
            tvNombreMp = (TextView) itemView.findViewById(R.id.tvNombreMp);
            tvDescripcionMp = (TextView)itemView.findViewById(R.id.tvDescripMp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    MetodoPago selectMP = metodosPago.get(position);
                    cupon = Access.getCuponSelect();
                    id_metodopago  = selectMP.getId_metodpago();
                    Intent intent = new Intent(context, GeneraCompraActivity.class);
                    context.startActivity(new Intent(intent));
                }
            });
        }
    }

    public static int getIdPago()
    { return id_metodopago;}

    public static Cupones getCupon()
    { return cupon;}
}
