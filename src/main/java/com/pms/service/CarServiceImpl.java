package com.pms.service;

import com.pms.entity.Car;
import com.pms.entity.Decoration;
import com.pms.mapper.CarMapper;
import com.pms.mapper.DecorationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<HashMap> listCar(HashMap map) throws Exception {
        return carMapper.listCar(map);
    }

    @Override
    public int count(HashMap map) throws Exception {
        return carMapper.count(map);
    }

    @Override
    public int insertCar(Car car) throws Exception {
        return carMapper.insertCar(car);
    }

    @Override
    public int updateCar(HashMap map) throws Exception {
        return carMapper.updateCar(map);
    }

    @Override
    public int deleteCar(int[] carIds) throws Exception {
        return carMapper.deleteCar(carIds);
    }


}
