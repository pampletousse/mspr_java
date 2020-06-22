package com.epsi.msprjava.model;

import java.sql.Time;
import java.util.Objects;

public class Tournee {
    private Long idTournee;
    private Time datetournee;
    private String idimmatric;
    private Short idEmploye;
    private Camion camionByIdimmatric;
    private Employe employeByIdEmploye;

    public Long getIdTournee() {
        return idTournee;
    }

    public void setIdTournee(Long idTournee) {
        this.idTournee = idTournee;
    }

    public Time getDatetournee() {
        return datetournee;
    }

    public void setDatetournee(Time datetournee) {
        this.datetournee = datetournee;
    }

    public String getIdimmatric() {
        return idimmatric;
    }

    public void setIdimmatric(String idimmatric) {
        this.idimmatric = idimmatric;
    }

    public Short getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Short idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Camion getCamionByIdimmatric() {
        return camionByIdimmatric;
    }

    public void setCamionByIdimmatric(Camion camionByIdimmatric) {
        this.camionByIdimmatric = camionByIdimmatric;
    }

    public Employe getEmployeByIdEmploye() {
        return employeByIdEmploye;
    }

    public void setEmployeByIdEmploye(Employe employeByIdEmploye) {
        this.employeByIdEmploye = employeByIdEmploye;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournee tournee = (Tournee) o;
        return Objects.equals(idTournee, tournee.idTournee) &&
                Objects.equals(datetournee, tournee.datetournee) &&
                Objects.equals(idimmatric, tournee.idimmatric) &&
                Objects.equals(idEmploye, tournee.idEmploye);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTournee, datetournee, idimmatric, idEmploye);
    }

}
