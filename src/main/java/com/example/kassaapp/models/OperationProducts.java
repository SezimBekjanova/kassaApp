package com.example.kassaapp.models;

public class OperationProducts {
    private double amount;
    private double priceWithDiscount;
    private Integer id;
    private Operation operation;
    private Product product;
    private double total;
    private  double sum;
    private  double result;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total= total ;
    }

    @Override
    public String toString() {
        return product+" "+ amount;
    }
}
