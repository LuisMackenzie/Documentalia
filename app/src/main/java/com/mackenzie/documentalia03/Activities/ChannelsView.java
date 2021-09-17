package com.mackenzie.documentalia03.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.mackenzie.documentalia03.Models.Urls;
import com.mackenzie.documentalia03.R;

import java.util.ArrayList;
import java.util.List;

import static com.mackenzie.documentalia03.Models.Urls.getAllUrls;

public class ChannelsView extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView exoPlayerView;
    private Bundle bundle;
    private int selection;
    private Uri videoURI;
    private List<Urls> urlsList;
    private final String dash = "http://www.bok.net/dash/tears_of_steel/cleartext/stream.mpd";
    private final String hls = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
    private final String smoothStreaming = "http://playready.directtaps.net/smoothstreaming/SSWSS720H264/SuperSpeedway_720.ism/Manifest";
    private final String progressive = "http://ftp.nluug.nl/pub/graphics/blender/demo/movies/Sintel.2010.720p.mkv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels_view);

        exoPlayerView = findViewById(R.id.exo_player);
        // getSupportActionBar().hide();
        // Recuperamos los datos del bundle que esta dentro del intent
        bundle = getIntent().getBundleExtra("bundle");
        urlsList = new ArrayList<>();;
        urlsList = getAllUrls();

        if (bundle != null) {
            // Recuperamos el dato del bundle
            selection = bundle.getInt("selection");
            // Pcon este metodo creamos un toast personalizado
            openChannelToast(selection);
            // con este metodo le pasamos la url y la convertimos en objeto Uri
            getChannelUrl(selection);

        } else {
            videoURI = Uri.parse(dash);
        }

        // Aqui creo que creamos la instancia del player
        player = new SimpleExoPlayer.Builder(this).build();
        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(videoURI);
        // Bind the player to the view.
        exoPlayerView.setPlayer(player);
        player.setMediaItem(mediaItem);
        // player.setMediaSource(mediaSource);
        player.prepare();
        player.setPlayWhenReady(true);

    }

    private void openChannelToast(int selection) {
        if (selection == 0 || selection == 1) {
            Toast.makeText(this, "Abriendo Nat Geo", Toast.LENGTH_SHORT).show();
        }
        if (selection == 2 || selection == 3) {
            Toast.makeText(this, "Abriendo Discovery Channel", Toast.LENGTH_SHORT).show();
        }
        if (selection == 4 || selection == 5) {
            Toast.makeText(this,"Abriendo DMAX", Toast.LENGTH_SHORT).show();
        }
        if (selection == 6 || selection == 7) {
            Toast.makeText(this, "Abriendo Odisea", Toast.LENGTH_SHORT).show();
        }
        if (selection == 8 || selection == 9) {
            Toast.makeText(this, "Abriendo Historia", Toast.LENGTH_SHORT).show();
        }
        if (selection == 10 || selection == 11) {
            Toast.makeText(this, "Abriendo Be Mad", Toast.LENGTH_SHORT).show();
        }
        if (selection == 12 || selection == 13) {
            Toast.makeText(this, "Abriendo Mega", Toast.LENGTH_SHORT).show();
        }
        if (selection == 14 || selection == 15) {
            Toast.makeText(this, "Abriendo NatGeo Wild", Toast.LENGTH_SHORT).show();
        }
        if (selection == 16 || selection == 17) {
            Toast.makeText(this, "Abriendo Caza y Pesca", Toast.LENGTH_SHORT).show();
        }
        if (selection == 18 || selection == 19) {
            Toast.makeText(this, "Abriendo La 2", Toast.LENGTH_SHORT).show();
        }
        if (selection == 20 || selection == 21) {
            Toast.makeText(this, "Abriendo Crimen e Investigación", Toast.LENGTH_SHORT).show();
        }
        if (selection == 22 || selection == 23) {
            Toast.makeText(this, "Abriendo Blaze", Toast.LENGTH_SHORT).show();
        }
        if (selection == 24 || selection == 25) {
            Toast.makeText(this, "Abriendo Canal Cocina", Toast.LENGTH_SHORT).show();
        }

    }

    private void getChannelUrl(int selection) {
        Urls temp = urlsList.get(selection);
        String temp2 = temp.toString();
        videoURI = Uri.parse(temp2);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
    }

    @Override
    public void onStart() {
        super.onStart();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*if (getActivity() != null) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }*/
    }


    // Activity onStop, player must be release because of memory saving
    @Override
    public void onStop() {
        super.onStop();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        if (player != null) {
            player.release();
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        /*if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            if (player != null) {
                player.release();
            }

            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}