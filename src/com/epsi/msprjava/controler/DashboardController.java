package com.epsi.msprjava.controler;

import com.epsi.msprjava.scenes.Dashboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Label labelWelcome;

    public void start(Stage window) throws Exception {
        //Dashboard.start(window);
        Parent root = FXMLLoader.load(Dashboard.class.getResource("../views/dashboard.fxml"));
        Scene scene = new Scene(root, 800, 700);
        window.setScene(scene);
        window.show();
        labelWelcome.setText("AHOJ");
        labelWelcome.setText("fdsf");
        System.out.println(labelWelcome.getLabelFor());
    }
}
