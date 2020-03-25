package com.pms.controller;

import com.pms.constant.OpinionStatus;
import com.pms.constant.RoleConstant;
import com.pms.entity.Role;
import com.pms.entity.User;
import com.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.RoleStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private PayService payService;
    @Autowired
    private OpinionService opinionService;
    @Autowired
    private DecorationService decorationService;
    @Autowired
    private OutInService outInService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "note", method = RequestMethod.GET)
    public ModelAndView note(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        int roleId = ((Role) session.getAttribute("role")).getId();
        int userId = ((User) session.getAttribute("user")).getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("note");
        //如果是业主登录
        if (roleId == RoleConstant.ROLE_OWNER) {
            HashMap opinionParam = new HashMap();
            opinionParam.put("userId", userId);
            opinionParam.put("status", "10");//查询处理中+未被查看
            List<HashMap> opinions1 = opinionService.listOpinion(opinionParam);
            opinionParam.put("status", "20");//查询已处理+未被查看
            List<HashMap> opinions2 = opinionService.listOpinion(opinionParam);
            opinions1.addAll(opinions2);
            if (opinions1.size() != 0) {
                modelAndView.addObject("opinions", opinions1);
                modelAndView.addObject("opinionsSize", opinions1.size());
            }
        }

        //如果是物业登录
        if (roleId == RoleConstant.ROLE_MANAGE) {
            HashMap opinionParam = new HashMap();
            String status = "0";//默认查询未处理反馈
            opinionParam.put("status", status);
            List<HashMap> opinions = opinionService.listOpinion(opinionParam);
            if (opinions.size() != 0) {
                modelAndView.addObject("opinions", opinions);
                modelAndView.addObject("opinionsSize", opinions.size());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/opinion/submit", method = RequestMethod.GET)
    public ModelAndView opinionSubmit(HttpServletRequest request, String method, String id, String intro, String details) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("opinionSubmit");
        modelAndView.addObject("method", method);
        if ("update".equals(method) && !StringUtils.isEmpty(id)) {
            HashMap map = new HashMap();
            map.put("id", id);
            modelAndView.addObject("opinion", opinionService.listOpinion(map).get(0));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/opinion", method = RequestMethod.GET)
    public ModelAndView opinion(String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("opinion");
        modelAndView.addObject("id", id);
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
            modelAndView.addObject("people", userService.listUser(map).get(0));
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

    @RequestMapping(value = "decoration", method = RequestMethod.GET)
    public ModelAndView decoration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("decoration");
        return modelAndView;
    }

    @RequestMapping(value = "/decoration/submit", method = RequestMethod.GET)
    public ModelAndView decorationSubmit(String method, String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("decorationSubmit");
        modelAndView.addObject("method", method);
        if ("add".equals(method)) {
            HashMap map = new HashMap();
            map.put("roleId", "2");
            List<HashMap> users = userService.listUser(map);
            modelAndView.addObject("users", users);
            modelAndView.addObject("usersSize", users.size());
        }
        if ("update".equals(method) && !StringUtils.isEmpty(id)) {
            HashMap map = new HashMap();
            map.put("id", id);
            modelAndView.addObject("decoration", decorationService.listDecoration(map).get(0));
        }
        return modelAndView;
    }

    @RequestMapping(value = "outIn", method = RequestMethod.GET)
    public ModelAndView outIn() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("outIn");
        return modelAndView;
    }

    @RequestMapping(value = "/outIn/submit", method = RequestMethod.GET)
    public ModelAndView outInSubmit(String method, String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("outInSubmit");
        modelAndView.addObject("method", method);
        if ("update".equals(method) && !StringUtils.isEmpty(id)) {
            HashMap map = new HashMap();
            map.put("id", id);
            modelAndView.addObject("outIn", outInService.listOutIn(map).get(0));
        }
        return modelAndView;
    }

    @RequestMapping(value = "pwd", method = RequestMethod.GET)
    public ModelAndView pwd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pwd");
        return modelAndView;
    }

    @RequestMapping(value = "car", method = RequestMethod.GET)
    public ModelAndView car() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car");
        return modelAndView;
    }

    @RequestMapping(value = "/car/submit", method = RequestMethod.GET)
    public ModelAndView carSubmit(String method, String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("carSubmit");
        modelAndView.addObject("method", method);
        if ("add".equals(method)) {
            List<HashMap> users = userService.listUser(new HashMap());
            modelAndView.addObject("users", users);
            modelAndView.addObject("usersSize", users.size());
        }
        if ("update".equals(method) && !StringUtils.isEmpty(id)) {
            HashMap map = new HashMap();
            map.put("id", id);
            modelAndView.addObject("car", carService.listCar(map).get(0));
        }
        return modelAndView;
    }


    @RequestMapping(value = "people", method = RequestMethod.GET)
    public ModelAndView people() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
