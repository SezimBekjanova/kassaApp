package com.example.kassaapp.controllers;

import com.example.kassaapp.Main;
import com.example.kassaapp.models.Product;
import com.example.kassaapp.services.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductController {
    //ProductServiceImpl productService=ProductServiceImpl.getInstance();
    @FXML
    private TableView<Product> tbProduct;


    @FXML
    private TableColumn<String, Product> colmAmount;

    @FXML
    private TableColumn<String, Product> colmBarcode;

    @FXML
    private TableColumn<String, Product> colmDiscount;

    @FXML
    private TableColumn<String, Product> colmName;

    @FXML
    private TableColumn<String, Product> colmPrice;

    @FXML
    private MenuItem mnAdd;

    @FXML
    private MenuItem mnDelete;

    @FXML
    private MenuItem mnEdit;
    @FXML
    private TableColumn<String,Product> colmUnit;

    @FXML
    private TableColumn<String,Product> colmCategory;

    @FXML
    void onClickedMenuItem(ActionEvent event) {
        if(event.getSource().equals(mnAdd)){
            showAddProduct();
        }else if(event.getSource().equals(mnEdit)){
            showEditProduct();
        }else if(event.getSource().equals(mnDelete)){
            showDeleteProduct();
        }

    }

    private void showDeleteProduct() {
    }

    private void showEditProduct() {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("productEditForm.fxml"));
        try {
            Scene scene =new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            ProductEditController controller=fxmlLoader.getController();
            controller.setProduct(tbProduct.getSelectionModel().getSelectedItem());
            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAddProduct() {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("productEditForm.fxml"));
        try {
            Scene scene =new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            ProductEditController controller=fxmlLoader.getController();
            controller.setProduct(new Product());
            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        colmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colmBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colmDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colmUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        refreshTable();
    }

    private void refreshTable() {
        tbProduct.setItems(FXCollections.observableList(ProductServiceImpl.getInstance().getProducts()));

    }

}


