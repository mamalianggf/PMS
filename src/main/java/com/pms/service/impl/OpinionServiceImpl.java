package com.pms.service.impl;

import com.pms.entity.Opinion;
import com.pms.mapper.OpinionMapper;
import com.pms.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    public int insertOpinion(Opinion opinion) throws Exception {
        return opinionMapper.insertOpinion(opinion);
    }

    @Override
    public List<HashMap> listOpinion(HashMap map) throws Exception {
        return opinionMapper.listOpinion(map);
    }

    @Override
    public int deleteOpinion(int[] opinionIds) throws Exception {
        return opinionMapper.deleteOpinion(opinionIds);
    }

    @Override
    public int updateOpinion(HashMap map) throws Exception {
        return opinionMapper.updateOpinion(map);
    }

    @Override
    public int count(HashMap map) throws Exception {
        return opinionMapper.count(map);
    }
}
