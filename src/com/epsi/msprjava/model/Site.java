package com.epsi.msprjava.model;

import java.util.Objects;

public class Site {
    private int idSite;
    private String nomsite;
    private int noruesite;
    private String ruesite;
    private Short cpostalsite;
    private String villesite;

    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }

    public String getNomsite() {
        return nomsite;
    }

    public void setNomsite(String nomsite) {
        this.nomsite = nomsite;
    }

    public int getNoruesite() {
        return noruesite;
    }

    public void setNoruesite(int noruesite) {
        this.noruesite = noruesite;
    }

    public String getRuesite() {
        return ruesite;
    }

    public void setRuesite(String ruesite) {
        this.ruesite = ruesite;
    }

    public Short getCpostalsite() {
        return cpostalsite;
    }

    public void setCpostalsite(Short cpostalsite) {
        this.cpostalsite = cpostalsite;
    }

    public String getVillesite() {
        return villesite;
    }

    public void setVillesite(String villesite) {
        this.villesite = villesite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(idSite, site.idSite) &&
                Objects.equals(nomsite, site.nomsite) &&
                Objects.equals(noruesite, site.noruesite) &&
                Objects.equals(ruesite, site.ruesite) &&
                Objects.equals(cpostalsite, site.cpostalsite) &&
                Objects.equals(villesite, site.villesite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSite, nomsite, noruesite, ruesite, cpostalsite, villesite);
    }
}
