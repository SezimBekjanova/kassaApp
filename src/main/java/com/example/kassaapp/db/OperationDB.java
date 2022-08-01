package com.example.kassaapp.db;

import com.example.kassaapp.db.impl.OperationDBImpl;
import com.example.kassaapp.models.Operation;

public interface OperationDB {
    OperationDB INSTANCE =new OperationDBImpl();

    Operation saveOperation(Operation operation);
    Operation findOperationByTotalPriceAndOperDate(double totalPrice, String operDate);

}