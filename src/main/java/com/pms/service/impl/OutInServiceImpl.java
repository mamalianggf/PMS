package com.pms.service.impl;

import com.pms.entity.OutIn;
import com.pms.mapper.OutInMapper;
import com.pms.service.OutInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OutInServiceImpl implements OutInService {

    @Autowired
    private OutInMapper outInMapper;

    @Override
    public List<HashMap> listOutIn(HashMap map) throws Exception {
        return outInMapper.listOutIn(map);
    }

    @Override
    public int count(HashMap map) throws Exception {
        return outInMapper.count(map);
    }

    @Override
    public int insertOutIn(OutIn outIn) throws Exception {
        return outInMapper.insertOutIn(outIn);
    }

    @Override
    public int updateOutIn(HashMap map) throws Exception {
        return outInMapper.updateOutIn(map);
    }

    @Override
    public int deleteOutIn(int[] outInIds) throws Exception {
        return outInMapper.deleteOutIn(outInIds);
    }
}
