package com.epsi.msprjava.model;

import java.util.Objects;

public class Entreprise {
    private Long siret;
    private String raisonsociale;
    private int norueentr;
    private String rueentr;
    private Short cpostalentr;
    private String villeentr;
    private String notel;
    private String contact;
    private Boolean estClient;
    private Boolean estCentreTraitement;

    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    public String getRaisonsociale() {
        return raisonsociale;
    }

    public void setRaisonsociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    public int getNorueentr() {
        return norueentr;
    }

    public void setNorueentr(int norueentr) {
        this.norueentr = norueentr;
    }

    public String getRueentr() {
        return rueentr;
    }

    public void setRueentr(String rueentr) {
        this.rueentr = rueentr;
    }

    public Short getCpostalentr() {
        return cpostalentr;
    }

    public void setCpostalentr(Short cpostalentr) {
        this.cpostalentr = cpostalentr;
    }

    public String getVilleentr() {
        return villeentr;
    }

    public void setVilleentr(String villeentr) {
        this.villeentr = villeentr;
    }

    public String getNotel() {
        return notel;
    }

    public void setNotel(String notel) {
        this.notel = notel;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getEstClient() {
        return estClient;
    }

    public void setEstClient(Boolean estClient) {
        this.estClient = estClient;
    }

    public Boolean getEstCentreTraitement() {
        return estCentreTraitement;
    }

    public void setEstCentreTraitement(Boolean estCentreTraitement) {
        this.estCentreTraitement = estCentreTraitement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entreprise that = (Entreprise) o;
        return Objects.equals(siret, that.siret) &&
                Objects.equals(raisonsociale, that.raisonsociale) &&
                Objects.equals(norueentr, that.norueentr) &&
                Objects.equals(rueentr, that.rueentr) &&
                Objects.equals(cpostalentr, that.cpostalentr) &&
                Objects.equals(villeentr, that.villeentr) &&
                Objects.equals(notel, that.notel) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(estClient, that.estClient) &&
                Objects.equals(estCentreTraitement, that.estCentreTraitement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siret, raisonsociale, norueentr, rueentr, cpostalentr, villeentr, notel, contact, estClient, estCentreTraitement);
    }
}
