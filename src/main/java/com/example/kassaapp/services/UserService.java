package com.example.kassaapp.services;

import com.example.kassaapp.models.User;
import com.example.kassaapp.services.impl.UserServiceImpl;

import java.util.List;

public interface UserService{
    UserService INSTANCE = new UserServiceImpl();
    boolean addOrEditUser(User user);
    List<User> findAllUsers();
    User findOneUserById(int userId);
    boolean deleteUser(int id);
}
