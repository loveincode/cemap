package com.hyf.cemap.controller.BI;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.bi.BICountEmployRate;
import com.hyf.cemap.bean.bi.BICountPay;
import com.hyf.cemap.bean.bi.BICountPeopleNumber;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.bi.BIDateServiceImpl;

@Controller
@RequestMapping(value = "/bischool")
public class BISchoolController {
    
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
        List<String> sessions = bIDateService.findAllSession();
        modelAndView.addObject("sessions",sessions);
        modelAndView.setViewName("bi/bischool");
        return modelAndView;
    }
    
    
    @RequestMapping(value = "/data", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String data(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            Integer all = bIDateService.countallByinfos(session,null, null, collegeId, professionId,administrativeClassId);
            Integer man = bIDateService.countallByinfos(session, null,"男", collegeId, professionId,administrativeClassId);
            Integer woman = bIDateService.countallByinfos(session,null, "女", collegeId, professionId,administrativeClassId);
            Integer zhuan = bIDateService.countallByinfos(session,"专科", null, collegeId, professionId,administrativeClassId);
            Integer ben = bIDateService.countallByinfos(session,"本科", null, collegeId, professionId,administrativeClassId);
            Integer shuo = bIDateService.countallByinfos(session,"硕士", null, collegeId, professionId,administrativeClassId);
            Integer bo = bIDateService.countallByinfos(session,"博士", null, collegeId, professionId,administrativeClassId);
            Integer bohou = bIDateService.countallByinfos(session,"博士后", null, collegeId, professionId,administrativeClassId);
            
            BICountPeopleNumber bICountPeopleNumber = new BICountPeopleNumber();
            bICountPeopleNumber.setAll(all);
            bICountPeopleNumber.setMan(man);
            bICountPeopleNumber.setWoman(woman);
            bICountPeopleNumber.setZhuan(zhuan);
            bICountPeopleNumber.setBen(ben);
            bICountPeopleNumber.setShuo(shuo);
            bICountPeopleNumber.setBo(bo);
            bICountPeopleNumber.setBohou(bohou);
            bICountPeopleNumber.setYan(shuo+bo+bohou);
            
            Integer hasemploy = bIDateService.countemployByinfos(session, collegeId, professionId,administrativeClassId);
            BICountEmployRate biCountEmployRate = new BICountEmployRate();
            biCountEmployRate.setAll(all);
            biCountEmployRate.setHasemploy(hasemploy);
            biCountEmployRate.setNoemploy(all-hasemploy);
            
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(bICountPeopleNumber);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/employdata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String employdata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            Integer all = bIDateService.countallByinfos(session,null, null, collegeId, professionId,administrativeClassId);
            Integer hasemploy = bIDateService.countemployByinfos(session, collegeId, professionId,administrativeClassId);
            BICountEmployRate biCountEmployRate = new BICountEmployRate();
            biCountEmployRate.setAll(all);
            biCountEmployRate.setHasemploy(hasemploy);
            biCountEmployRate.setNoemploy(all-hasemploy);
            
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(biCountEmployRate);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/methoddata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String methoddata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            List<Map<String, Object>> lists =  bIDateService.countMethodByinfos(session, collegeId, professionId,administrativeClassId);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(lists);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/naturedata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String naturedata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            List<Map<String, Object>> lists =  bIDateService.countNatureByinfos(session, collegeId, professionId,administrativeClassId);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(lists);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/industrydata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String industrydata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            List<Map<String, Object>> lists =  bIDateService.countIndustryByinfos(session, collegeId, professionId,administrativeClassId);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(lists);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/companynamedata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String companynamedata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            List<Map<String, Object>> lists =  bIDateService.countCompanyNameByinfos(session, collegeId, professionId,administrativeClassId);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(lists);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/paydata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String paydata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            @RequestParam(value = "administrativeClassId", required = false) Integer administrativeClassId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            if(administrativeClassId==null){
                administrativeClassId=-1;
            }
            BICountPay biCountPay = new BICountPay();
            biCountPay.setLess4(bIDateService.countPayByinfos(0, 40000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore4less6(bIDateService.countPayByinfos(40000, 60000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore6less8(bIDateService.countPayByinfos(60000, 80000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore8less10(bIDateService.countPayByinfos(80000, 100000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore10less12(bIDateService.countPayByinfos(100000, 120000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore12less15(bIDateService.countPayByinfos(120000, 150000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore15less20(bIDateService.countPayByinfos(150000, 200000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore20less30(bIDateService.countPayByinfos(200000, 300000, session, collegeId, professionId,administrativeClassId));
            biCountPay.setMore30(bIDateService.countPayByinfos(300000, -1, session, collegeId, professionId,administrativeClassId));
            
            
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(biCountPay);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }

}
