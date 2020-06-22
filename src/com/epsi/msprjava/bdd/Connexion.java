package com.epsi.msprjava.bdd;

import java.sql.*;

public class Connexion {

    public Connexion() {
    }

    public void connect() {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "RECYCL", "password");

            if (con != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Not connected");
            }
            //step3 create the statement object
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from profil");

            while (rs.next()) {
                System.out.println(rs.getInt("id_profil") + "  " + rs.getString("nomprofil"));
            }

            //step5 close the connection object
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}