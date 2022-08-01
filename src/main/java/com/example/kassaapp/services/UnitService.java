package com.example.kassaapp.services;

import com.example.kassaapp.models.Unit;
import com.example.kassaapp.services.impl.UnitServiceImpl;

import java.util.List;

public interface UnitService {
    UnitService INSTANCE = new UnitServiceImpl();
    List<Unit> getUnits();
}
