package com.dam.proyectodamdaw.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.proyectodamdaw.Parameters;
import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_element_definition);
        int n = getIntent().getExtras().getInt("numero");
        Root r = (Root) getIntent().getExtras().get("rooti");
        Ciudad c = (Ciudad) getIntent().getExtras().get("ciudad");
        Date date = new Date(r.list.get(n).dt * 1000L);
        TextView fecha = findViewById(R.id.fechad);
        TextView diasemana = findViewById(R.id.diasemanad);
        TextView hora = findViewById(R.id.horad);
        TextView temp = findViewById(R.id.temperaturad);
        TextView tempmax = findViewById(R.id.temperaturamaxd);
        TextView tempmin = findViewById(R.id.temperaturamind);
        TextView humedad = findViewById(R.id.humedadd);
        TextView sensacion = findViewById(R.id.sesaciontermicad);
        ImageView imageView = findViewById(R.id.imagend);
        Button volver = findViewById(R.id.volveralista);
        fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
        diasemana.setText(new SimpleDateFormat("EEEE",new Locale("es","ES")).format(date));
        hora.setText(new SimpleDateFormat("HH:mm").format(date));
        temp.setText(r.list.get(n).main.temp + "");
        tempmax.setText(r.list.get(n).main.temp_max + "");
        tempmin.setText(r.list.get(n).main.temp_min + "");
        humedad.setText(r.list.get(n).main.humidity + "");
        sensacion.setText(String.valueOf(r.list.get(n).main.feels_like));
        ImageDownloader.downloadImage(getApplicationContext(),Parameters.URL_ICON_PRE+r.list.get(n).weather.get(0).icon+Parameters.URL_ICON_POST, imageView,imageView.getScaleType(),R.mipmap.ic_launcher_cielo);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("rootdevuelta",r);
                intent.putExtra("ciudaddevuelta",c);
                startActivity(intent);
                finish();
            }
        });
    }
}