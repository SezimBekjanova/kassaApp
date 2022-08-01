package com.example.kassaapp.db;

import com.example.kassaapp.db.impl.ProductDBImpl;
import com.example.kassaapp.models.Product;

import java.util.List;

public interface ProductDB {
    ProductDB INSTANCE = new ProductDBImpl();
     void insert(Product product);
    List<Product> selectProducts();
    void update(Product product);
    Product findProductByBarcode(String barcode);
}
