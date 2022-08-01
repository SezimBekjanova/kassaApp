package com.example.kassaapp.db.impl;

import com.example.kassaapp.db.CategoryDB;
import com.example.kassaapp.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDBImpl implements CategoryDB {
    private static CategoryDBImpl INSTANCE;

    public static CategoryDBImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryDBImpl();
        }
        return INSTANCE;
    }
    public void insert(Category category) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into categories(name) VALUES (?)");
            ps.setString(1, category.getName());
            ps.executeUpdate(); // для добавления
        } finally {// обязательно обработается
            ConnectionDBImpl.close(connection);
        }
    }
       public void update(Category category) throws SQLException {
           Connection connection = null;
           try {
               connection = ConnectionDBImpl.getConnection();
               PreparedStatement ps = connection.prepareStatement("update categories set name = ? where id= ?");
               ps.setString(1, category.getName());
               ps.setInt(2,category.getId());
               ps.executeUpdate(); // для добавления
           } finally {// обязательно обработается
               ConnectionDBImpl.close(connection);

           }
       }
    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from categories where id= ?");
            ps.setInt(1,id);
            ps.executeUpdate(); // для добавления
        } finally {// обязательно обработается
            ConnectionDBImpl.close(connection);
        }
    }
     public List<Category> findAll()  {
        Connection connection= null;
        List<Category>categories=new ArrayList<>();
        try {
                connection = ConnectionDBImpl.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement("select * from categories");
                ResultSet resultSet= preparedStatement.executeQuery();
                while (resultSet.next()){

                    Category category=new Category();
                    category.setId(resultSet.getInt(1));
                    category.setName(resultSet.getString(2));
                    categories.add(category);
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionDBImpl.close(connection);
            return categories;
        }
    }
}
