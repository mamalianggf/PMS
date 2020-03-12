package com.pms.service.impl;

import com.pms.entity.Opinion;
import com.pms.mapper.OpinionMapper;
import com.pms.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    public int insertOpinion(Opinion opinion) throws Exception {
        return opinionMapper.insertOpinion(opinion);
    }
}
