package com.example.kassaapp.services.impl;
import com.example.kassaapp.db.impl.UserDBImpl;
import com.example.kassaapp.models.User;
import com.example.kassaapp.services.UserService;

import java.util.List;
public class UserServiceImpl implements UserService {

    private static UserServiceImpl INSTANCE;

    public static UserServiceImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserServiceImpl();
        }
        return INSTANCE;
    }

    public boolean addOrEditUser(User user) {
        return save(user);
    }

    private boolean save(User user) {
        System.out.println("to save " +user     );
        if (user.getId() == null)
            return UserDBImpl.getINSTANCE().insert(user);
        else
            return UserDBImpl.getINSTANCE().update(user);

    }

    public List<User> findAllUsers() {
        return UserDBImpl.getINSTANCE().getAllUsersFromDb();
    }

    public User findOneUserById(int userId) {
        return UserDBImpl.getINSTANCE().getUserById(userId);
    }

    public boolean deleteUser(int id) {
        return UserDBImpl.getINSTANCE().deleteUserById(id);
    }
}

