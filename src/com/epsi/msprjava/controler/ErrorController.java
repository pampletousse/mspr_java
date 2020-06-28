package com.epsi.msprjava.controler;

import com.epsi.msprjava.scenes.Dashboard;
import com.epsi.msprjava.scenes.ModalError;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController {

    @FXML
    private static Label stringError = new Label();

    private static StringProperty errorMessage;

    //private static SimpleStringProperty stringErrorrProperty = new SimpleStringProperty("kikoo");

    public static void start(Stage window, String message) throws Exception {

        errorMessage = new SimpleStringProperty(message);
        stringError.textProperty().bind(errorMessageProperty());

        Parent root = FXMLLoader.load(ErrorController.class.getResource("../views/modalerror.fxml"));
        Scene scene = new Scene(root, 400, 300);
        window.setScene(scene);
        window.show();


        stringError.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                System.out.println("Label Text Changed");
            }
        });

    }

    public static StringProperty errorMessageProperty() {
        return errorMessage;
    }
}
