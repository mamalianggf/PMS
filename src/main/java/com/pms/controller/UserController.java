package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.constant.OpinionStatus;
import com.pms.constant.RoleConstant;
import com.pms.entity.EiInfo;
import com.pms.entity.Opinion;
import com.pms.entity.User;
import com.pms.service.UserService;
import com.pms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(HttpServletRequest request, User user, String roleId) throws Exception {
        User authentication = userService.authentication(user.getName());
        EiInfo eiInfo = new EiInfo();
        if (authentication != null) {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_405);
            eiInfo.setMessage("已存在相同用户名");
            return eiInfo;
        }
        user.setCreateTime(DateUtil.now());
        userService.insertUser(user);
        //默认为业主
        if (StringUtils.isEmpty(roleId)) {
            userService.insertUser_role(String.valueOf(userService.authentication(user.getName()).getId()), String.valueOf(RoleConstant.ROLE_OWNER));
        } else {
            userService.insertUser_role(String.valueOf(userService.authentication(user.getName()).getId()), roleId);
        }
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("新增成功");
        return eiInfo;
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo select(String page, String limit, String id, String name, String rname, String roleId,String roleIds) throws Exception {
        HashMap map = new HashMap();
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(limit)) {
            map.put("start", String.valueOf((Integer.valueOf(page) - 1) * Integer.valueOf(limit)));
            map.put("limit", limit);
        }
        map.put("id", id);
        map.put("name", name);
        map.put("rname", rname);
        map.put("roleId", roleId);
        map.put("roleIds", roleIds);
        List<HashMap> users = userService.listUser(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(userService.count(map));
        eiInfo.setData(users);
        return eiInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo update(int id, String pwd, String oldPwd, String phone, String address, String rname, String roleId) throws Exception {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("pwd", pwd);
        map.put("phone", phone);
        map.put("oldPwd", oldPwd);
        map.put("address", address);
        map.put("rname", rname);
        int result = userService.updateUser(map);
        EiInfo eiInfo = new EiInfo();
        if (!StringUtils.isEmpty(oldPwd) && result == 0) {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_405);
            eiInfo.setMessage("旧密码错误");
        } else {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
            eiInfo.setMessage("重置成功");
        }
        if (!StringUtils.isEmpty(roleId)) {
            HashMap param = new HashMap();
            param.put("userId", id);
            param.put("roleId", roleId);
            userService.updateUser_role(param);
        }
        return eiInfo;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo delete(@RequestParam("userIds[]") int[] userIds) throws Exception {
        userService.deleteUser(userIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("删除成功");
        return eiInfo;
    }
}
