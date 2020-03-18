package com.pms.mapper;

import com.pms.entity.Opinion;
import com.pms.entity.Pay;

import java.util.HashMap;
import java.util.List;

public interface PayMapper {

    List<HashMap> listPay(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertPay(Pay pay) throws Exception;

    int deletePay(int[] payIds) throws Exception;

    int updatePay(HashMap map) throws Exception;

}
