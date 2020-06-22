package com.epsi.msprjava.model;

import java.util.Objects;

public class DetailDemande {
    private Byte quantiteEnlevee;
    private String remarque;
    private Long idDemande;
    private Byte idTypeDechet;

    public Byte getQuantiteEnlevee() {
        return quantiteEnlevee;
    }

    public void setQuantiteEnlevee(Byte quantiteEnlevee) {
        this.quantiteEnlevee = quantiteEnlevee;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public Byte getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(Byte idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailDemande that = (DetailDemande) o;
        return Objects.equals(quantiteEnlevee, that.quantiteEnlevee) &&
                Objects.equals(remarque, that.remarque) &&
                Objects.equals(idDemande, that.idDemande) &&
                Objects.equals(idTypeDechet, that.idTypeDechet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantiteEnlevee, remarque, idDemande, idTypeDechet);
    }
}
