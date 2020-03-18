package com.pms.controller;

import com.pms.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "note", method = RequestMethod.GET)
    public ModelAndView note(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("note");
        return modelAndView;
    }

    @RequestMapping(value = "/opinion/submit", method = RequestMethod.GET)
    public ModelAndView opinionSubmit(HttpServletRequest request, String method, String id, String intro, String details) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("opinionSubmit");
        modelAndView.addObject("method", method);
        if (!StringUtils.isEmpty(id)) {
            modelAndView.addObject("id", id);
        }
        if (!StringUtils.isEmpty(intro)) {
            modelAndView.addObject("intro", intro);
        }
        if (!StringUtils.isEmpty(details)) {
            modelAndView.addObject("details", details);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/opinion/check", method = RequestMethod.GET)
    public ModelAndView opinionCheck() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("opinionCheck");
        return modelAndView;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping(value = "/user/submit", method = RequestMethod.GET)
    public ModelAndView userSubmit(HttpServletRequest request, String method, String id, String pwd, String name, String phone, String address, String rname, String roleId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userSubmit");
        modelAndView.addObject("method", method);
        if (!StringUtils.isEmpty(id)) {
            modelAndView.addObject("id", id);
        }
        if (!StringUtils.isEmpty(name)) {

            modelAndView.addObject("name", name);
        }
        if (!StringUtils.isEmpty(pwd)) {
            modelAndView.addObject("pwd", pwd);
        }
        if (!StringUtils.isEmpty(phone)) {
            modelAndView.addObject("phone", phone);
        }
        if (!StringUtils.isEmpty(address)) {
            modelAndView.addObject("address", address);
        }
        if (!StringUtils.isEmpty(rname)) {
            modelAndView.addObject("rname", rname);
        }
        if (!StringUtils.isEmpty(roleId)) {
            modelAndView.addObject("roleId", roleId);
        }
        return modelAndView;
    }

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public ModelAndView pay() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay");
        return modelAndView;
    }

    @RequestMapping(value = "/pay/submit", method = RequestMethod.GET)
    public ModelAndView paySubmit(HttpServletRequest request, String method, String id, String textMoney, String valueMoney, String tax, String comment, String payerId, String payeeId,String payerName,String payeeName) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paySubmit");
        modelAndView.addObject("method", method);
        if ("add".equals(method)) {
            List<HashMap> users = userService.listUser(new HashMap());
            modelAndView.addObject("users",users);
            modelAndView.addObject("usersSize",users.size());
        }
        if (!StringUtils.isEmpty(id)) {
            modelAndView.addObject("id", id);
        }
        if (!StringUtils.isEmpty(textMoney)) {

            modelAndView.addObject("textMoney", textMoney);
        }
        if (!StringUtils.isEmpty(valueMoney)) {
            modelAndView.addObject("valueMoney", valueMoney);
        }
        if (!StringUtils.isEmpty(tax)) {
            modelAndView.addObject("tax", tax);
        }
        if (!StringUtils.isEmpty(comment)) {
            modelAndView.addObject("comment", comment);
        }
        if (!StringUtils.isEmpty(payerId)) {
            modelAndView.addObject("payerId", payerId);
        }
        if (!StringUtils.isEmpty(payeeId)) {
            modelAndView.addObject("payeeId", payeeId);
        }
        if (!StringUtils.isEmpty(payerName)) {
            modelAndView.addObject("payerName", payerName);
        }
        if (!StringUtils.isEmpty(payeeName)) {
            modelAndView.addObject("payeeName", payeeName);
        }
        return modelAndView;
    }
}
