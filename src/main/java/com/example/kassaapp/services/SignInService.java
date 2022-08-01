package com.example.kassaapp.services;

import com.example.kassaapp.services.impl.SignInServiceImpl;

public interface SignInService {
    SignInService INSTANCE = new SignInServiceImpl();
}
