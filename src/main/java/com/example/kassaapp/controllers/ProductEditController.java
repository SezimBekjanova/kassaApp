package com.example.kassaapp.controllers;

import com.example.kassaapp.models.Category;
import com.example.kassaapp.models.Product;
import com.example.kassaapp.models.Unit;
import com.example.kassaapp.services.impl.CategoryServiceImpl;
import com.example.kassaapp.services.impl.ProductServiceImpl;
import com.example.kassaapp.services.impl.UnitServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ProductEditController {
    private Product product;
    private CategoryServiceImpl categoryService= CategoryServiceImpl.
    private UnitServiceImpl unitService= UnitServiceImpl.getINSTANCE();

    @FXML
    private ComboBox<Category> combCategory;

    @FXML
    private ComboBox<Unit> combUnit;

    public void setProduct(Product product) {
        this.product = product;
        if(product.getId()!=null){
            txtName.setText(product.getName());
            txtAmount.setText(String.valueOf(product.getAmount()));
            txtDiscount.setText(String.valueOf(product.getDiscount()));
            txtBarcode.setText(product.getBarcode());
            txtPrice.setText(String.valueOf(product.getPrice()));
            combCategory.getSelectionModel().select(product.getCategory());
            combUnit.getSelectionModel().select(product.getUnit());
        }
    }

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtBarcode;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    void onBtnClickedCancel(ActionEvent event) {

    }
    @FXML
    void onBtnClickedSave(ActionEvent event) {
        double price = Double.parseDouble(txtPrice.getText());
        String name = txtName.getText();
        String barcode = txtBarcode.getText();
        int discount = Integer.parseInt(txtDiscount.getText());
        double amount = Double.parseDouble(txtAmount.getText());
        Category category = combCategory.getSelectionModel().getSelectedItem();
        Unit unit = combUnit.getSelectionModel().getSelectedItem();
        product.setName(name);
        product.setAmount(amount);
        product.setPrice(price);
        product.setBarcode(barcode);
        product.setDiscount(discount);
        product.setCategory(category);
        product.setUnit(unit);
        ProductServiceImpl.getInstance().save(product);
    }

    @FXML
    void initialize() {
        combCategory.setItems(FXCollections.observableList(categoryService.getCategories()));
        combUnit.setItems(FXCollections.observableList(unitService.getUnits()));

    }

}
