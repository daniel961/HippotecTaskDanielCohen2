package com.app.daniel.hippotectask_daniel_cohen;

import java.net.URL;

public class Flower {
    String Fname;
    String FbestSeason;
    String url;


    public Flower(Object fname, Object fbestSeason, Object url) {
        Fname = (String) fname;
        FbestSeason = (String) fbestSeason;
        this.url = (String) url;
    }


    public String getFname() {
        return Fname;
    }

    public String getFbestSeason() {
        return FbestSeason;
    }

    public String getUrl() {
        return url;
    }
}
