package com.example.kassaapp.models;

import com.example.kassaapp.enums.OperStatus;

import java.time.LocalDateTime;
public class Operation {
    private Integer id;
    private LocalDateTime addDate;
    private double total;

    public Operation() {
    }

    public Operation(LocalDateTime addDate, double total) {
        this.addDate = addDate;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", addDate=" + addDate +
                ", total=" + total +
                '}';
    }
}
