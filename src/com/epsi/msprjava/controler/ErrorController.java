package com.epsi.msprjava.controler;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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

    private SimpleStringProperty errorValue = new SimpleStringProperty();

    private String message;

    public ErrorController() {
    }

    public ErrorController(String message) {
        this.message = message;
    }

    public void initialize() {
        errorValue.set(message);
        stringError.textProperty().bind(Bindings.convert(errorValue));
    }

    public static void start(Stage window, String message) throws Exception {

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
}
