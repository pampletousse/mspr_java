package com.epsi.msprjava.bdd;

import com.epsi.msprjava.controler.ErrorController;
import javafx.stage.Stage;

import java.sql.*;

public class OracleConnexion {

    private ErrorController errorController;

    public OracleConnexion() {
    }

    public Connection connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connexion = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "RECYCLE", "pass");

            if (connexion != null) {
                System.out.println("Connected to database");
            } else {
                System.out.println("Error connecting to database");
                errorController.start(new Stage(), "Erreur de connexion à la base de données");
            }
            return connexion;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnexion(Connection connexion) {
        try {
            connexion.close();
            errorController.start(new Stage(), "Erreur de connexion à la base de données");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}