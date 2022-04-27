package com.dam.proyectodamdaw.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectodamdaw.Parameters;
import com.dam.proyectodamdaw.api.Connector;
import com.dam.proyectodamdaw.base.BaseActivity;
import com.dam.proyectodamdaw.base.CallInterface;
import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

public class MainActivity extends BaseActivity implements CallInterface, View.OnClickListener {
    Root root;
    Root rootvuelta;
    RecyclerView recyclerView;
    Ciudad ciudad;
    Ciudad ciudadvuelta;
    TextView ciudadinf;
    String unidadel;
    String apiper;
    String lenguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.recycler_view);
        ciudad = (Ciudad) getIntent().getExtras().get("ciudad");
        rootvuelta = (Root) getIntent().getExtras().get("rootdevuelta");
        ciudadvuelta = (Ciudad) getIntent().getExtras().get("ciudaddevuelta");
        unidadel = (String) getIntent().getExtras().get("unidad");
        apiper = (String) getIntent().getExtras().get("api");
        lenguage = (String) getIntent().getExtras().get("lenguage");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        if (rootvuelta != null){
            root = rootvuelta;
            ciudad = ciudadvuelta;
        } else if(root==null) {
            if (!apiper.equals("")){
                root = Connector.getConector().get(Root.class,apiper);
            } else {
                root = Connector.getConector().get(Root.class, "forecast?lang=" + lenguage + "&units=" + unidadel + "&lat=" + ciudad.getLat() + "&lon=" + ciudad.getLon() + "&appid=4a668aded6d58cfa85819ce1b23942a2");
            }
        }
    }

    @Override
    public void doInUI() {
        hideProgress();
        recyclerView = findViewById(R.id.recyclerview);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, root);
        myRecyclerViewAdapter.setListener(this);

        recyclerView.setAdapter(myRecyclerViewAdapter);
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setOnClickListener(this);

        ciudadinf = findViewById(R.id.ciudadinf);
        ciudadinf.setText(ciudad.getNombre().toUpperCase());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
        int info = recyclerView.getChildAdapterPosition(view);
        intent.putExtra("numero",info);
        intent.putExtra("rooti",root);
        intent.putExtra("ciudad",ciudad);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(root!=null && ciudad!=null) {
            outState.putSerializable("rootguardado", root);
            outState.putSerializable("ciudadguardado", ciudad);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null) {
            root = (Root) savedInstanceState.getSerializable("rootguardado");
            ciudad = (Ciudad) savedInstanceState.getSerializable("ciudadguardado");
        }
    }
}