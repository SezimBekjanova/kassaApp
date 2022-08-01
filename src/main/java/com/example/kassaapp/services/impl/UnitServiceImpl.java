package com.example.kassaapp.services.impl;

import com.example.kassaapp.db.impl.UnitDBImpl;
import com.example.kassaapp.models.Unit;
import com.example.kassaapp.services.UnitService;

import java.util.List;

public class UnitServiceImpl implements UnitService {
    public static UnitServiceImpl INSTANCE;
    public  static UnitServiceImpl getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE=new UnitServiceImpl();
        }
        return INSTANCE;
    }
    public List<Unit> getUnits(){
        return UnitDBImpl.getINSTANCE().getUnits();
    }
}
