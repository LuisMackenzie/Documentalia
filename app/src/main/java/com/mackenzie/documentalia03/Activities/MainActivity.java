package com.mackenzie.documentalia03.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;
import com.mackenzie.documentalia03.Fragments.ChannelsFragment;
import com.mackenzie.documentalia03.Fragments.OnDemandFragment;
import com.mackenzie.documentalia03.Fragments.VideoViewFragment;
import com.mackenzie.documentalia03.Models.ChannelList;
import com.mackenzie.documentalia03.Models.Channels;
import com.mackenzie.documentalia03.Models.Urls;
import com.mackenzie.documentalia03.R;
import com.mackenzie.documentalia03.controller.Preferencias;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity{

    private Toolbar myToolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SearchView searchView;
    private SharedPreferences prefs;
    private ChannelItemFilter mFilter = new ChannelItemFilter();
    private ArrayList<ChannelList> channelLists;
    private static final int MY_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Aqui seteamos las preferencias al valor establecido anteriormente
        loadPreferences();

        Objetos();
        setMyToolbar();
        setFragmentByDefault();
        // aqui gestionamos el click del navigation drawer
        navigationConfig();
        // Este metodo comprueba si tenemos permisos de escritura en la memoria y en caso negativo los pide
        checkPermission();
        // Aqui gestionamos el clikck
        // boton.setOnClickListener(this);


    }

    private void loadPreferences() {

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Preferencias.obtenerPreferencias(prefs, this);

    }

    private void navigationConfig() {
        // aqui gestionamos el click del navigation drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_channels:
                        fragment = new ChannelsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_ondemand:
                        fragment = new OnDemandFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_search:


                        // searchView.requestFocus(90, null);
                        searchView.requestFocus();
                        // searchView.onActionViewExpanded();
                        /*int selection = 2;
                        Intent in = new Intent(MainActivity.this, ChannelsView.class);
                        // Inicializamos el bundle
                        bun = new Bundle();
                        // agregamos un dato tipo String que recogemos de et1
                        bun.putInt("selection", selection);
                        // agregamos el bundle al intent
                        in.putExtra("bundle", bun);
                        startActivity(in);
                        // fragment = new InfoFragment();*/
                        drawer.closeDrawers();
                        break;
                    case R.id.menu_option1:
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        // oast.makeText(MainActivity.this, "Has pulsado en ajustes", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        break;
                    case R.id.menu_option2:
                        fragment = new VideoViewFragment();
                        fragmentTransaction = true;
                        // openWeb();
                        Toast.makeText(MainActivity.this, "Has pulsado actualizar", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        break;
                    case R.id.menu_option3:
                        shareTelegram();
                        Toast.makeText(MainActivity.this, "Has Pulsado la opcion Telegram", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        break;
                    case R.id.menu_option4:
                        Toast.makeText(MainActivity.this, "Saliendo", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        finish();
                        break;
                }

                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawer.closeDrawers();
                }

                return true;
            }
        });

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
        myToolbar = findViewById(R.id.toolbar);
        // instanciamos el drawerLayout
        drawer = findViewById(R.id.drawer_layout);
        // instanciamos el NavigationView
        navigationView = findViewById(R.id.nav_view);
        // instanciamos el imageButton
        // boton = (ImageButton) findViewById(R.id.imageViewPoster);
        // Esto no se bien lo que hace
        // SearchView searchView = (SearchView) searchItem.getActionView();
    }

    /*public void abrirExoplayer(int selection) {
        Intent intent = new Intent(MainActivity.this, ChannelsView.class);
        // Inicializamos el bundle
        Bundle bun = new Bundle();
        // agregamos un dato tipo String que recogemos de et1
        bun.putInt("selection", selection);
        // agregamos el bundle al intent
        intent.putExtra("bundle", bun);
        startActivity(intent);
    }*/

    /*public static List<Urls> getAllUrls() {
        return new ArrayList<Urls>() {{
            add(new Urls("www.google.es","National Geographic"));
            add(new Urls("www.google.es","Discovery Channel"));
            add(new Urls("www.google.es","DMAX"));
            add(new Urls("www.google.es","Odisea"));
            add(new Urls("www.google.es","Historia"));
            add(new Urls("www.google.es","BeMad"));
            add(new Urls("www.google.es","Mega"));
            add(new Urls("www.google.es","Nat Geo Wild"));
            add(new Urls("www.google.es","Caza y Pesca"));
            add(new Urls("www.google.es","La 2"));
            add(new Urls("www.google.es","Crimen e Investigación"));
            add(new Urls("www.google.es","Blaze"));
            add(new Urls("www.google.es","Canal Cocina"));
        }};
    }*/

    public static List<Channels> getAllChannels() {
        return new ArrayList<Channels>() {{
            add(new Channels("National Geographic", R.drawable.natgeo));
            add(new Channels("National Geographic", R.drawable.natgeo));
            add(new Channels("Discovery Channel", R.drawable.discovery));
            add(new Channels("Discovery Channel", R.drawable.discovery));
            add(new Channels("DMAX", R.drawable.dmax));
            add(new Channels("DMAX", R.drawable.dmax));
            add(new Channels("Odisea", R.drawable.odisea));
            add(new Channels("Odisea", R.drawable.odisea));
            add(new Channels("Historia", R.drawable.historia));
            add(new Channels("Historia", R.drawable.historia));
            add(new Channels("BeMad", R.drawable.bemad));
            add(new Channels("BeMad", R.drawable.bemad));
            add(new Channels("Mega", R.drawable.mega));
            add(new Channels("Mega", R.drawable.mega));
            add(new Channels("Nat Geo Wild", R.drawable.natgeowild));
            add(new Channels("Nat Geo Wild", R.drawable.natgeowild));
            add(new Channels("Caza y Pesca", R.drawable.caza));
            add(new Channels("Caza y Pesca", R.drawable.caza));
            add(new Channels("La 2", R.drawable.la2));
            add(new Channels("La 2", R.drawable.la2));
            add(new Channels("Crimen e Investigación", R.drawable.crimen));
            add(new Channels("Crimen e Investigación", R.drawable.crimen));
            add(new Channels("Blaze", R.drawable.blaze));
            add(new Channels("Blaze", R.drawable.blaze));
            add(new Channels("Canal Cocina", R.drawable.canalcocina));
            add(new Channels("Canal Cocina", R.drawable.canalcocina));
        }};
    }

    private void setFragmentByDefault() {
        changeFragment( new ChannelsFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        // Aqui reemplazamos el contenido del FrameLayout y el fragment y lo mostramos
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        // Otra manera mas detallada de hacer lo anterior seria
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();*/
        // Mostramos la opcion escogida como Checked para indicar donde estamos
        item.setChecked(true);
        // Aqui recogemos el titulo del fragment y lo ponemos en el actionbar
        getSupportActionBar().setTitle(item.getTitle());

    }

    private void openWeb() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.google.es/"));
        startActivity(i);
    }

    private void shareTelegram() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://t.me/joinchat/T5YB6298BZ9yN34j"));
        final String appName = "org.telegram.messenger";
        if (isAppAvailable(getApplicationContext(), appName))
            i.setPackage(appName);
        startActivity(i);

        // Esto es para compartir por telegram
        /*Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        waIntent.setPackage("org.telegram.messenger");
        if (waIntent != null) {
            waIntent.putExtra(Intent.EXTRA_TEXT, message);//
            startActivity(Intent.createChooser(waIntent, "Share with"));
        }
        else
        {
            Toast.makeText(this, "Telegram is not installed", Toast.LENGTH_SHORT).show();
        }*/

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Explicacion del uso del permiso
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }
    }

    public static boolean isAppAvailable(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    private class ChannelItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            final ArrayList<ChannelList> filteredChannels = new ArrayList<>();

            String channelNameToFilter;
            for (int i = 0; i < channelLists.size(); i++) {
                ChannelList channelList = channelLists.get(i);
                channelNameToFilter = channelList.getChannel().getName();

                if (channelNameToFilter.toLowerCase().contains(filterString)) {
                    filteredChannels.add(channelList);
                }
            }

            results.values = filteredChannels;
            results.count = filteredChannels.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // mAdapter.submitList((ArrayList<ChannelList>) results.values);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu mimenu) {
        // Inflamos el menu
        getMenuInflater().inflate(R.menu.menu, mimenu);

        MenuItem searchItem = mimenu.findItem(R.id.menu_buscar);
        // Change color of the search button
        if (getContext() != null) {
            Drawable drawable = DrawableCompat.wrap(searchItem.getIcon());
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
            mimenu.findItem(R.id.menu_buscar).setIcon(drawable);
        }

        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This will be fired every time you input any character.
                if (mFilter != null) {
                    mFilter.filter(newText);
                }
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // abrir el menu lateral
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_buscar:

                Toast.makeText(this, "Busqueda Avanzada No Implementada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        Preferencias.guardarPreferencias(prefs, this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Preferencias.guardarPreferencias(prefs, this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Preferencias.guardarPreferencias(prefs, this);
        super.onDestroy();
    }
}