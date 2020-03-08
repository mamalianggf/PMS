package com.pms.service.impl;

import com.pms.mapper.UserMapper;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public int authentication(String username, String password) throws Exception {
        return userMapper.authentication(username,password);
    }
}
