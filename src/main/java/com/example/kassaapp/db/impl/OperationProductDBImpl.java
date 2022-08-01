package com.example.kassaapp.db.impl;

import com.example.kassaapp.db.ConnectionDB;
import com.example.kassaapp.db.OperationProductDB;
import com.example.kassaapp.models.OperationProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class OperationProductDBImpl implements OperationProductDB {

    @Override
    public boolean saveOperationProduct(int operationId, List<OperationProducts> operationProductsList) {
        System.out.println("saveOperationProduct -> " + operationProductsList);
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            PreparedStatement ps = null;
            String query = "insert into oper_products (oper_id, product_id, amount, price_with_discount)" +
                    " values(?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            for (OperationProducts item : operationProductsList) {
                ps.setInt(1, operationId);
                ps.setInt(2, item.getProduct().getId());
                ps.setDouble(3, item.getAmount());
                ps.setDouble(4, item.getPriceWithDiscount());
                ps.addBatch();
            }
            int[] rows = ps.executeBatch();
            return rows.length >= 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return false;
    }
}
