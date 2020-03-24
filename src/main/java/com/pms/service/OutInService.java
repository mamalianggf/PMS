package com.pms.service;

import com.pms.entity.OutIn;

import java.util.HashMap;
import java.util.List;

public interface OutInService {

    List<HashMap> listOutIn(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertOutIn(OutIn outIn) throws Exception;

    int updateOutIn(HashMap map) throws Exception;

    int deleteOutIn(int[] outInIds) throws Exception;
}
