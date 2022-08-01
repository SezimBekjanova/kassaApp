package com.example.kassaapp.services;

import com.example.kassaapp.models.Category;
import com.example.kassaapp.services.impl.CategoryServiceImpl;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    CategoryService INSTANCE = new CategoryServiceImpl();
    public void save(Category category) throws SQLException;
    public List<Category> getCategories();
    public void delete(Integer id);
}
