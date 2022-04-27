package com.dam.proyectodamdaw.activities;

import androidx.appcompat.app.AppCompatDelegate;

public class NLmode {
    public static void setMode(int mode){
        if (mode==0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
