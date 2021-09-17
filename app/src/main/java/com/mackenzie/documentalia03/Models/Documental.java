package com.mackenzie.documentalia03.Models;

import java.util.List;

public class Documental {

    public String name;
    public int poster;

    public Documental(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public Documental() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
