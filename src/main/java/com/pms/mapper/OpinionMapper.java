package com.pms.mapper;

import com.pms.entity.Opinion;

import java.util.List;

public interface OpinionMapper {

    int insertOpinion(Opinion opinion) throws Exception;

    List<Opinion> listOpinion(Opinion opinion) throws Exception;
}
