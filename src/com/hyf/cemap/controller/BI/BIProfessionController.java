package com.hyf.cemap.controller.BI;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.service.base.CollegeServiceImpl;
import com.hyf.cemap.service.base.ProfessionServiceImpl;
import com.hyf.cemap.service.bi.BIDateServiceImpl;

@Controller
@RequestMapping(value = "/biprofession")
public class BIProfessionController {
    
    @Autowired
    private CollegeServiceImpl collegeService;
    
    @Autowired
    private ProfessionServiceImpl professionService;
    
    @Autowired
    private BIDateServiceImpl bIDateService;
    
    /**
     * 主页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public ModelAndView indexPage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<College> colleges = collegeService.listAll("id", "ace");
        modelAndView.addObject("colleges", colleges);
        List<Profession> professions = professionService.listByCollegeId(colleges.get(0).getId());
        modelAndView.addObject("professions", professions);
        List<String> sessions = bIDateService.findAllSession();
        modelAndView.addObject("sessions",sessions);
        modelAndView.setViewName("bi/biprofession");
        return modelAndView;
    }

}
