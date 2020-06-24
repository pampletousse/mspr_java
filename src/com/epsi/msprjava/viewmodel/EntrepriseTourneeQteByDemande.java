package com.epsi.msprjava.viewmodel;

import com.epsi.msprjava.model.Entreprise;
import com.epsi.msprjava.model.Tournee;

public class EntrepriseTourneeQteByDemande {

    private String raisonSocialeEntreprise;
    private int idtournee;
    private DechetsEnleves dechetsEnleves;

    public EntrepriseTourneeQteByDemande() {
    }

    public String getRaisonSocialeEntreprise() {
        return raisonSocialeEntreprise;
    }

    public void setRaisonSocialeEntreprise(String raisonSocialeEntreprise) {
        this.raisonSocialeEntreprise = raisonSocialeEntreprise;
    }

    public int getTournee() {
        return idtournee;
    }

    public void setTournee(int tournee) {
        this.idtournee = tournee;
    }

    public DechetsEnleves getDechetsEnleves() {
        return dechetsEnleves;
    }

    public void setDechetsEnleves(DechetsEnleves dechetsEnleves) {
        this.dechetsEnleves = dechetsEnleves;
    }
}
