package com.pms.controller;

import com.pms.service.PayService;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private PayService payService;

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

    @RequestMapping(value = "/opinion", method = RequestMethod.GET)
    public ModelAndView opinion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("opinion");
        return modelAndView;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping(value = "/user/submit", method = RequestMethod.GET)
    public ModelAndView userSubmit(String method, String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userSubmit");
        modelAndView.addObject("method", method);
        if ("update".equals(method) && !StringUtils.isEmpty(id)) {
            HashMap map = new HashMap();
            map.put("id", id);
            modelAndView.addObject("user", userService.listUser(map).get(0));
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
    public ModelAndView paySubmit(String method, String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paySubmit");
        modelAndView.addObject("method", method);
        if ("add".equals(method)) {
            List<HashMap> users = userService.listUser(new HashMap());
            modelAndView.addObject("users", users);
            modelAndView.addObject("usersSize", users.size());
        }
        if ("update".equals(method) && !StringUtils.isEmpty(id)) {
            HashMap map = new HashMap();
            map.put("id", id);
            modelAndView.addObject("pay", payService.listPay(map).get(0));
        }
        return modelAndView;
    }
}
