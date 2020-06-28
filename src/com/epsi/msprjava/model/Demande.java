package com.epsi.msprjava.model;

import java.sql.Time;
import java.util.Objects;

public class Demande {
    private Long iddemande;
    private Time datedemande;
    private Time dateenlevement;
    private int idEntreprise;
    private Long idTournee;
    private String webON;
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

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Long getIdTournee() {
        return idTournee;
    }

    public void setIdTournee(Long idTournee) {
        this.idTournee = idTournee;
    }

    public String getWebON() {
        return webON;
    }

    public void setWebON(String webON) {
        this.webON = webON;
    }

    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }


}