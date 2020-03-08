package com.pms.service;

import org.springframework.stereotype.Service;

public interface UserService {

    int authentication(String username, String password) throws Exception;
}
