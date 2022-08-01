package com.example.kassaapp.controllers;


import com.example.kassaapp.models.Category;
import com.example.kassaapp.services.CategoryService;
import com.example.kassaapp.services.impl.CategoryServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CategoryEditController {
    //private CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();
    private Category category;
    public void setCategory(Category category){
        this.category=category;
        if(category!=null){
            txtName.setText(category.getName());
        }
    }

    @FXML
    private TextField txtName;

    @FXML
    void onClickedCancel(ActionEvent event) {

    }

    @FXML
    void onClickedSave(ActionEvent event) {
        String categoryName = txtName.getText();
        category.setName(categoryName);

        String result = "успешно!";
        Alert.AlertType alertType = Alert.AlertType.INFORMATION;
        try {
            CategoryService.INSTANCE.save(category);
            txtName.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            switch (e.getErrorCode()){
                case 19:
                    result="Название должно быть уникальным!";
                    break;
                case 0:
                    result="Ошибка подключения к базы данных";
                    break;
                default:
                    result= "Системная ошибка!";
                    alertType= Alert.AlertType.ERROR;
            }
        }finally {
            Alert alert=new Alert(alertType,result, ButtonType.OK);
            alert.showAndWait();
        }


    }

    @FXML
    void initialize() {

    }

}
