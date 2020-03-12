package com.pms.mapper;

import com.pms.entity.Role;
import com.pms.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    User authentication(@Param("name") String username, @Param("pwd") String password) throws Exception;

    Role getRole(@Param("userId") int userId) throws Exception;

}
