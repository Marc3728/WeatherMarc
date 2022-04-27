package com.dam.proyectodamdaw.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

public class SeleccionCiudad extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private ArrayAdapter<Ciudad> adapter;
    private Button buttonspinner;
    private Ciudad seleccionada;
    private ImageView imagenc;
    private Button anadirciudad;
    private ImageButton miubi;
    private boolean boleanmiubi;
    private List<Ciudad> listaciudad;
    private final int BUTT_CODE = 1234;
    private final int MIUB_CODE = 4321;
    private Ciudad miubicacionactual;
    private Boolean miubiac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_ciudad);

        spinner = findViewById(R.id.spinner);
        imagenc = findViewById(R.id.imagenseleccionc);
        anadirciudad = findViewById(R.id.anadirciudad);
        buttonspinner = findViewById(R.id.buttonspinner);
        miubi = findViewById(R.id.miubi);
        boleanmiubi = false;
        listaciudad = new ArrayList<>();
        listaciudad.add(new Ciudad("Valencia",39.586376,-0.5421003,"https://multimedia.comunitatvalenciana.com/69078293CFC84CE3B7B3D37B130EEA72/img/3243E68927C44B04A352FB0233DF56A7/VALENCIA.jpg"));
        listaciudad.add(new Ciudad("Madrid",40.4120417,-3.7015498,"https://www.autofacil.es/wp-content/uploads/2021/09/foto-madrid-centro.jpg"));
        listaciudad.add(new Ciudad("Barcelona",41.3736564,2.1263555,"https://zfbarcelona.es/wp-content/uploads/2021/01/czfb-web-portada2-768x343.jpg"));


        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,listaciudad);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(this);
        buttonspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seleccionada!=null){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("ciudad",seleccionada);
                    intent.putExtra("unidad",GestionPreferencias.getUnidad(getApplicationContext()));
                    intent.putExtra("lenguage",GestionPreferencias.getLanguage(getApplicationContext()));
                    intent.putExtra("api",GestionPreferencias.getAPI(getApplicationContext()));
                    startActivity(intent);
                    finish();
                }
            }
        });

        anadirciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CreacionCiudad.class);
                startActivityForResult(intent,BUTT_CODE);
            }
        });

        miubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyGPSLocation.class);
                startActivityForResult(intent,MIUB_CODE);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        seleccionada = (Ciudad) adapterView.getSelectedItem();
        ImageDownloader.downloadImage(getApplicationContext(),seleccionada.getImagen(),imagenc,imagenc.getScaleType(),R.mipmap.ic_launcher_cielo);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spinner.setSelection(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BUTT_CODE){
            if (resultCode == RESULT_CANCELED)
                Toast.makeText(getApplicationContext(),"cancelado",Toast.LENGTH_SHORT).show();
            else if (resultCode== RESULT_OK){
                String name = data.getExtras().getString("nombre");
                double lat = Double.parseDouble(data.getExtras().getString("lat"));
                double lon = Double.parseDouble(data.getExtras().getString("lon"));
                String url = data.getExtras().getString("url");
                listaciudad.add(new Ciudad(name,lat,lon,url));
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode == MIUB_CODE){
            if (resultCode == RESULT_CANCELED)
                Toast.makeText(getApplicationContext(),"cancelado",Toast.LENGTH_SHORT).show();
            else if (resultCode== RESULT_OK){
                String lat = data.getExtras().getString("latm");
                String lon = data.getExtras().getString("lonm");
                listaciudad.add(new Ciudad("MI UBICACION",Double.parseDouble(lat),Double.parseDouble(lon),"ninguna"));
                adapter.notifyDataSetChanged();
                spinner.setSelection(adapter.getCount());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (listaciudad.indexOf(spinner.getSelectedItem())!=0){
            outState.putInt("seleccionado",listaciudad.indexOf(spinner.getSelectedItem()));
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null){
            spinner.setSelection(savedInstanceState.getInt("seleccionado"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.configuration):
                Intent intent = new Intent(this, PreferenciasActivity.class);
                startActivity(intent);
                return true;
            case (R.id.exit):
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}