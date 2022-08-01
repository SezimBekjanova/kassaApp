package com.example.kassaapp.db;

import com.example.kassaapp.db.impl.UnitDBImpl;
import com.example.kassaapp.models.Unit;

import java.util.List;

public interface UnitDB {
    UnitDB INSTANCE = new UnitDBImpl();
    List<Unit> getUnits();
}
