package com.pms.service;

import com.pms.entity.Role;
import com.pms.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User authentication(String username, String password) throws Exception;

    Role getRole(int userId) throws Exception;
}
