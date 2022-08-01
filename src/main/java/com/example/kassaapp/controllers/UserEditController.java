package com.example.kassaapp.controllers;

import com.example.kassaapp.models.Position;
import com.example.kassaapp.models.User;
import com.example.kassaapp.services.PositionService;
import com.example.kassaapp.services.UserService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserEditController {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println(user);
        this.user = user;

        if (this.user.getId() != null) {
            System.out.println(123123123);
            txtName.setText(this.user.getName());
            txtLogin.setText(this.user.getLogin());
            txtPassword.setText(this.user.getPassword());
            combPosition.getSelectionModel().select(this.user.getPosition());
        }
    }

    @FXML
    private ComboBox<Position> combPosition;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtName;

    @FXML
    private  PasswordField txtPassword;

    @FXML
    void onClickedCancel(ActionEvent event) {

    }

    @FXML
    void onClickedSave(ActionEvent event) {
        String name = txtName.getText().trim();
        String login = txtLogin.getText().trim();
        String password = txtPassword.getText().trim();
        Position position = combPosition.getSelectionModel().getSelectedItem();
        user.setPassword(password);
        user.setPosition(position);
        user.setName(name);
        user.setLogin(login);
        boolean isResult = UserService.INSTANCE.addOrEditUser(user);
        if (isResult) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "пользователь сохранен!");
            alert.show();
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "пользователь не сохранен!");
            alert.show();
    }
    }

    @FXML
    void initialize() {

        combPosition.setItems(FXCollections.observableList(PositionService.INSTANCE.findAllPostions()));
    }

}

