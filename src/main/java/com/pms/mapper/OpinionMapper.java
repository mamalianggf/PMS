package com.pms.mapper;

import com.pms.entity.Opinion;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OpinionMapper {

    int insertOpinion(Opinion opinion) throws Exception;

    List<HashMap> listOpinion(HashMap map) throws Exception;

    int deleteOpinion(int[] opinionIds) throws Exception;

    int updateOpinion(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int updateStatus(@Param("status") int status, @Param("array") int[] opinionIds) throws Exception;
}
