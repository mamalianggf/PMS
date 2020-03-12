package com.pms.service;

import com.pms.entity.Opinion;

import java.util.List;

public interface OpinionService {

    int insertOpinion(Opinion opinion) throws Exception;

    List<Opinion> listOpinion(Opinion opinion) throws Exception;
}
