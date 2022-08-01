package com.example.kassaapp.services;

import com.example.kassaapp.models.Position;
import com.example.kassaapp.services.impl.PositionServiceImpl;

import java.util.List;

public interface PositionService {

    PositionService INSTANCE = new PositionServiceImpl();

    void save(Position position);

    Position findPositionById(int positionId);

    List<Position> findAllPostions();
}
