package com.example.kassaapp.db.impl;

import com.example.kassaapp.db.ProductDB;
import com.example.kassaapp.models.Category;
import com.example.kassaapp.models.Product;
import com.example.kassaapp.models.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDBImpl implements ProductDB {
    private static ProductDBImpl INSTANCE;

    public static ProductDBImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ProductDBImpl();
        }
        return INSTANCE;
    }
    public void insert(Product product){
        Connection connection=null;
        try {
            connection= ConnectionDBImpl.getConnection();
            String sql ="insert into products(name,category_id, price, amount, discount, unit_id, barcode)"+"" +
                    "values(?,?,?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,product.getName());
            ps.setInt(2,product.getCategory().getId());
            ps.setDouble(3,product.getPrice());
            ps.setDouble(4,product.getAmount());
            ps.setInt(5, product.getDiscount());
            ps.setInt(6,product.getUnit().getId());
            ps.setString(7,product.getBarcode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDBImpl.close(connection);
        }

    }


    public List<Product> selectProducts() {
        Connection connection=null;
        List<Product> products=new ArrayList<>();
        try {
            connection= ConnectionDBImpl.getConnection();
            PreparedStatement ps = connection.prepareStatement("select p.id, p.name,category_id, price, amount, discount, unit_id, barcode,c.name,unit_name from products p\n" +
                    "join categories c\n" +
                    "on p.category_id=c.id\n" +
                    "join units u\n" +
                    "on p.unit_id=u.id");
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(4));
                product.setBarcode(resultSet.getString(8));
                product.setDiscount(resultSet.getInt(6));
                product.setAmount(resultSet.getDouble(5));
                Category category = new Category();
                category.setId(resultSet.getInt(3));
                category.setName(resultSet.getString(9));
                Unit unit = new Unit();
                unit.setId(resultSet.getInt(7));
                unit.setName(resultSet.getString(10));
                product.setCategory(category);
                product.setUnit(unit);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionDBImpl.close(connection);
            return products;
        }
    }

    public void update(Product product) {
        Connection connection=null;
        try {
            connection= ConnectionDBImpl.getConnection();
            String sql ="update products set name=?,category_id=?, price=?, amount=?, discount=?, unit_id=?, barcode=? where id=?";

            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,product.getName());
            ps.setInt(2,product.getCategory().getId());
            ps.setDouble(3,product.getPrice());
            ps.setDouble(4,product.getAmount());
            ps.setInt(5, product.getDiscount());
            ps.setInt(6,product.getUnit().getId());
            ps.setString(7,product.getBarcode());
            ps.setInt(8, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDBImpl.close(connection);
        }
    }

    public Product findProductByBarcode(String barcode) {
        Connection connection =null;
        Product product=new Product();
        try {
            connection= ConnectionDBImpl.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT p.id, p.name, p.category_id, p.price, p.unit_id, p.barcode, p.amount, \n" +
                    "p.discount, c.name, u.unit_name \n" +
                    "from products p join categories c\n" +
                    "on p.category_id = c.id\n" +
                    "join units u\n" +
                    "on p.unit_id = u.id\n" +
                    "where p.barcode = ?");
            ps.setString(1,barcode);
            ResultSet resultSet=ps.executeQuery();
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getDouble(4));
            product.setBarcode(barcode);
            product.setDiscount(resultSet.getInt(6));
            product.setAmount(resultSet.getDouble(5));
            Category category = new Category();
            category.setId(resultSet.getInt(3));
            category.setName(resultSet.getString(9));
            Unit unit = new Unit();
            unit.setId(resultSet.getInt(7));
            unit.setName(resultSet.getString(10));
            product.setCategory(category);
            product.setUnit(unit);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDBImpl.close(connection);
            return  product;
        }
    }
}

