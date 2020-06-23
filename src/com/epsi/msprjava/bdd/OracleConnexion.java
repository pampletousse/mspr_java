package com.epsi.msprjava.bdd;

import java.sql.*;

public class OracleConnexion {

    public OracleConnexion() {
    }

    public Connection connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connexion = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "RECYCLE", "pass");

            if (connexion != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Not connected");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}