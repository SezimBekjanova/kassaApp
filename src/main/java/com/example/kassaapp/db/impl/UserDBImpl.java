package com.example.kassaapp.db.impl;

import com.example.kassaapp.db.UserDB;
import com.example.kassaapp.models.Position;
import com.example.kassaapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBImpl implements UserDB {

    private static UserDBImpl INSTANCE;

    public static UserDBImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserDBImpl();
        }
        return INSTANCE;
    }

    public boolean insert(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            String sql = "insert into users(name, login, password, id_positions)" +
                    "values(?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getPosition().getId());

            int countUpdRows = ps.executeUpdate();

            ConnectionDBImpl.close(connection);

            return countUpdRows >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return false;
    }

    public boolean update(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            String sql = "update users set name = ?, login = ?, password = ?, id_positions = ? where id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getPosition().getId());
            ps.setInt(5, user.getId());

            int countUpdRow = ps.executeUpdate();
            ConnectionDBImpl.close(connection);
            return countUpdRow >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return false;
    }

    public User getUserById(int id) {
        Connection connection = null;

        User user = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            String query = "select us.id, us.name, us.login, us.password, p.id, p.name from users us join positions p" +
                    " on us.id_positions = p.id where us.id= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setPassword(rs.getString(4));
            int positionId = rs.getInt(5);
            String posName = rs.getString(6);
            user.setPosition(new Position(positionId, posName));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return user;
    }



    public List<User> getAllUsersFromDb() {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        try {
            connection = ConnectionDBImpl.getConnection();
            String query = "select us.id, us.name, us.login, us.password, p.id, p.name from users us join positions p" +
                    " on us.id_positions = p.id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setPassword(rs.getString(4));
                int positionId = rs.getInt(5);
                String posName = rs.getString(6);
                user.setPosition(new Position(positionId, posName));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return userList;
    }

    public boolean deleteUserById (int id) {
        Connection connection = null;
        try {
            connection = ConnectionDBImpl.getConnection();
            String query = "delete from users where id =? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int countDeletedRows = ps.executeUpdate();
            return countDeletedRows >= 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBImpl.close(connection);
        }
        return false;
    }

}
