package com.dam.proyectodamdaw.activities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class GestionPreferencias {
    private static SharedPreferences preferences;

    public static String getUnidad(Context context){
        inicializa(context);
        return preferences.getString("unidades","default");
    }

    private static void inicializa(Context context) {
        if (preferences==null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static String getAPI(Context context){
        inicializa(context);
        return preferences.getString("edit_text_preference_1","default");
    }

    public static String getLanguage(Context context){
        inicializa(context);
        return preferences.getString("idiomas","default");
    }
}
