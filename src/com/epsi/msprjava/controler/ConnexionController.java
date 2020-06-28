package com.epsi.msprjava.controler;

import com.epsi.msprjava.bdd.OracleConnexion;
import com.epsi.msprjava.model.Employe;
import com.epsi.msprjava.scenes.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnexionController {

    @FXML
    private TextField stringUser;

    @FXML
    private TextField stringPass;

    @FXML
    private Button btnConnexion;

    @FXML
    private Label labelError;

    private OracleConnexion oracleConnexion = new OracleConnexion();

    private ErrorController errorController;

    //private static SessionFactory sessionFactory;
    public ConnexionController() {

    }

    public void clickConnexion(ActionEvent actionEvent) throws Exception {

        String user = stringUser.getText();
        String pass = stringPass.getText();

        if (checkUser(user, pass)) {
            OracleConnexion oracleConnexion = new OracleConnexion();
            oracleConnexion.connect();

            Stage stage = (Stage) btnConnexion.getScene().getWindow();
            DashboardController dashControl = new DashboardController();
            dashControl.start(stage);
        } else {
            System.out.println("erreur");
        }
    }

    // Fonction de connexion check le user en bdd
    private boolean checkUser(String user, String pass) {
        try {
            Connection connexion = oracleConnexion.connect();
            int type = ResultSet.TYPE_SCROLL_INSENSITIVE;
            int mode = ResultSet.CONCUR_UPDATABLE;
            //Statement stmt = connexion.createStatement(type,mode);
            PreparedStatement stmt = connexion.prepareStatement("select * from employe where login = ? and motpass = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, user);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                Employe connectedUser = new Employe();
                connectedUser.setIdEmploye(rs.getShort(1));
                connectedUser.setNom(rs.getString(2));
                connectedUser.setDateembauche(rs.getTime(3));
                connectedUser.setDatenaiss(rs.getTime(4));
                connectedUser.setIdProfil(rs.getInt(5));
                connectedUser.setPrenom(rs.getString(6));
                connectedUser.setSalaire(rs.getLong(7));
                connectedUser.setMotpass(rs.getString(8));
                connexion.close();
                return true;
            } else {
                connexion.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
