package com.mackenzie.documentalia03.Fragments;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.mackenzie.documentalia03.R;

public class VideoViewFragment extends DialogFragment implements Player.EventListener {

    public static final String TAG = VideoViewFragment.class.getSimpleName();
    private static final String CHANNEL_KEY = "CHANNEL_URL";
    private SimpleExoPlayer player;
    private PlayerView channelVideoView;
    private DefaultDataSourceFactory dataSourceFactory;

    public VideoViewFragment() {
        // Required empty public constructor
    }

    public static VideoViewFragment newInstance(String streamURL) {
        VideoViewFragment fragment = new VideoViewFragment();
        Bundle args = new Bundle();
        args.putString(CHANNEL_KEY, streamURL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video_view, container, false);

        channelVideoView = rootView.findViewById(R.id.channel_video_detail_exoplayer);

        if (getArguments() != null) {
            String channelUrl = getArguments().getString(CHANNEL_KEY);
            if (getActivity() != null && getContext() != null && channelUrl != null) {
                player = new SimpleExoPlayer.Builder(getContext()).build();

                DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(
                        Util.getUserAgent(getContext(), "com.mackenzie.documentalia03"),
                        DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                        DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                        true
                );

                dataSourceFactory = new DefaultDataSourceFactory(getContext(), httpDataSourceFactory);

                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                loadVideo(channelUrl);
            }
        }

        return rootView;
    }

    public void loadVideo(String streamURL) {
        channelVideoView.setPlayer(player);

        // Add listener for onPlayerError
        player.addListener(this);

        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(streamURL));

        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getActivity() != null) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    // Activity onStop, player must be release because of memory saving
    @Override
    public void onStop() {
        super.onStop();

        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            if (player != null) {
                player.release();
            }

            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }


    public void onPlayerError(ExoPlaybackException error) {
        if (getContext() != null) {
            switch (error.type) {
                case ExoPlaybackException.TYPE_SOURCE:
                    Toast.makeText(getContext(), getContext().getString(R.string.error_source), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "TYPE_SOURCE: " + error.getSourceException().getMessage());
                    break;
                case ExoPlaybackException.TYPE_RENDERER:
                    Toast.makeText(getContext(), getContext().getString(R.string.error_type), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "TYPE_RENDERER: " + error.getRendererException().getMessage());
                    break;
                case ExoPlaybackException.TYPE_UNEXPECTED:
                    Toast.makeText(getContext(), getContext().getString(R.string.error_unknown), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "TYPE_UNEXPECTED: " + error.getUnexpectedException().getMessage());
                    break;
                default:
                    Toast.makeText(getContext(), getContext().getString(R.string.error_unknown), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "TYPE_UNKNOWN: " + error.getCause().getMessage());
            }
        }

        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

}