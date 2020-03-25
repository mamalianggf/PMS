package com.pms.service;

import com.pms.entity.Car;
import com.pms.entity.Decoration;

import java.util.HashMap;
import java.util.List;

public interface CarService {

    List<HashMap> listCar(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertCar(Car car) throws Exception;

    int updateCar(HashMap map) throws Exception;

    int deleteCar(int[] carIds) throws Exception;
}
