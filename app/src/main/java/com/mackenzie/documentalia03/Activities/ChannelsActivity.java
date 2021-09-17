package com.mackenzie.documentalia03.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mackenzie.documentalia03.Interfaces.ChannelClickListener;
import com.mackenzie.documentalia03.Models.Channels;
import com.mackenzie.documentalia03.Models.Urls;
import com.mackenzie.documentalia03.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChannelsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar myToolbar;
    private ImageView natGeo, discovery, dmax, odisea;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Context context;
    private List<Channels> channels;
    private List<Urls> urlsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toast toast;
    private ChannelClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);
        Objetos();
        setMyToolbar();
        setImages();

        natGeo.setOnClickListener(this);
        discovery.setOnClickListener(this);
        dmax.setOnClickListener(this);
        odisea.setOnClickListener(this);


        // Instanciamos la lista y la rellenamos
        // channels = MainActivity.getAllChannels();

        // Rellenamos la lista de urls aqui
        // urlsList = MainActivity.getAllUrls();

    }

    private void openWeb() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.google.es/"));
        startActivity(i);
    }

    private void setImages() {
        Picasso.get().load(R.drawable.natgeo).fit().into(natGeo);
        Picasso.get().load(R.drawable.discovery).fit().into(discovery);
        Picasso.get().load(R.drawable.dmax).fit().into(dmax);
        Picasso.get().load(R.drawable.odisea).fit().into(odisea);
    }

    private void setMyToolbar() {
        // Aqui ponemos el toolbar como action bar por defecto
        setSupportActionBar(myToolbar);
        // Aqui le damos un icono
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
        // Aqui lo mostramos en el toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Objetos () {
        // Instanciamos el toolbar
        myToolbar = findViewById(R.id.toolbar2);
        natGeo = findViewById(R.id.iv_natgeo);
        discovery = findViewById(R.id.iv_discovery);
        dmax = findViewById(R.id.iv_dmax);
        odisea = findViewById(R.id.iv_odisea);
        // instanciamos el drawerLayout
        // drawer = findViewById(R.id.drawer_layout);
        // instanciamos el NavigationView
        // navigationView = findViewById(R.id.nav_view);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_natgeo:
                Toast.makeText(this, "Pulsaste Nat Geo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_discovery:
                Toast.makeText(this, "Pulsaste Discovery Channel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_dmax:
                Toast.makeText(this, "Pulsaste DMAX", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_odisea:
                Toast.makeText(this, "Pulsaste Canal Odisea", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}