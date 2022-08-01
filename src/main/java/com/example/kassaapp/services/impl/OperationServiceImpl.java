package com.example.kassaapp.services.impl;

import com.example.kassaapp.db.OperationDB;
import com.example.kassaapp.models.Operation;
import com.example.kassaapp.models.OperationProducts;
import com.example.kassaapp.services.OperationProductService;
import com.example.kassaapp.services.OperationService;

import java.time.LocalDateTime;
import java.util.List;

public class OperationServiceImpl implements OperationService {
    @Override
    public boolean closeAndSaveOperation(double totalPrice, int userId, List<OperationProducts> operationProductsList) {
        Operation operation = OperationDB.INSTANCE.saveOperation(new Operation(LocalDateTime.now(), totalPrice));
        System.out.println(operation);
        return OperationProductService.INSTANCE.saveOperationProducts(operation.getId(), operationProductsList);
    }
}
