package com.pms.service.impl;

import com.pms.entity.Role;
import com.pms.entity.User;
import com.pms.mapper.UserMapper;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User authentication(String username) throws Exception {
        return userMapper.authentication(username);
    }

    public Role getRole(int userId) throws Exception {
        return userMapper.getRole(userId);
    }

    @Override
    public List<HashMap> listUser(HashMap map) throws Exception {
        return userMapper.listUser(map);
    }

    @Override
    public int count(HashMap map) throws Exception {
        return userMapper.count(map);
    }

    @Override
    public int insertUser(User user) throws Exception {
        return userMapper.insertUser(user);
    }

    @Override
    public int insertUser_role(String userId, String roleID) throws Exception {
        return userMapper.insertUser_role(userId, roleID);
    }

    @Override
    public int updateUser(HashMap map) throws Exception {
        return userMapper.updateUser(map);
    }

    @Override
    public int updateUser_role(HashMap map) throws Exception {
        return userMapper.updateUser_role(map);
    }

    @Override
    public int deleteUser(int[] userIds) throws Exception {
        return userMapper.deleteUser(userIds);
    }
}
