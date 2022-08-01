package com.example.kassaapp.db.impl;

import com.example.kassaapp.db.UnitDB;
import com.example.kassaapp.models.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDBImpl implements UnitDB {
    private static UnitDBImpl INSTANCE;

    public static UnitDBImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UnitDBImpl();
        }
        return INSTANCE;
    }
    public List<Unit> getUnits() {

        List<Unit> units = new ArrayList<>();
        Connection connection = null;

        {
            try {
                connection = ConnectionDBImpl.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from units ");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Unit unit = new Unit();
                    unit.setId(resultSet.getInt(1));
                    unit.setName(resultSet.getString(2));
                    units.add(unit);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionDBImpl.close(connection);
                return units;
            }
        }
    }
}

