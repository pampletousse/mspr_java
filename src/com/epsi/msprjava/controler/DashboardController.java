package com.epsi.msprjava.controler;

import com.epsi.msprjava.bdd.OracleConnexion;
import com.epsi.msprjava.model.*;
import com.epsi.msprjava.scenes.Dashboard;
import com.epsi.msprjava.viewmodel.DechetsEnleves;
import com.epsi.msprjava.viewmodel.EntrepriseTourneeQteByDemande;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.WhileNode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardController {

    @FXML
    private Label labelWelcome = new Label();

    private OracleConnexion oracleConnexion = new OracleConnexion();

    private ErrorController errorController;

    private SimpleStringProperty value = new SimpleStringProperty();

    public DashboardController() {

    }

    public void start(Stage window) throws Exception {
        //Dashboard.start(window);
        Parent root = FXMLLoader.load(DashboardController.class.getResource("../views/dashboard.fxml"));
        Scene scene = new Scene(root, 800, 700);
        window.setScene(scene);
        window.show();
        value.set("fdjsofsd");
        labelWelcome.textProperty().bind(Bindings.convert(value));
        callErrorModale("test");

        // Fonction 1
        String str = "2018-10-10";
        java.sql.Date date = java.sql.Date.valueOf(str);
        List<Demande> ld = getListDemandesApresDate(date);

        // Fonction 2
        String str1 = "2018-09-10";
        java.sql.Date date1 = java.sql.Date.valueOf(str1);
        String str2 = "2018-11-10";
        java.sql.Date date2 = java.sql.Date.valueOf(str2);
        DechetsEnleves de = getQteTotaleDechetByDate(date1, date2);

        // Fonction 2
        EntrepriseTourneeQteByDemande etq = getInformationsByDemande(1);

        // Fonction 3
        List<Demande> ldd = getDemandesNonAffectees();

        // Fonction 4
        List<Employe> le = getEmployesMoinsTournees(4);

        // Fonction 5
        DechetsEnleves dee = getQteTotaleByTypeDateSite(1, date1, date2, 1);

        // Fonction 6
        //getQteTotaleByTypePeriodeAllSites();

        // Fonction 7
        //affectTournees();


/*      labelWelcome.setText("AHOJ");
        labelWelcome.setText("fdsf");
        labelWelcome.textProperty().bind(secondsProperty.asString("%f seconds left"));
        System.out.println(labelWelcome.getLabelFor());*/

        // test fonctions

    }

    // Récupération des demandes après une date donnée
    public List<Demande> getListDemandesApresDate(java.sql.Date date) {
        List listedemandes = new ArrayList<Demande>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where datedemande > ?");
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Demande d = new Demande();
                d.setIddemande(rs.getLong(1));
                d.setDatedemande(rs.getTime(2));
                d.setDateenlevement(rs.getTime(3));
                d.setIdEntreprise(rs.getInt(4));
                d.setIdTournee(rs.getLong(5));
                d.setWebON(rs.getString(6));
                d.setIdSite(rs.getInt(7));
                listedemandes.add(d);
            }
            connexion.close();
            return listedemandes;
        } catch (Exception e) {
            callErrorModale("Une erreur est survenue");
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de déchet par type pour une période donnée
    public DechetsEnleves getQteTotaleDechetByDate(Date date1, Date date2) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement(
                    "select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                    "inner join demande d on dd.id_demande = d.id_demande " +
                    "inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                    "where d.datedemande between ? and ? " +
                    "group by dd.id_type_dechet");
            stmt.setDate(1, date1);
            stmt.setDate(2, date2);
            ResultSet rs = stmt.executeQuery();

            DechetsEnleves dechetsEnleves = new DechetsEnleves();

            while (rs.next()) {
                TypeDechet td = getTypeDechetById(rs.getInt(1));
                dechetsEnleves.getMapDechetsEnleves().put(td, rs.getLong(2));
            }
            connexion.close();
            return dechetsEnleves;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EntrepriseTourneeQteByDemande getInformationsByDemande(int demandeid) {
        EntrepriseTourneeQteByDemande etq = new EntrepriseTourneeQteByDemande();
        DechetsEnleves de = new DechetsEnleves();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement(
                    "select id_tournee, raisonsociale from demande d " +
                            "inner join entreprise e on d.id_entreprise = e.id_entreprise " +
                            "where id_demande = ? ");
            stmt.setInt(1, demandeid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                etq.setTournee(rs.getInt(1));
                etq.setRaisonSocialeEntreprise(rs.getString(2));
            }

            PreparedStatement stmt2 = connexion.prepareStatement(
                    "select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                            "inner join demande d on dd.id_demande = d.id_demande " +
                            "inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                            "where dd.id_demande = ? " +
                            "group by dd.id_type_dechet "
            );
            stmt2.setInt(1, demandeid);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                TypeDechet td = getTypeDechetById(rs2.getInt(1));
                de.getMapDechetsEnleves().put(td, rs2.getLong(2));
            }
            etq.setDechetsEnleves(de);
            connexion.close();
            return etq;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupération des demandes non affectées à une tournée
    public List<Demande> getDemandesNonAffectees() {
        List listedemandes = new ArrayList<Demande>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where id_tournee IS NULL");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Demande d = new Demande();
                d.setIddemande(rs.getLong(1));
                d.setDatedemande(rs.getTime(2));
                d.setDateenlevement(rs.getTime(3));
                d.setIdEntreprise(rs.getInt(4));
                d.setIdTournee(rs.getLong(5));
                d.setWebON(rs.getString(6));
                d.setIdSite(rs.getInt(7));
                listedemandes.add(d);
            }
            connexion.close();
            return listedemandes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération des employés ayant effectués moins de n tournées
    public List<Employe> getEmployesMoinsTournees(int nbTournees) {
        List listeEmployes = new ArrayList<Employe>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement(
                    "select *" +
                            "FROM employe " +
                            "WHERE id_employe IN (" +
                            "select id_employe " +
                            "from tournee " +
                            "group by id_employe " +
                            "HAVING COUNT(id_employe) < ? " +
                            ")");
            stmt.setInt(1, nbTournees);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employe e = new Employe();
                e.setIdEmploye(rs.getShort(1));
                e.setNom(rs.getString(2));
                e.setPrenom(rs.getString(3));
                e.setDatenaiss(rs.getTime(4));
                e.setDateembauche(rs.getTime(5));
                e.setSalaire(rs.getLong(6));
                e.setIdProfil(rs.getInt(7));
                listeEmployes.add(e);
            }
            connexion.close();
            return listeEmployes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de dechet par type periode et/ou site
    public DechetsEnleves getQteTotaleByTypeDateSite(int idTypeDechet, Date dateDebut, Date dateFin, int idSite) {
        try {
            Connection connexion = oracleConnexion.connect();
            String sql =
                    "select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                            " inner join demande d on dd.id_demande = d.id_demande " +
                            " inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                            " where dd.id_type_dechet = ? and d.datedemande between ? and ? ";

            if (idSite != -1) {
                // Recherche sur un site précis
                sql += " and d.id_site = ? ";
            }
            sql += " group by dd.id_type_dechet ";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, idTypeDechet);
            stmt.setDate(2, dateDebut);
            stmt.setDate(3, dateFin);
            stmt.setInt(4, idSite);

            ResultSet rs = stmt.executeQuery();
            DechetsEnleves dechetsEnleves = new DechetsEnleves();
            while (rs.next()) {
                TypeDechet td = getTypeDechetById(rs.getInt(1));
                dechetsEnleves.getMapDechetsEnleves().put(td, rs.getLong(2));
            }
            connexion.close();
            return dechetsEnleves;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de déchet par type et période sur tout les sites
    public int getQteTotaleByTypePeriodeAllSites(int idTypeDechet, Date dateDebut, Date dateFin) {
        return 0;
    }


    // Affectation des tournées
    public void affectTournees(Date datedemandee) {
        // Inscription dans une tournée déjà créée pour la date demandée
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from tournee where detatournee = ?");
            stmt.setDate(1, datedemandee);
            ResultSet rs = stmt.executeQuery();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public TypeDechet getTypeDechetById(int idTypeDechet) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from type_dechet where id_type_dechet = ?");
            stmt.setInt(1, idTypeDechet);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TypeDechet td = new TypeDechet();
                td.setIdTypeDechet(rs.getInt(1));
                td.setNom(rs.getString(2));
                td.setNivDanger(rs.getBoolean(3));
                return td;
            }
            connexion.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Tournee getTourneeById(int idTournee) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from tournee where id_type_dechet = ?");
            stmt.setInt(1, idTournee);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tournee t = new Tournee();
                t.setIdTournee(rs.getLong(1));
                t.setDatetournee(rs.getTime(2));
                t.setIdimmatric(rs.getString(3));
                t.setIdEmploye(rs.getShort(4));
                return t;
            }
            connexion.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void callErrorModale(String message) {
        try {
            errorController.start(new Stage(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
