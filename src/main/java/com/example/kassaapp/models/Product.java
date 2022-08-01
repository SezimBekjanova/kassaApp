package com.example.kassaapp.models;

public class Product {
    private Integer id;
    private String name;
    private  double price;
    private String barcode;
    private double amount;
    private int discount;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getAmount() {
        return amount;
    }

    public int getDiscount() {
        return discount;
    }

    public Category getCategory() {
        return category;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private  Category category; // об класса категория
    private Unit unit;

    public String toString(){
        return getName();
    }



}
