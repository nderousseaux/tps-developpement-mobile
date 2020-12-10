package com.example.tp34;

import android.graphics.Bitmap;

public class Photo {

    private String auteur;
    private String description;
    private String url;

    public Photo(String auteur, String description, String url_image){
        this.auteur = auteur;
        this.description = description;
        this.url = url_image;
    }

    public String get_auteur() {
        return auteur;
    }

    public String get_description() {
        return description;
    }

    public String get_url() {
        return url;
    }
}

