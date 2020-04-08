package com.epsi.msprjava.controler;

import com.epsi.msprjava.Main;
import com.epsi.msprjava.model.Profil;
import com.epsi.msprjava.model.User;
import com.epsi.msprjava.scenes.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

public class ConnexionController {

    @FXML
    private TextField stringUser;

    @FXML
    private TextField stringPass;

    @FXML
    private Button btnConnexion;

    @FXML
    private Label labelError;

    @FXML
    public void clickConnexion(ActionEvent actionEvent) throws Exception {

        String user = stringUser.getText();
        String pass = stringPass.getText();

        System.out.println(user);
        System.out.println(pass);

        if (checkUser(user, pass)) {
            System.out.println("connected");
            Profil profil = new Profil(1, "admin");
            User u = new User(user, pass, profil);
            Stage stage = (Stage) btnConnexion.getScene().getWindow();
            DashboardController.start(stage);
        } else {
            System.out.println("erreur");
        }
    }

    public boolean checkUser(String user, String pass) {
        if (user.equals("user") && pass.equals("pass")) {
            return true;
        } else {
            return false;
        }
    }
}
