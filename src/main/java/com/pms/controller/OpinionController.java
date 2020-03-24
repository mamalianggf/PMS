package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.constant.OpinionStatus;
import com.pms.entity.EiInfo;
import com.pms.entity.Opinion;
import com.pms.entity.User;
import com.pms.service.OpinionService;
import com.pms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/opinion")
@Controller
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(HttpServletRequest request, Opinion opinion) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = Integer.valueOf(user.getId());
        opinion.setStatus(OpinionStatus.OPINION_STATUS_UNHAND);
        opinion.setCreatorId(userId);
        opinion.setCreateTime(DateUtil.now());
        int result = opinionService.insertOpinion(opinion);
        EiInfo eiInfo = new EiInfo();
        if (result > 0) {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
            eiInfo.setMessage("反馈成功");
        } else {
            eiInfo.setStatus(HttpConstant.HTTP_CODE_405);
            eiInfo.setMessage("反馈失败");
        }
        return eiInfo;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public EiInfo select(String page, String limit,String id, String userId, String intro, String status) throws Exception {
        HashMap map = new HashMap();
        if (!StringUtils.isEmpty(page)&&!StringUtils.isEmpty(limit)){
            map.put("start", String.valueOf((Integer.parseInt(page) - 1) * Integer.parseInt(limit)));
            map.put("limit", limit);
        }
        map.put("userId", userId);
        map.put("id", id);
        map.put("intro", intro);
        map.put("status", status);
        List<HashMap> opinions = opinionService.listOpinion(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(opinionService.count(map));
        eiInfo.setData(opinions);
        return eiInfo;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo delete(@RequestParam("opinionIds[]") int[] opinionIds) throws Exception {
        opinionService.deleteOpinion(opinionIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("删除成功");
        return eiInfo;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo update(int id, String intro, String details) throws Exception {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("intro", intro);
        map.put("details", details);
        opinionService.updateOpinion(map);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("编辑成功");
        return eiInfo;
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo updateStatus(@RequestParam("opinionIds[]") int[] opinionIds,int status) throws Exception {
        opinionService.updateStatus(status,opinionIds);
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("处理成功");
        return eiInfo;
    }


}
