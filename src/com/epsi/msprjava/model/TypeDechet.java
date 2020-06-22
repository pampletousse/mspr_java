package com.epsi.msprjava.model;

import java.util.Objects;

public class TypeDechet {
    private Byte idTypeDechet;
    private String nom;
    private Boolean nivDanger;

    public Byte getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(Byte idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getNivDanger() {
        return nivDanger;
    }

    public void setNivDanger(Boolean nivDanger) {
        this.nivDanger = nivDanger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDechet that = (TypeDechet) o;
        return Objects.equals(idTypeDechet, that.idTypeDechet) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(nivDanger, that.nivDanger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeDechet, nom, nivDanger);
    }
}
