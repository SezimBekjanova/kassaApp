package com.example.kassaapp.controllers;

import com.example.kassaapp.Main;
import com.example.kassaapp.models.Category;
import com.example.kassaapp.services.CategoryService;
import com.example.kassaapp.services.impl.CategoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CategoryController {
    //private CategoryServiceImpl categoryService= CategoryServiceImpl.getInstance();

    @FXML
    private ListView<Category> listViewCategories;

    @FXML
    private MenuItem mnAdd;

    @FXML
    private MenuItem mnDelete;

    @FXML
    private MenuItem mnEdit;

    @FXML
    void onClickedMenuItem(ActionEvent event) {
        if(event.getSource().equals(mnAdd)){
            showAdd();
        }else if(event.getSource().equals(mnDelete)){
            showDelete();
        }else if(event.getSource().equals(mnEdit)){
            showEdit();
        }
    }

    private void showAdd() {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("categoryEditForm.fxml"));
        try {
            Scene scene=new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CategoryEditController controller=fxmlLoader.getController();
            controller.setCategory(new Category());
            stage.setResizable(false);
            stage.showAndWait();
            refreshList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDelete() {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены?", ButtonType.YES,ButtonType.NO);
        ButtonType result = alert.showAndWait().get();
        if(result.equals(ButtonType.NO)){

        }
        if(result.equals(ButtonType.YES)){
            Category category= listViewCategories.getSelectionModel().getSelectedItem();
            CategoryService.INSTANCE.delete(category.getId());
            refreshList();
        }
    }


    private void showEdit() {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("categoryEditForm.fxml"));
        try {
            Scene scene=new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            Category category=listViewCategories.getSelectionModel().getSelectedItem();
            CategoryEditController controller=fxmlLoader.getController();
            controller.setCategory(category);
            stage.showAndWait();
            refreshList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        refreshList();// обращается к бд
    }

    private void refreshList() {
        List<Category> categories= CategoryService.INSTANCE.getCategories();
        listViewCategories.setItems(FXCollections.observableList(categories));
    }

}

