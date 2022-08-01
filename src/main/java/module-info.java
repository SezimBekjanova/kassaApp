module com.example.kassaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kassaapp to javafx.fxml;
    exports com.example.kassaapp;
    exports com.example.kassaapp.controllers;
    exports com.example.kassaapp.models;
    opens com.example.kassaapp.controllers to javafx.fxml;
}