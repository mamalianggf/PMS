package com.pms.mapper;

import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    int authentication(@Param("name") String username, @Param("pwd") String password) throws Exception;

}
