package com.dam.proyectodamdaw.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.proyectodamdaw.R;

import java.util.Arrays;
import java.util.List;


public class ConfigFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencesc,rootKey);

        ListPreference listPreference = findPreference("unidades");

        java.util.List<String> entries = Arrays.asList(getResources().getStringArray(R.array.unidades_entries));
        List<String> values = Arrays.asList(getResources().getStringArray(R.array.unidades_values));

        String val = entries.get(values.indexOf(GestionPreferencias.getUnidad(getContext())));

        listPreference.setSummary("Seleccionado " + val);

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String val = entries.get(values.indexOf(newValue));
                listPreference.setSummary("Seleccionado " + val);
                return true;
            }
        });

        SwitchPreference switchPreference = findPreference("switch_preference_1");
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (switchPreference.isChecked()){
                    NLmode.setMode(0);
                } else {
                    NLmode.setMode(1);
                }
                return true;
            }
        });

        EditTextPreference editTextPreference = findPreference("edit_text_preference_1");

        ListPreference listPreferencel = findPreference("idiomas");

        java.util.List<String> entriesl = Arrays.asList(getResources().getStringArray(R.array.lenguage_entries));
        List<String> valuesl = Arrays.asList(getResources().getStringArray(R.array.lenguages_values));

        listPreferencel.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String vald = entriesl.get(valuesl.indexOf(newValue));
                listPreferencel.setSummary("Seleccionado " + vald);
                return true;
            }
        });
    }
}