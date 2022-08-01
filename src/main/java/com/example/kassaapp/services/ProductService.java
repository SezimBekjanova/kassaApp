package com.example.kassaapp.services;

import com.example.kassaapp.models.Product;
import com.example.kassaapp.services.impl.ProductServiceImpl;

import java.util.List;

public interface ProductService {
    ProductService INSTANCE = new ProductServiceImpl();
    void save(Product product);
    List<Product> getProducts();
    Product findProductByBarcode(String barcode);

}
