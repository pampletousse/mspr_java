package com.epsi.msprjava.scenes;

import com.epsi.msprjava.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Dashboard {

    @FXML
    private Label labelWelcome;

    private User user;

    public Dashboard(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        labelWelcome.setText("AHOJ");
    }

    public static void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(Dashboard.class.getResource("../views/dashboard.fxml"));
        Scene scene = new Scene(root, 800, 700);
        window.setScene(scene);
        window.show();
    }
}