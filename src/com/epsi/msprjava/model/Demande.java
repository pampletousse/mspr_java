package com.epsi.msprjava.model;

import java.sql.Time;
import java.util.Objects;

public class Demande {
    private Long iddemande;
    private Time datedemande;
    private Time dateenlevement;
    private Long siret;
    private Long idTournee;
    private int idSite;

    public Long getIddemande() {
        return iddemande;
    }

    public void setIddemande(Long iddemande) {
        this.iddemande = iddemande;
    }

    public Time getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(Time datedemande) {
        this.datedemande = datedemande;
    }

    public Time getDateenlevement() {
        return dateenlevement;
    }

    public void setDateenlevement(Time dateenlevement) {
        this.dateenlevement = dateenlevement;
    }

    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    public Long getIdTournee() {
        return idTournee;
    }

    public void setIdTournee(Long idTournee) {
        this.idTournee = idTournee;
    }

    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demande demande = (Demande) o;
        return Objects.equals(iddemande, demande.iddemande) &&
                Objects.equals(datedemande, demande.datedemande) &&
                Objects.equals(dateenlevement, demande.dateenlevement) &&
                Objects.equals(siret, demande.siret) &&
                Objects.equals(idTournee, demande.idTournee) &&
                Objects.equals(idSite, demande.idSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddemande, datedemande, dateenlevement, siret, idTournee, idSite);
    }
}
