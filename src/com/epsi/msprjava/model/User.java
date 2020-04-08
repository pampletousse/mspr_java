package com.epsi.msprjava.model;

public class User {

    private String username;
    private String password;
    private Profil profil;

    public User(String username, String password, Profil profil) {
        this.username = username;
        this.password = password;
        this.profil = profil;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Profil getProfil() {
        return profil;
    }
}
