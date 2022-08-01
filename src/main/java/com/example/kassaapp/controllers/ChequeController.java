package com.example.kassaapp.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.kassaapp.models.OperationProducts;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ChequeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OperationProducts> tbListPurchase;

    @FXML
    private TextField txtChange;

    @FXML
    private TextField txtSum;

    @FXML
    private TextField txtTotal;
    public void setData(double change, List<OperationProducts> operationProductsList) {
        txtChange.setText(String.valueOf(change));
        tbListPurchase.setItems(FXCollections.observableList(operationProductsList));

    }

    @FXML
    void initialize() {

    }

}


