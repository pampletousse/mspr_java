package com.epsi.msprjava.controler;

import com.epsi.msprjava.bdd.OracleConnexion;
import com.epsi.msprjava.model.Demande;
import com.epsi.msprjava.model.Employe;
import com.epsi.msprjava.model.TypeDechet;
import com.epsi.msprjava.scenes.Dashboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
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
/*        labelWelcome.setText("AHOJ");
        labelWelcome.setText("fdsf");
        System.out.println(labelWelcome.getLabelFor());*/
    }

    public List<Demande> getListDemandesApresDate(Date date) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where datedemande > ?");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TypeDechet> getQteTotaleDechetByDate(Date date) {
        return null;
    }

    public List<Demande> getDemandesNonAffectees() {
        return null;
    }

    public List<Employe> getEmployesMoinsTournees(int nbTournees) {
        return null;
    }

    public int getQteTotaleByTypeDateSite(int idTypeDechet, Date date, int idSite) {
        return 0;
    }

    public int getQteTotaleByTypePeriodeAllSites(int idTypeDechet, Date dateDebut, Date dateFin) {
        return 0;
    }

    public void affectTournees() {

    }
}
