package com.epsi.msprjava.controler;

import com.epsi.msprjava.bdd.OracleConnexion;
import com.epsi.msprjava.model.Demande;
import com.epsi.msprjava.model.Employe;
import com.epsi.msprjava.model.TypeDechet;
import com.epsi.msprjava.scenes.Dashboard;
import com.epsi.msprjava.viewmodel.DechetsEnleves;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.Proxy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    @FXML
    private Label labelWelcome;

    private OracleConnexion oracleConnexion = new OracleConnexion();

    public DashboardController() {

    }

    public void start(Stage window) throws Exception {
        //Dashboard.start(window);
        Parent root = FXMLLoader.load(Dashboard.class.getResource("../views/dashboard.fxml"));
        Scene scene = new Scene(root, 800, 700);
        window.setScene(scene);
        window.show();
/*      labelWelcome.setText("AHOJ");
        labelWelcome.setText("fdsf");
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
                d.setIdSite(rs.getInt(4));
                d.setIdTournee(rs.getLong(5));
                d.setSiret(rs.getLong(6));
                listedemandes.add(d);
            }
            connexion.close();
            return listedemandes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de déchet par type pour une période donnée
    public DechetsEnleves getQteTotaleDechetByDate(Date date) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                    "inner join demande d on dd.id_demande = d.id_demande " +
                    "inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                    "where d.datedemande between ? and ? " +
                    "group by dd.id_type_dechet");
            stmt.setDate(1, date);
            stmt.setDate(2, date);
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

    // Récupération des demandes non affectées à une tournée
    public List<Demande> getDemandesNonAffectees() {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where id_tournee = null");
            ResultSet rs = stmt.executeQuery();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération des employés ayant effectués moins de n tournées
    public List<Employe> getEmployesMoinsTournees(int nbTournees) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select *" +
                    "FROM employe" +
                    "WHERE id_employe IN (" +
                    "select id_employe" +
                    "from tournee " +
                    "group by id_employe" +
                    "HAVING COUNT(id_employe) < ?" +
                    ");");
            stmt.setInt(1, nbTournees);
            ResultSet rs = stmt.executeQuery();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de dechet par type periode et site
    public int getQteTotaleByTypeDateSite(int idTypeDechet, Date date, int idSite) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where id_tournee = null");
            ResultSet rs = stmt.executeQuery();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Récupération de la quantité totale de déchet par type et période sur tout les sites
    public int getQteTotaleByTypePeriodeAllSites(int idTypeDechet, Date dateDebut, Date dateFin) {
        return 0;
    }

    // Affectation des tournées
    public void affectTournees() {
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
                td.setNivDanger(rs.getBoolean(2));
                td.setNom(rs.getString(3));
                return td;
            }
            connexion.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
