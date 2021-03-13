package com.example.easyminingapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String NOM_PREFERENCIES = "Easymining_prefs";
    private final static String DARKMODE="darkmode";
    private Boolean darkmode;
    private SharedPreferences prefs;

    public Preferences(Context ctx) {
        this.prefs = ctx.getSharedPreferences(NOM_PREFERENCIES, ctx.MODE_PRIVATE);
        this.darkmode = this.prefs.getBoolean(DARKMODE,false);
    }

    public Boolean getDarkmode() {
        return darkmode;
    }
    public void setDarkmode(Boolean rec) {
        this.darkmode = rec;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putBoolean(DARKMODE,darkmode);
        editor.apply();
    }
}
