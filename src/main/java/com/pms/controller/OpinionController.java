package com.pms.controller;

import com.pms.constant.HttpConstant;
import com.pms.constant.OpinionStatus;
import com.pms.entity.EiInfo;
import com.pms.entity.Opinion;
import com.pms.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/opinion")
@Controller
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo submit(HttpServletRequest request, Opinion opinion) throws Exception {
        // todo 上传
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("userId").toString());
        opinion.setStatus(OpinionStatus.OPINION_STATUS_UNHAND);
        opinion.setCreatorId(userId);
        opinion.setCreateTime(LocalDateTime.now().toString());
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

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public EiInfo select(String page, String limit) throws Exception {
        List<Opinion> opinions = opinionService.listOpinion(new Opinion());
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_200);
        eiInfo.setMessage("查询成功");
        eiInfo.setCount(opinions.size());
        eiInfo.setData(opinions);
        return eiInfo;
    }


}
