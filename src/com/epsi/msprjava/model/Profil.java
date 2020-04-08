package com.epsi.msprjava.model;

public class Profil {

    private int id;
    private String nom;

    public Profil(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
