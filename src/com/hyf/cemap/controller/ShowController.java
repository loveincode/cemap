package com.hyf.cemap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.News;
import com.hyf.cemap.bean.po.PreachingMeeting;
import com.hyf.cemap.bean.po.RecruitmentInformation;
import com.hyf.cemap.service.NewsServiceImpl;
import com.hyf.cemap.service.PreachingMeetingServiceImpl;
import com.hyf.cemap.service.RecruitmentInformationServiceImpl;

@Controller
@RequestMapping(value = "/show")
public class ShowController {
    
    @Autowired
    private RecruitmentInformationServiceImpl recruitmentInformationService;
    
    @Autowired
    private PreachingMeetingServiceImpl preachingMeetingService;
    
    @Autowired
    private NewsServiceImpl newsService;
    
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        List<RecruitmentInformation> recruitmentInformations =recruitmentInformationService.listSixRecruitmentInformation();
        List<PreachingMeeting> preachingMeetings =preachingMeetingService.listSixPreachingMeeting();
        List<News> newsgg = newsService.listSixNews(32); 
        List<News> newszx = newsService.listSixNews(33); 
        List<News> newszc = newsService.listSixNews(34);
        modelAndView.addObject("newsgg", newsgg);
        modelAndView.addObject("newszx", newszx);
        modelAndView.addObject("newszc", newszc);
        modelAndView.addObject("recruitmentInformations", recruitmentInformations);
        modelAndView.addObject("preachingMeetings", preachingMeetings);
        modelAndView.setViewName("font/index");
        return modelAndView;
    }
    
    /**
     * 公告 资讯 政策  32 33 34
     * @param typeid 类型
     * @param page   页数
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/news{newsTypeID}/_{pageNo}", method = { RequestMethod.GET })
    public ModelAndView news(
            @PathVariable("newsTypeID") Integer newsTypeID,
            @PathVariable("pageNo") Integer pageNo,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        List<News> news = newsService.listNewsByinfo(null, newsTypeID, pageNo, 9);
        Integer counts = newsService.listNewsByinfo(null, newsTypeID, -1,-1).size();
        Integer currentpage = pageNo;
        modelAndView.addObject("counts", counts);
        modelAndView.addObject("currentpage", currentpage);
        modelAndView.addObject("news", news);
        modelAndView.setViewName("font/news");
        return modelAndView;
    }
    
    @RequestMapping(value = "/newsdetail/{newsId}", method = { RequestMethod.GET })
    public ModelAndView newsdetail(
            @PathVariable("newsId") Integer newsId,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            News newsone = newsService.getById(newsId);
            newsone.setNewsClick(newsone.getNewsClick()+1);
            newsService.update(newsone);
            modelAndView.addObject("newsone",newsone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("font/news_detail");
        return modelAndView;
    }
    
    
    /**
     * 招聘信息show
     * @param companyType
     * @param pageNo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/employmentinformation{companyType}/_{pageNo}", method = { RequestMethod.GET })
    public ModelAndView employmentinformation(
            @PathVariable("companyType") String companyType,
            @PathVariable("pageNo") Integer pageNo,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        if(companyType.equals("")){
            companyType=null;
        }
        List<RecruitmentInformation> recruitmentInformations = recruitmentInformationService.flistRecruitmentInformationByinfo(null, companyType, pageNo, 10);
        Integer counts;
        counts = recruitmentInformationService.flistRecruitmentInformationByinfo(null, companyType, -1, -1).size();
        Integer currentpage = pageNo;
        modelAndView.addObject("counts", counts);
        modelAndView.addObject("currentpage", currentpage);

        modelAndView.addObject("recruitmentInformations",recruitmentInformations);
        modelAndView.setViewName("font/employmentinformation");
        return modelAndView;
    }
    
    @RequestMapping(value = "/employmentinformationdetail/{infoId}", method = { RequestMethod.GET })
    public ModelAndView employmentinformationdetail(
            @PathVariable("infoId") String infoId,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            RecruitmentInformation recruitmentInformation = recruitmentInformationService.getByUuid(infoId);
            modelAndView.addObject("recruitmentInformation", recruitmentInformation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("font/employmentinformation_detail");
        return modelAndView;
    }
    
    /**
     * 宣讲会show
     * @param companyType
     * @param pageNo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/preachingmeeting{companyType}/_{pageNo}", method = { RequestMethod.GET })
    public ModelAndView preachingmeeting(
            @PathVariable("companyType") String companyType,
            @PathVariable("pageNo") Integer pageNo,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        if(companyType.equals("")){
            companyType=null;
        }
        List<PreachingMeeting> preachingMeetings = preachingMeetingService.flistPreachingMeetingByinfo(null, companyType, pageNo,10);
        Integer counts;
        counts = preachingMeetingService.flistPreachingMeetingByinfo(null, companyType, -1, -1).size();
        Integer currentpage = pageNo;
        modelAndView.addObject("counts", counts);
        modelAndView.addObject("currentpage", currentpage);

        modelAndView.addObject("preachingMeetings",preachingMeetings);
        modelAndView.setViewName("font/preachingmeeting");
        return modelAndView;
    }
    
    @RequestMapping(value = "/preachingmeetingdetail/{infoId}", method = { RequestMethod.GET })
    public ModelAndView preachingmeetingdetail(
            @PathVariable("infoId") String infoId,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            PreachingMeeting preachingMeeting = preachingMeetingService.getByUuid(infoId);
            modelAndView.addObject("preachingMeeting", preachingMeeting);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("font/preachingmeeting_detail");
        return modelAndView;
    }
    
    @RequestMapping(value = "/quality", method = { RequestMethod.GET })
    public ModelAndView quality(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("font/quality");
        return modelAndView;
    }
    
    @RequestMapping(value = "/studentService", method = { RequestMethod.GET })
    public ModelAndView studentService(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("font/studentservice");
        return modelAndView;
    }
    
    @RequestMapping(value = "/about", method = { RequestMethod.GET })
    public ModelAndView about(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("font/about");
        return modelAndView;
    }
    
    
}
