package com.example.kassaapp.services.impl;

import com.example.kassaapp.db.impl.OperationProductDBImpl;
import com.example.kassaapp.models.OperationProducts;
import com.example.kassaapp.services.OperationProductService;

import java.util.List;

public class OperationProductServiceImpl implements OperationProductService {

    @Override
    public boolean saveOperationProducts(int operationId, List<OperationProducts> operationProductsList) {
        boolean isSaveResult = OperationProductDBImpl.INSTANCE.saveOperationProduct(operationId, operationProductsList);
        return isSaveResult;
    }
}