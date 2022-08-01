package com.example.kassaapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onClickedCancelBtn(ActionEvent event) {

    }

    @FXML
    void onClickedSaveBtn(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert txtLogin != null : "fx:id=\"txtLogin\" was not injected: check your FXML file 'avto.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'avto.fxml'.";

    }

}
