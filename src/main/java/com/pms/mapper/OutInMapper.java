package com.pms.mapper;

import com.pms.entity.Decoration;
import com.pms.entity.OutIn;

import java.util.HashMap;
import java.util.List;

public interface OutInMapper {

    List<HashMap> listOutIn(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertOutIn(OutIn outIn) throws Exception;

    int updateOutIn(HashMap map) throws Exception;

    int deleteOutIn(int[] outInIds) throws Exception;

}
