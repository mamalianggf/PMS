package com.pms.mapper;

import com.pms.entity.Decoration;
import com.pms.entity.Opinion;

import java.util.HashMap;
import java.util.List;

public interface DecorationMapper {

    List<HashMap> listDecoration(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertDecoration(Decoration decoration) throws Exception;

    int updateDecoration(HashMap map) throws Exception;

    int deleteDecoration(int[] decorationIds) throws Exception;

}
