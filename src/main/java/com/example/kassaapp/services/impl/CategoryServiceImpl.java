package com.example.kassaapp.services.impl;

import com.example.kassaapp.db.impl.CategoryDBImpl;
import com.example.kassaapp.models.Category;
import com.example.kassaapp.services.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDBImpl categoryDB= CategoryDBImpl.getINSTANCE();
    public void save(Category category) throws SQLException {
        if(category.getId()== null)
            categoryDB.insert(category);
        else
            categoryDB.update(category);
    }

    public List<Category> getCategories() {
        return categoryDB.findAll();
    }

    public void delete(Integer id) {
        try {
            categoryDB.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
