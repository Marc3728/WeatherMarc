package com.dam.proyectodamdaw.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new ConfigFragment()).commit();
    }
}