package com.example.kassaapp.db;

import com.example.kassaapp.db.impl.UserDBImpl;
import com.example.kassaapp.models.User;

import java.util.List;

public interface UserDB {
    UserDB INSTANCE = new UserDBImpl();
    boolean insert(User user);
    boolean update(User user);
    User getUserById(int id);
    List<User> getAllUsersFromDb();
    boolean deleteUserById (int id);

}
