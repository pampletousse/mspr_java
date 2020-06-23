package com.epsi.msprjava.model;

import java.util.Objects;

public class Stockage {

    private int idStockage;
    private Long espacestockage;
    private int idTypeDechet;
    private Long siret;

    public int getIdStockage() {
        return idStockage;
    }

    public void setIdStockage(int idStockage) {
        this.idStockage = idStockage;
    }

    public Long getEspacestockage() {
        return espacestockage;
    }

    public void setEspacestockage(Long espacestockage) {
        this.espacestockage = espacestockage;
    }

    public int getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(int idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stockage stockage = (Stockage) o;
        return Objects.equals(idStockage, stockage.idStockage) &&
                Objects.equals(espacestockage, stockage.espacestockage) &&
                Objects.equals(idTypeDechet, stockage.idTypeDechet) &&
                Objects.equals(siret, stockage.siret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStockage, espacestockage, idTypeDechet, siret);
    }
}
