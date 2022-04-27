package com.dam.proyectodamdaw.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.proyectodamdaw.R;

public class CreacionCiudad extends AppCompatActivity {
    private EditText nombre;
    private EditText lat;
    private EditText lon;
    private EditText url;
    private Button aceptar;
    private Button cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creacion_ciudad_usuario);

        nombre = findViewById(R.id.nombreciudad);
        lat = findViewById(R.id.latitudciudad);
        lon = findViewById(R.id.longitudciudad);
        url = findViewById(R.id.urlciudad);
        aceptar = findViewById(R.id.anadir);
        cancelar = findViewById(R.id.cancelar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre!=null && lat!=null && lon!=null && url!=null){
                    Intent intent = new Intent();
                    intent.putExtra("nombre",nombre.getText().toString());
                    intent.putExtra("lat",lat.getText().toString());
                    intent.putExtra("lon",lon.getText().toString());
                    intent.putExtra("url",url.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Introduce todos los datos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}