package com.example.kassaapp.db;

import com.example.kassaapp.db.impl.PositionDBImpl;
import com.example.kassaapp.models.Position;

import java.util.List;

public interface PositionDB {
    PositionDB INSTANCE = new PositionDBImpl();
    void insert(Position position);

    void update(Position position);

    List<Position> finAllPostions();
}

