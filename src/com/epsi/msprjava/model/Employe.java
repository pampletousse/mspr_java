package com.epsi.msprjava.model;

import java.sql.Time;
import java.util.Objects;

public class Employe {
    private Short idEmploye;
    private String nom;
    private String prenom;
    private Time datenaiss;
    private Time dateembauche;
    private Long salaire;
    private Byte idProfil;

    public Short getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Short idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Time getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Time datenaiss) {
        this.datenaiss = datenaiss;
    }

    public Time getDateembauche() {
        return dateembauche;
    }

    public void setDateembauche(Time dateembauche) {
        this.dateembauche = dateembauche;
    }

    public Long getSalaire() {
        return salaire;
    }

    public void setSalaire(Long salaire) {
        this.salaire = salaire;
    }

    public Byte getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Byte idProfil) {
        this.idProfil = idProfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(idEmploye, employe.idEmploye) &&
                Objects.equals(nom, employe.nom) &&
                Objects.equals(prenom, employe.prenom) &&
                Objects.equals(datenaiss, employe.datenaiss) &&
                Objects.equals(dateembauche, employe.dateembauche) &&
                Objects.equals(salaire, employe.salaire) &&
                Objects.equals(idProfil, employe.idProfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmploye, nom, prenom, datenaiss, dateembauche, salaire, idProfil);
    }
}
