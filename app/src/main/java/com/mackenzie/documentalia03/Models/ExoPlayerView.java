package com.mackenzie.documentalia03.Models;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class ExoPlayerView extends FrameLayout implements Player.EventListener {

    private SimpleExoPlayer player;

    public ExoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // Aqui creamos un objeto de tipo PlayerView
        PlayerView playerView = new PlayerView(context);
        // Aqui no se que hace esto
        addView(playerView);
        // aqui creamos el objeto de tipo exoplayer y lo inicializamos
        player = new SimpleExoPlayer.Builder(context).build();
        // Aqui unimos el listener al objeto SimpleExoPlayer
        player.addListener(this);
        // esto parece que inicia el player cuando esta listo
        player.setPlayWhenReady(true);
        // Aqui creo que unimos el player a la vista
        playerView.setPlayer(player);
    }

    private void prepare(Uri uri) {
        int timeout = 10000;
        MediaSource mediaSource;

        /*DefaultHttpDataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory
                (Util.getUserAgent(getContext(), "ExoPlayerView"),
                        timeout, timeout, true);*/
        // Create a data source factory.
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();

        // Create a HLS media source pointing to a playlist uri.
        HlsMediaSource hlsMediaSource =
                new HlsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(uri));

        // Set the media source to be played.
        player.setMediaSource(hlsMediaSource);

        // Prepare the player.
        player.prepare();

    }


    public void onPlayerError(ExoPlaybackException error) {
        // super.onPlayerError(error);
        Log.e("Exoplayer", "Error: ", error);
        }

}
