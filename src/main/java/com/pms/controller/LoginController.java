package com.pms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.constant.HttpConstant;
import com.pms.entity.EiInfo;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "authentication", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo authentication(HttpServletRequest request, HttpServletResponse response,String username, String password) throws Exception {
        //校验密码
        int authentication = userService.authentication(username, password);
        //todo 查询角色
        //todo 查询对应的权限集合
        EiInfo eiInfo = new EiInfo();
        if (authentication > 0) {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
            eiInfo.setMessage("登录成功");
        } else {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_405);
            eiInfo.setMessage("账号或者密码错误");
        }
        return eiInfo;
    }
}
