package com.example.kassaapp.services;

import com.example.kassaapp.models.Operation;
import com.example.kassaapp.models.OperationProducts;
import com.example.kassaapp.services.impl.OperationServiceImpl;

import java.util.List;

public interface OperationService {
    OperationService INSTANCE = new OperationServiceImpl();

    boolean closeAndSaveOperation(double totalPrice, int userId, List<OperationProducts> operationProductsList);
}
