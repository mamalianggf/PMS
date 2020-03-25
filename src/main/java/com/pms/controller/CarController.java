package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.entity.Car;
import com.pms.entity.Decoration;
import com.pms.entity.EiInfo;
import com.pms.service.CarService;
import com.pms.service.DecorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo select(int page, int limit, String license, String userName) throws Exception {
        HashMap map = new HashMap();
        map.put("start", (page - 1) * limit);
        map.put("limit", limit);
        map.put("license", license);
        map.put("userName", userName);
        List<HashMap> cars = carService.listCar(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(carService.count(map));
        eiInfo.setData(cars);
        return eiInfo;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(Car car) throws Exception {
        EiInfo eiInfo = new EiInfo();
        carService.insertCar(car);
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("新增成功");
        return eiInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo update(int id, String stall,String license,String details) throws Exception {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("stall", stall);
        map.put("license", license);
        map.put("details", details);
        carService.updateCar(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("编辑成功");
        return eiInfo;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo delete(@RequestParam("carIds[]") int[] carIds) throws Exception {
        carService.deleteCar(carIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("删除成功");
        return eiInfo;
    }
}
