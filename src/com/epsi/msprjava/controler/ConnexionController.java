package com.epsi.msprjava.controler;

import com.epsi.msprjava.bdd.Connexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ConnexionController {

    @FXML
    private TextField stringUser;

    @FXML
    private TextField stringPass;

    @FXML
    private Button btnConnexion;

    @FXML
    private Label labelError;

    //private static SessionFactory sessionFactory;
    public ConnexionController() {

    }

    public void clickConnexion(ActionEvent actionEvent) throws Exception {

        String user = stringUser.getText();
        String pass = stringPass.getText();

        System.out.println(user);
        System.out.println(pass);

        if (checkUser(user, pass)) {
            Connexion connexion = new Connexion();
            connexion.connect();


            //System.out.println("connected");
            //Profil profil = new Profil(1, "admin");
            //User u = new User(user, pass, profil);
            Stage stage = (Stage) btnConnexion.getScene().getWindow();
            DashboardController dashControl = new DashboardController();
            dashControl.start(stage);
        } else {
            System.out.println("erreur");
        }
    }

    private boolean checkUser(String user, String pass) {
        if (user.equals("user") && pass.equals("pass")) {
            return true;
        } else {
            return false;
        }
    }
}
