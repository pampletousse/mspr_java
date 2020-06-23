package com.epsi.msprjava.model;

import java.io.Serializable;
import java.util.Objects;

public class DetailDemandePK implements Serializable {
    private Long idDemande;
    private int idTypeDechet;

    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(int idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailDemandePK that = (DetailDemandePK) o;
        return Objects.equals(idDemande, that.idDemande) &&
                Objects.equals(idTypeDechet, that.idTypeDechet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDemande, idTypeDechet);
    }
}
