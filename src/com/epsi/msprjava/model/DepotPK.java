package com.epsi.msprjava.model;

import java.io.Serializable;
import java.util.Objects;

public class DepotPK implements Serializable {
    private Long idTournee;
    private Byte idTypeDechet;
    private Byte idEntreprise;

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
        DepotPK depotPK = (DepotPK) o;
        return Objects.equals(idTournee, depotPK.idTournee) &&
                Objects.equals(idTypeDechet, depotPK.idTypeDechet) &&
                Objects.equals(idEntreprise, depotPK.idEntreprise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTournee, idTypeDechet, idEntreprise);
    }
}
