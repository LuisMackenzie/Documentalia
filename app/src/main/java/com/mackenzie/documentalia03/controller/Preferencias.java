package com.mackenzie.documentalia03.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Preferencias {

    public static final String TEMA = "choose_theme";
    public static final String PLAYER = "player_values";
    public static final String HW_VIDEO = "acelerate_hw";
    public static final String IDIOMA = "audio";
    public static final String HW_SOUND = "acelerate_opensl";
    public static final String ACE_INT = "acestream";
    public static final String ACE_EXT = "ace_attach";
    public static final String YOUTUBE_EXT = "youtube";
    public static final String SEARCH_ENGINE = "web";
    public static final String INFORMATION = "informacion";
    public static final String BETA_PROGRAM = "beta";

    public static String userTheme;
    public static String userPlayer;
    public static boolean userHwVideo;
    public static String userLanguage;
    public static boolean userHwSound;
    public static boolean userAceInt;
    public static boolean userAceExt;
    public static boolean userYoutubeExt;
    public static String userSearchEngine;

    public static void obtenerPreferencias(SharedPreferences preferences, Context context) {
        String msjError = "ok";
        userTheme = preferences.getString(TEMA, "System");
        // Toast.makeText(context, "UserTheme=" + userTheme, Toast.LENGTH_SHORT).show();
        userPlayer = preferences.getString(PLAYER, "Automatic");
        // Toast.makeText(context, "userPlayer=" + userPlayer, Toast.LENGTH_SHORT).show();
        userHwVideo = preferences.getBoolean(HW_VIDEO, false);
        userLanguage = preferences.getString(IDIOMA, "Ingles");
        userHwSound = preferences.getBoolean(HW_SOUND, false);
        userAceInt = preferences.getBoolean(ACE_INT, false);
        userAceExt = preferences.getBoolean(ACE_EXT, false);
        userYoutubeExt = preferences.getBoolean(YOUTUBE_EXT, false);
        userSearchEngine = preferences.getString(SEARCH_ENGINE, "Google");



    }

    public static void guardarPreferencias(SharedPreferences preferences, Context context) {

        // SharedPreferences pref2 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferences.edit();
        userTheme = preferences.getString(TEMA, "Light");
        Obj_editor.putString(TEMA, userTheme);
        userPlayer = preferences.getString(PLAYER, "Automatic");
        Obj_editor.putString(PLAYER, userPlayer);
        userHwVideo = preferences.getBoolean(HW_VIDEO, false);
        Obj_editor.putBoolean(HW_VIDEO, userHwVideo);
        userLanguage = preferences.getString(IDIOMA, "Ingles");
        Obj_editor.putString(IDIOMA, userLanguage);
        userHwSound = preferences.getBoolean(HW_SOUND, false);
        Obj_editor.putBoolean(HW_SOUND, userHwSound);
        userAceInt = preferences.getBoolean(ACE_INT, false);
        Obj_editor.putBoolean(ACE_INT, userAceInt);
        userAceExt = preferences.getBoolean(ACE_EXT, false);
        Obj_editor.putBoolean(ACE_EXT, userAceExt);
        userYoutubeExt = preferences.getBoolean(YOUTUBE_EXT, false);
        Obj_editor.putBoolean(YOUTUBE_EXT, userYoutubeExt);
        userSearchEngine = preferences.getString(SEARCH_ENGINE, "Google");
        Obj_editor.putString(SEARCH_ENGINE, userSearchEngine);
        Obj_editor.commit();
    }

}
