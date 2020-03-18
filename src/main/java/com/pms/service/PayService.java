package com.pms.service;

import com.pms.entity.Opinion;
import com.pms.entity.Pay;

import java.util.HashMap;
import java.util.List;

public interface PayService {

    List<HashMap> listPay(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertPay(Pay pay) throws Exception;

    int deletePay(int[] payIds) throws Exception;

    int updatePay(HashMap map) throws Exception;
}
