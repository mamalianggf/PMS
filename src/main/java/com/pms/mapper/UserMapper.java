package com.pms.mapper;

import com.pms.entity.Opinion;
import com.pms.entity.Role;
import com.pms.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


public interface UserMapper {

    User authentication(@Param("name") String username) throws Exception;

    Role getRole(@Param("userId") int userId) throws Exception;

    List<HashMap> listUser(HashMap map) throws Exception;

    int count(HashMap map) throws Exception;

    int insertUser(User user) throws Exception;

    int insertUser_role(@Param("userId")String userId, @Param("roleId")String roleId) throws Exception;

    int updateUser(HashMap map) throws Exception;

    int updateUser_role(HashMap map) throws Exception;

    int deleteUser(int[] userIds) throws Exception;

}
