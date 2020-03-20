package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.entity.Decoration;
import com.pms.entity.EiInfo;
import com.pms.entity.Pay;
import com.pms.service.DecorationService;
import com.pms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("decoration")
public class DecorationController {

    @Autowired
    private DecorationService decorationService;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo select(int page, int limit, String address, String userName) throws Exception {
        HashMap map = new HashMap();
        map.put("start", (page - 1) * limit);
        map.put("limit", limit);
        map.put("address", address);
        map.put("userName", userName);
        List<HashMap> decorations = decorationService.listDecoration(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(decorationService.count(map));
        eiInfo.setData(decorations);
        return eiInfo;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(Decoration decoration) throws Exception {
        EiInfo eiInfo = new EiInfo();
        decorationService.insertDecoration(decoration);
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("新增成功");
        return eiInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo update(int id, String startTime) throws Exception {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("startTime", startTime);
        decorationService.updateDecoration(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("编辑成功");
        return eiInfo;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo delete(@RequestParam("decorationIds[]") int[] decorationIds) throws Exception {
        decorationService.deleteDecoration(decorationIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("删除成功");
        return eiInfo;
    }
}
