package com.epsi.msprjava.model;

import java.util.Objects;

public class Depot {
    private Byte quantiteDeposee;
    private Long idTournee;
    private Byte idTypeDechet;
    private Byte idEntreprise;

    public Byte getQuantiteDeposee() {
        return quantiteDeposee;
    }

    public void setQuantiteDeposee(Byte quantiteDeposee) {
        this.quantiteDeposee = quantiteDeposee;
    }

    public Long getIdTournee() {
        return idTournee;
    }

    public void setIdTournee(Long idTournee) {
        this.idTournee = idTournee;
    }

    public Byte getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(Byte idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    public Byte getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Byte idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(quantiteDeposee, depot.quantiteDeposee) &&
                Objects.equals(idTournee, depot.idTournee) &&
                Objects.equals(idTypeDechet, depot.idTypeDechet) &&
                Objects.equals(idEntreprise, depot.idEntreprise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantiteDeposee, idTournee, idTypeDechet, idEntreprise);
    }
}
