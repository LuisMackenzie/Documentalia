package com.mackenzie.documentalia03.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

import com.mackenzie.documentalia03.R;
import com.mackenzie.documentalia03.controller.Preferencias;

public class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    private Toolbar myToolbar;
    // private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        myToolbar = findViewById(R.id.toolbar3);
        setMyToolbar();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        // loadPreferences();
        // prefs = PreferenceManager.getDefaultSharedPreferences(this);


    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat caller, PreferenceScreen pref){

        /*// Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
                getClassLoader(),
                pref.getFragment());
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment.newInstance(pref.getKey()))
                // .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit();
        return true;
    }

    public void betaTesting() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/"));
        startActivity(i);
    }

    private void setMyToolbar() {
        // Aqui ponemos el toolbar como action bar por defecto
        setSupportActionBar(myToolbar);
        // Aqui lo mostramos en el toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Aqui recogemos el titulo del fragment y lo ponemos en el actionbar
        getSupportActionBar().setTitle(R.string.action_settings);
    }


    public static class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

        private ListPreference themePreference, playerPreference, audioPreference, enginePreference;
        private CheckBoxPreference videoHW, audioHW, youtubeExt;
        private SwitchPreferenceCompat aceInt, aceExt;
        private Preference infoPreference, updatePreference, betaPreference;
        private SharedPreferences prefs;

        public static SettingsFragment newInstance(String rootKey){
            // Required empty public constructor
            SettingsFragment fragment = new SettingsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, rootKey);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            setUpElements();
            setUpListeners();


            Intent inDevInfo = new Intent(getContext(), DevInfoActivity.class);
            infoPreference.setIntent(inDevInfo);

            Intent inUpdate = new Intent(Intent.ACTION_VIEW);
            inUpdate.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.wiseplay"));
            updatePreference.setIntent(inUpdate);

            Intent inBetaTest = new Intent(Intent.ACTION_VIEW);
            inBetaTest.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.teslacoilsw.launcher"));
            betaPreference.setIntent(inBetaTest);

            // themePreference.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance());


        }

        private void setUpElements() {
            themePreference = (ListPreference) findPreference("choose_theme");
            playerPreference = (ListPreference) findPreference("player_values");
            videoHW = (CheckBoxPreference) findPreference("acelerate_hw");
            audioPreference = (ListPreference) findPreference("audio");
            audioHW = (CheckBoxPreference) findPreference("acelerate_opensl");
            aceInt = (SwitchPreferenceCompat) findPreference("acestream");
            aceExt = (SwitchPreferenceCompat) findPreference("ace_attach");
            youtubeExt = (CheckBoxPreference) findPreference("youtube");
            enginePreference = (ListPreference) findPreference("web_engine");
            infoPreference = findPreference("info_dev");
            updatePreference = findPreference("update_app");
            betaPreference = findPreference("beta_testing");
        }

        private void setUpListeners() {
            themePreference.setOnPreferenceChangeListener(this);
            playerPreference.setOnPreferenceChangeListener(this);
            videoHW.setOnPreferenceChangeListener(this);
            audioPreference.setOnPreferenceChangeListener(this);
            audioHW.setOnPreferenceChangeListener(this);
            youtubeExt.setOnPreferenceChangeListener(this);
            aceInt.setOnPreferenceChangeListener(this);
            aceExt.setOnPreferenceChangeListener(this);
            enginePreference.setOnPreferenceChangeListener(this);
        }

        private void loadPreferences() {

            PreferenceManager.setDefaultValues(getContext(), R.xml.root_preferences, false);
            prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            Preferencias.obtenerPreferencias(prefs, getContext());

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            switch (preference.getKey()) {
                case "choose_theme":
                    if (newValue.equals("light")) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        Toast.makeText(getContext(), "Usando Tema Claro", Toast.LENGTH_SHORT).show();
                    } else if (newValue.equals("dark")){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(getContext(), "Usando Tema Oscuro", Toast.LENGTH_SHORT).show();
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        Toast.makeText(getContext(), "Usando Tema Sistema", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "player_values":
                    if (newValue.equals("automatic")) {
                        Toast.makeText(getContext(),R.string.auto_selected, Toast.LENGTH_SHORT).show();
                    } else if (newValue.equals("exoPlayer")){
                        Toast.makeText(getContext(),R.string.exo_selected, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),R.string.system_selected, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "acelerate_hw":
                    if (newValue.equals(true)) {
                        Toast.makeText(getContext(),"Aceleración por Hardware Desactivada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"Aceleración por Hardware Activada", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "audio":
                    if (newValue.equals("ingles")) {
                        Toast.makeText(getContext(),"Se utilizara Ingles como audio predeterminado", Toast.LENGTH_SHORT).show();
                    } else if (newValue.equals("espanyol")){
                        Toast.makeText(getContext(),"Se utilizara Español como audio predeterminado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"Se utilizara Catalán como audio predeterminado", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "acelerate_opensl":
                    if (newValue.equals(true)) {
                        Toast.makeText(getContext(),"Aceleración de audio por Hardware Desactivada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"Aceleración de audio por Hardware Activada", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "acestream":
                    if (newValue.equals(true)) {
                        Toast.makeText(getContext(),"Se abrira AceStream con el motor interno", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"No se abren los enlaces AceStream", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "ace_attach":
                    if (newValue.equals(true)) {
                        Toast.makeText(getContext(),"Se abrira AceStream con el motor Externo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"Se abrira AceStream con el motor interno", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "youtube":
                    if (newValue.equals(true)) {
                        Toast.makeText(getContext(),"Se abriran los videos de Youtube con la app oficial", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"Se abriran los videos de Youtube con reproductor interno", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "web_engine":
                    if (newValue.equals("google")) {
                        Toast.makeText(getContext(),"Buscador Seleccionado: Google", Toast.LENGTH_SHORT).show();
                    } else if (newValue.equals("duckduckgo")){
                        Toast.makeText(getContext(),"Buscador Seleccionado: DuckDuckGo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),"Buscador Seleccionado: Bing", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    Toast.makeText(getContext(), " Las demas opciones " + newValue, Toast.LENGTH_SHORT).show();
                    break;

            }

            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /*if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Pulsaste algo", Toast.LENGTH_SHORT).show();
            onBackPressed();
            return true;
        }*/
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        // loadPreferences();
        // recreate();
        // Toast.makeText(this, "Pulsaste atras", Toast.LENGTH_SHORT).show();
        // super.onBackPressed();
    }
}