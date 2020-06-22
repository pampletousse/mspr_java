package com.epsi.msprjava.model;

import java.sql.Time;
import java.util.Objects;

public class Camion {
    private String idimmatric;
    private Time dateachat;
    private String modele;
    private String marque;

    public String getIdimmatric() {
        return idimmatric;
    }

    public void setIdimmatric(String idimmatric) {
        this.idimmatric = idimmatric;
    }

    public Time getDateachat() {
        return dateachat;
    }

    public void setDateachat(Time dateachat) {
        this.dateachat = dateachat;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camion camion = (Camion) o;
        return Objects.equals(idimmatric, camion.idimmatric) &&
                Objects.equals(dateachat, camion.dateachat) &&
                Objects.equals(modele, camion.modele) &&
                Objects.equals(marque, camion.marque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idimmatric, dateachat, modele, marque);
    }
}
