package com.epsi.msprjava.scenes;

import com.epsi.msprjava.controler.DashboardController;
import com.epsi.msprjava.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Dashboard {

    //private DashboardController controler;

    private User user;


    public static void start(Stage window) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = FXMLLoader.load(Dashboard.class.getResource("../views/dashboard.fxml"));
        //DashboardController controler = (DashboardController) fxmlLoader.getController();
        Scene scene = new Scene(root, 800, 700);
        window.setScene(scene);
        window.show();
    }
}