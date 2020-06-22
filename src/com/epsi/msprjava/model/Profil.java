package com.epsi.msprjava.model;

import java.util.Objects;

public class Profil {
    private Short idProfil;
    private String nomprofil;

    public Short getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Short idProfil) {
        this.idProfil = idProfil;
    }

    public String getNomprofil() {
        return nomprofil;
    }

    public void setNomprofil(String nomprofil) {
        this.nomprofil = nomprofil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profil profil = (Profil) o;
        return Objects.equals(idProfil, profil.idProfil) &&
                Objects.equals(nomprofil, profil.nomprofil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfil, nomprofil);
    }
}
