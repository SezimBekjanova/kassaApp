package com.example.kassaapp.db.impl;

import com.example.kassaapp.db.ConnectionDB;
import com.example.kassaapp.db.OperationDB;
import com.example.kassaapp.models.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OperationDBImpl implements OperationDB {
    @Override
    public Operation saveOperation(Operation operation) {
        Connection connection = null;
        Operation operationFROMdb = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            String query = "insert into operations(oper_date, total) values (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, operation.getAddDate().toString());
            ps.setDouble(2, operation.getTotal());
            ps.execute();

            operationFROMdb = findOperationByTotalPriceAndOperDate(operation.getTotal(), operation.getAddDate().toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return operationFROMdb;

    }

    @Override
    public Operation findOperationByTotalPriceAndOperDate(double totalPrice, String operDate) {
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            String query = "select * from operations where oper_date = ? and total = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, operDate);
            ps.setDouble(2, totalPrice);

            ResultSet rs = ps.executeQuery();
            Operation operation = new Operation();
            operation.setId(rs.getInt(1));
            //System.out.println((rs.getDate(2)));
            //operation.setAddDate(rs.getDate(2));
            operation.setTotal(rs.getInt(3));

            return operation;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return null;
    }
}
