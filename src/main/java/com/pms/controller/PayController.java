package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.entity.EiInfo;
import com.pms.entity.Pay;
import com.pms.entity.User;
import com.pms.service.PayService;
import com.pms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


@RequestMapping("/pay")
@Controller
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo select(int page, int limit, String payerRname, String payeeRname) throws Exception {
        HashMap map = new HashMap();
        map.put("start", (page - 1) * limit);
        map.put("limit", limit);
        map.put("payerRname", payerRname);
        map.put("payeeRname", payeeRname);
        List<HashMap> pays = payService.listPay(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(payService.count(map));
        eiInfo.setData(pays);
        return eiInfo;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(Pay pay) throws Exception {
        EiInfo eiInfo = new EiInfo();
        BigDecimal textMoney = new BigDecimal(pay.getTextMoney());
        BigDecimal valueMoney = new BigDecimal(pay.getValueMoney());
        BigDecimal tax = new BigDecimal(pay.getTax());
        if (textMoney.subtract(valueMoney).compareTo(tax) != 0) {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_405);
            eiInfo.setMessage("请正确输入金额");
            return eiInfo;
        }
        pay.setCreateTime(DateUtil.now());
        payService.insertPay(pay);
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("新增成功");
        return eiInfo;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo delete(@RequestParam("payIds[]") int[] payIds) throws Exception {
        payService.deletePay(payIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("删除成功");
        return eiInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo update(int id, String textMoney, String valueMoney, String tax, String comment) throws Exception {
        EiInfo eiInfo = new EiInfo();
        BigDecimal text = new BigDecimal(textMoney);
        BigDecimal value = new BigDecimal(valueMoney);
        BigDecimal t = new BigDecimal(tax);
        if (text.subtract(value).compareTo(t) != 0) {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_405);
            eiInfo.setMessage("请正确输入金额");
            return eiInfo;
        }
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("textMoney", textMoney);
        map.put("valueMoney", valueMoney);
        map.put("tax", tax);
        map.put("comment", comment);
        payService.updatePay(map);
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("编辑成功");
        return eiInfo;
    }
}
