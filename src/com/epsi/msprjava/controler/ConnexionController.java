package com.epsi.msprjava.controler;

import com.epsi.msprjava.Main;
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
    private Dashboard dashboard;

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

        if (user.equals("user") && pass.equals("pass")) {
            System.out.println("connected");
            User u = new User(user, pass);
            dashboard = new Dashboard(u);
            Stage stage = (Stage) btnConnexion.getScene().getWindow();
            DashboardController.start(stage);
        } else {
            //labelError.setText("Erreur");
            System.out.println("erreur");
        }
    }
}
