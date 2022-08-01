package com.example.kassaapp.db;


import com.example.kassaapp.db.impl.OperationProductDBImpl;
import com.example.kassaapp.models.OperationProducts;

import java.util.List;

public interface OperationProductDB {


    OperationProductDB INSTANCE = new OperationProductDBImpl();



    boolean saveOperationProduct(int operationId, List<OperationProducts> operationProductsList);


}
