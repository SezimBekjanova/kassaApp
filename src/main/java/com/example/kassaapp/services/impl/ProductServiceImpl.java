package com.example.kassaapp.services.impl;

import com.example.kassaapp.db.ProductDB;
import com.example.kassaapp.db.impl.ProductDBImpl;
import com.example.kassaapp.models.Product;
import com.example.kassaapp.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl INSTANCE;
    public static ProductServiceImpl getInstance(){

        if(INSTANCE== null){
            INSTANCE= new ProductServiceImpl();
        }
        return INSTANCE;
    }


    public void save(Product product) {
        if(product.getId()==null)
            ProductDBImpl.getINSTANCE().insert(product);
        else
            ProductDBImpl.getINSTANCE().update(product);
    }
    public List<Product> getProducts(){

        return ProductDBImpl.getINSTANCE().selectProducts();
    }
    public Product findProductByBarcode(String barcode){
        return ProductDBImpl.getINSTANCE().findProductByBarcode(barcode);
    }
}
