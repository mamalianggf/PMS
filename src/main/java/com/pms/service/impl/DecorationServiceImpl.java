package com.pms.service.impl;

import com.pms.entity.Decoration;
import com.pms.mapper.DecorationMapper;
import com.pms.service.DecorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DecorationServiceImpl implements DecorationService {

    @Autowired
    private DecorationMapper decorationMapper;

    @Override
    public List<HashMap> listDecoration(HashMap map) throws Exception {
        return decorationMapper.listDecoration(map);
    }

    @Override
    public int count(HashMap map) throws Exception {
        return decorationMapper.count(map);
    }

    @Override
    public int insertDecoration(Decoration decoration) throws Exception {
        return decorationMapper.insertDecoration(decoration);
    }

    @Override
    public int updateDecoration(HashMap map) throws Exception {
        return decorationMapper.updateDecoration(map);
    }

    @Override
    public int deleteDecoration(int[] decorationIds) throws Exception {
        return decorationMapper.deleteDecoration(decorationIds);
    }
}
