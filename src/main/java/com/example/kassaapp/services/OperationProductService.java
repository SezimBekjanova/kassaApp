package com.example.kassaapp.services;


import com.example.kassaapp.models.OperationProducts;
import com.example.kassaapp.services.impl.OperationProductServiceImpl;

import java.util.List;

public interface OperationProductService {

    OperationProductService INSTANCE = new OperationProductServiceImpl();

    boolean saveOperationProducts(int operationId, List<OperationProducts> operationProductsList);

}