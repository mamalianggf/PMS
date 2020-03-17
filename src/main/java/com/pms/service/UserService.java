package com.pms.service;

import com.pms.entity.Role;
import com.pms.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    User authentication(String username) throws Exception;

    Role getRole(int userId) throws Exception;

    List<HashMap> listUser(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertUser(User user) throws Exception;

    int insertUser_role(String userId, String roleID) throws Exception;

    int updateUser(HashMap map) throws Exception;

    int updateUser_role(HashMap map) throws Exception;

    int deleteUser(int[] userIds) throws Exception;
}
