package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.entity.Decoration;
import com.pms.entity.EiInfo;
import com.pms.entity.OutIn;
import com.pms.service.DecorationService;
import com.pms.service.OutInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("outIn")
public class OutInController {

    @Autowired
    private OutInService outInService;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo select(int page, int limit, String startTime, String endTime, String type) throws Exception {
        HashMap map = new HashMap();
        map.put("start", (page - 1) * limit);
        map.put("limit", limit);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("type", type);
        List<HashMap> decorations = outInService.listOutIn(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(outInService.count(map));
        eiInfo.setData(decorations);
        return eiInfo;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(OutIn outIn) throws Exception {
        EiInfo eiInfo = new EiInfo();
        outInService.insertOutIn(outIn);
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("新增成功");
        return eiInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo update(int id, String name, String phone, String license, String startTime, String endTime, String type) throws Exception {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("phone", phone);
        map.put("license", license);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("type", type);
        outInService.updateOutIn(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("编辑成功");
        return eiInfo;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo delete(@RequestParam("outInIds[]") int[] outInIds) throws Exception {
        outInService.deleteOutIn(outInIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("删除成功");
        return eiInfo;
    }
}
