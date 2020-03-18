package com.pms.service.impl;

import com.pms.entity.Opinion;
import com.pms.entity.Pay;
import com.pms.mapper.OpinionMapper;
import com.pms.mapper.PayMapper;
import com.pms.service.OpinionService;
import com.pms.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public List<HashMap> listPay(HashMap map) throws Exception {
        return payMapper.listPay(map);
    }

    @Override
    public int count(HashMap map) throws Exception {
        return payMapper.count(map);
    }

    @Override
    public int insertPay(Pay pay) throws Exception {
        return payMapper.insertPay(pay);
    }

    @Override
    public int deletePay(int[] payIds) throws Exception {
        return payMapper.deletePay(payIds);
    }

    @Override
    public int updatePay(HashMap map) throws Exception {
        return payMapper.updatePay(map);
    }
}
