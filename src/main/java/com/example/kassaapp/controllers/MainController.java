package com.example.kassaapp.controllers;
import com.example.kassaapp.Main;
import com.example.kassaapp.models.OperationProducts;
import com.example.kassaapp.models.Product;
import com.example.kassaapp.services.OperationService;
import com.example.kassaapp.services.ProductService;
import com.example.kassaapp.services.impl.OperationServiceImpl;
import com.example.kassaapp.services.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    List<OperationProducts> operationProductsList= new ArrayList<>();
    @FXML
    private MenuItem mnItemCategories;

    @FXML
    private MenuItem mnItemDiscounts;

    @FXML
    private MenuItem mnItemProducts;

    @FXML
    private MenuItem mnItemsUsers;
    @FXML
    private TextField txtBarcode;

    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtResult;

    @FXML
    private TextField txtSum;
    @FXML
    private TableColumn<String, OperationProducts> colmAmount;

    @FXML
    private TableColumn<String, OperationProducts> colmDiscount;

    @FXML
    private TableColumn<String, OperationProducts> colmProduct;
    @FXML
    private TableColumn<String, OperationProducts> columTotal;

    @FXML
    private TableColumn<String, OperationProducts> colmUnit;

    @FXML
    private TableColumn<String, OperationProducts> columPrice;
    @FXML
    private TableView<OperationProducts> tbOperations;

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if(event.getSource().equals(mnItemCategories)){
            showCategories();
        }if(event.getSource().equals(mnItemProducts)){
            showProducts();
        }if(event.getSource().equals(mnItemsUsers)){
            showUsers();

        }

    }

    private void showUsers() {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("userForm.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showProducts() {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("productForm.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showCategories() {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("categoriesForm.fxml"));
        try {
            Scene scene=new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onClickedEnter(ActionEvent event) {
        Product product = ProductService.INSTANCE.findProductByBarcode(txtBarcode.getText());
        addProductToList(product);
        refreshList();
        txtBarcode.setText(null);
    }

    @FXML
    void onClickedClear(ActionEvent event) {
        if (txtSum.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Введите сумму для оплаты!");
            alert.show();
            return;
        } else if (Double.parseDouble(txtSum.getText().trim()) >= Double.parseDouble(txtTotal.getText().trim())) {
            boolean isSaveResult = OperationService.INSTANCE.closeAndSaveOperation(Double.parseDouble(txtTotal.getText()), 0, operationProductsList);
            if (!isSaveResult) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ошибка при закрытии операции!");
                alert.show();
                return;
            }

            Stage stage = new Stage();
            FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("chequeForm.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);

                ChequeController controller = fxmlLoader.getController();
                controller.setData(Double.parseDouble(txtSum.getText().trim()) - Double.parseDouble(txtTotal.getText().trim())
                        , operationProductsList);

                stage.showAndWait();
                operationProductsList.clear();
                refreshList();
                txtSum.clear();
                txtBarcode.clear();
                txtTotal.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Не хватате денег для закрытия оперции!");
            alert.show();
        }

      /*  List<OperationProducts> listItems = new ArrayList<>(tbOperations.getItems());
        tbOperations.refresh();
        operationProductsList.clear();
        txtResult.setText(null);
        txtTotal.setText(null);
        txtSum.setText(null);
        txtBarcode.setText(null);
        
       */
    }
    @FXML
    void onClickedCancel(ActionEvent event) {
    txtSum.setText(null);
    }

    @FXML
    void onClickedResult(ActionEvent event) {
        double result;
        result= Double.parseDouble(txtSum.getText())-Double.parseDouble(txtTotal.getText());
        txtResult.setText(String.valueOf(result));



    }
    private void addProductToList(Product product){
            for (int i=0;i<operationProductsList.size();i++){
                OperationProducts operationProducts=operationProductsList.get(i);
                if(operationProducts.getProduct().getId().equals(product.getId())){
                    operationProducts.setAmount(operationProducts.getAmount()+1);
                    operationProducts.setTotal(operationProducts.getAmount()*operationProducts.getPriceWithDiscount());
                    operationProductsList.set(i,operationProducts);
                    return;
                }

        }
        OperationProducts operationProducts= new OperationProducts();
        operationProducts.setAmount(1);
        operationProducts.setPriceWithDiscount(product.getPrice()*(100- product.getDiscount()/100));
        operationProducts.setTotal(operationProducts.getAmount()*operationProducts.getPriceWithDiscount());
        operationProducts.setProduct(product);
        operationProductsList.add(operationProducts);
    }

    private void refreshList() {
        tbOperations.refresh();
        tbOperations.setItems(FXCollections.observableList(operationProductsList));
        double total= 0;
        for (OperationProducts item:operationProductsList) {
            total+=item.getTotal();
        }
        txtTotal.setText(String.valueOf(total));
    }


    @FXML
    void initialize() {
        colmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colmProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        columPrice.setCellValueFactory(new PropertyValueFactory<>("priceWithDiscount"));
        columTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


    }

}