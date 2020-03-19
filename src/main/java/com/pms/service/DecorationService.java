package com.pms.service;

import com.pms.entity.Decoration;

import java.util.HashMap;
import java.util.List;

public interface DecorationService {

    List<HashMap> listDecoration(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertDecoration(Decoration decoration) throws Exception;

    int updateDecoration(HashMap map) throws Exception;

    int deleteDecoration(int[] decorationIds) throws Exception;
}
