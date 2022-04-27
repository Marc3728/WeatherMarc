package com.dam.proyectodamdaw.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dam.proyectodamdaw.R;

import java.util.List;

public class MyGPSLocation extends AppCompatActivity implements LocationListener {

    private TextView salida;
    private Button aplicar;
    private LocationManager locationManager;
    private String proveedor;
    private Button cancelarac;
    private Location locationenviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gpslocation);

        aplicar = findViewById(R.id.aplicarubiactual);
        cancelarac = findViewById(R.id.cancelarmiubiac);
        salida = findViewById(R.id.infoubimia);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        @SuppressLint("MissingPermission")
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,true));

        locationenviar = location;

        proveedor = locationManager.getBestProvider(criteria,true);

        mostrarLocalizacion(location);

        aplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("latm",String.valueOf(locationenviar.getLatitude()));
                intent.putExtra("lonm",String.valueOf(locationenviar.getLongitude()));
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        cancelarac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }

    private void mostrarLocalizacion(Location location) {
        if (location!=null){
            salida.append("Latitud " + location.getLatitude() + "\n" + "Longitud " + location.getLongitude());
        } else {
            salida.append("LastKnowedLocation: desconocido");
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(proveedor,10*1000,5,this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mostrarLocalizacion(location);
        locationenviar = location;
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}