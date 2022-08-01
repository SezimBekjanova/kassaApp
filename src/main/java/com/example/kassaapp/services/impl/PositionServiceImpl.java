package com.example.kassaapp.services.impl;

import com.example.kassaapp.db.impl.PositionDBImpl;
import com.example.kassaapp.models.Position;
import com.example.kassaapp.services.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    public void save(Position position) {
        if (position.getId() == null)
            PositionDBImpl.getINSTANCE().insert(position);
        else
            PositionDBImpl.getINSTANCE().update(position);
    }

    public Position findPositionById(int positionId) {
        return null;
    }

    @Override
    public List<Position> findAllPostions() {
        return PositionDBImpl.getINSTANCE().finAllPostions();
    }
}
