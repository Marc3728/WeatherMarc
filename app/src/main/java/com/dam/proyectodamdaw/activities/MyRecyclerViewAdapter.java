package com.dam.proyectodamdaw.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectodamdaw.Parameters;
import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Root root;
    private LayoutInflater inflater;
    public View.OnClickListener listener;
    private Context contexta;

    public MyRecyclerViewAdapter(Context context, Root r){
        this.root = r;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contexta = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simpleelement,parent,false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Date date = new Date(root.list.get(position).dt * 1000L);
        holder.fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
        holder.hora.setText(new SimpleDateFormat("HH:mm").format(date));
        holder.diasemana.setText(new SimpleDateFormat("EEEE",new Locale("es","ES")).format(date));
        holder.temperatura.setText(root.list.get(position).main.temp + "");
        holder.estado.setText(root.list.get(position).weather.get(0).description);
        holder.maximo.setText(root.list.get(position).main.temp_max + "");
        holder.minimo.setText(root.list.get(position).main.temp_min + "");
        ImageDownloader.downloadImage(contexta,Parameters.URL_ICON_PRE+root.list.get(position).weather.get(0).icon+Parameters.URL_ICON_POST, holder.image,holder.image.getScaleType(),R.mipmap.ic_launcher_cielo);
    }

    @Override
    public int getItemCount() {
        return root.list.size();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView diasemana;
        TextView fecha;
        TextView hora;
        TextView estado;
        TextView temperatura;
        TextView minimo;
        TextView maximo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView2);
            diasemana = itemView.findViewById(R.id.diasemanad);
            fecha = itemView.findViewById(R.id.fecha);
            hora = itemView.findViewById(R.id.hora);
            estado = itemView.findViewById(R.id.estado);
            temperatura = itemView.findViewById(R.id.temperatura);
            minimo = itemView.findViewById(R.id.minimo);
            maximo = itemView.findViewById(R.id.maximo);
        }
    }
}
