package com.epsi.msprjava.model;

import java.util.Objects;

public class Depot {
    private Long quantiteDeposee;
    private Long idTournee;
    private int idTypeDechet;
    private int idEntreprise;

    public Long getQuantiteDeposee() {
        return quantiteDeposee;
    }

    public void setQuantiteDeposee(Long quantiteDeposee) {
        this.quantiteDeposee = quantiteDeposee;
    }

    public Long getIdTournee() {
        return idTournee;
    }

    public void setIdTournee(Long idTournee) {
        this.idTournee = idTournee;
    }

    public int getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(int idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
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
