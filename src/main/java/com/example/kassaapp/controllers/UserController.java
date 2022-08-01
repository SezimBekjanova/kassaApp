package com.example.kassaapp.controllers;

import java.io.IOException;

import com.example.kassaapp.Main;
import com.example.kassaapp.models.User;
import com.example.kassaapp.services.UserService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserController {
    @FXML
    private TableColumn<String, User> colmLogin;

    @FXML
    private TableColumn<String, User> colmName;

    @FXML
    private TableColumn<String, User> colmPosition;

    @FXML
    private MenuItem mnAdd;

    @FXML
    private MenuItem mnDelete;

    @FXML
    private MenuItem mnEdit;

    @FXML
    private TableView<User> tbUser;

    @FXML
    void onClickedMenu(ActionEvent event) {
        if(event.getSource().equals(mnAdd)){
            showAdd();
        }else if(event.getSource().equals(mnEdit)){
            showEdit();
        }else if(event.getSource().equals(mnDelete)){
            showDelete(tbUser.getSelectionModel().getSelectedItem());
        }
    }

    private void showDelete(User selectedItem) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены?", ButtonType.YES,ButtonType.NO);
        ButtonType result = alert.showAndWait().get();
        if(result.equals(ButtonType.NO)){

        }
        if(result.equals(ButtonType.YES)) {
            System.out.println("LOG -- " + selectedItem.getId());
            UserService.INSTANCE.deleteUser(selectedItem.getId());
            refreshTableUsers();
        }
    }

    private void showEdit() {
        showForm(tbUser.getSelectionModel().getSelectedItem());
    }

    private void showAdd() {
        showForm(new User());
    }
    private void showForm(User user){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("userEditForm.fxml"));
        try {
            Scene scene=new Scene(fxmlLoader.load());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            UserEditController controller = fxmlLoader.getController();
            controller.setUser(user);
            stage.setScene(scene);
            stage.showAndWait();
            refreshTableUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colmPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        refreshTableUsers();
    }

    private void refreshTableUsers() {
        tbUser.refresh();
        tbUser.setItems(FXCollections.observableList(UserService.INSTANCE.findAllUsers()));
    }
}
