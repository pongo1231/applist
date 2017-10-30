package com.pongo1231.applist.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class App {
    private String name;
    private Drawable icon;
    private String version;

    public App(String name, Drawable icon, String version) {
        this.name = name;
        this.icon = icon;
        this.version = version;
    }

    /**
     * @return Name of the app
     */
    public String getName() {
        return name;
    }

    /**
     * @return Icon of the app
     */
    public Drawable getIcon() {
        return icon;
    }

    /**
     * @return Version of the app
     */
    public String getVersion() {
        return version;
    }
}
