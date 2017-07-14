package com.hyf.cemap.controller.BI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.hyf.cemap.bean.bi.BICountPay;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.bi.BIDateServiceImpl;

@Controller
@RequestMapping(value = "/bicompareschool")
public class BICompareSchoolsController {
    
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
        modelAndView.setViewName("bicompare/school");
        return modelAndView;
    }
    
    @RequestMapping(value = "/peopledata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String peopledata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            Integer session5 = Integer.parseInt(session);
            Integer session4 = session5-1;
            Integer session3 = session5-2;
            Integer session2 = session5-3;
            Integer session1 = session5-4;
            
            Integer all5 = bIDateService.countallByinfos(session5.toString(),null, null, collegeId, professionId,-1);
            Integer all4 = bIDateService.countallByinfos(session4.toString(),null, null, collegeId, professionId,-1);
            Integer all3 = bIDateService.countallByinfos(session3.toString(),null, null, collegeId, professionId,-1);
            Integer all2 = bIDateService.countallByinfos(session2.toString(),null, null, collegeId, professionId,-1);
            Integer all1 = bIDateService.countallByinfos(session1.toString(),null, null, collegeId, professionId,-1);
            
            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
            
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("method",session1.toString());
            map1.put("counts",all1);
            lists.add(map1);

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("method",session2.toString());
            map2.put("counts",all2);
            lists.add(map2);
            
            
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("method",session3.toString());
            map3.put("counts",all3);
            lists.add(map3);
            
            Map<String, Object> map4 = new HashMap<String, Object>();
            map4.put("method",session4.toString());
            map4.put("counts",all4);
            lists.add(map4);

            Map<String, Object> map5 = new HashMap<String, Object>();
            map5.put("method",session5.toString());
            map5.put("counts",all5);
            lists.add(map5);
            
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(lists);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/employratedata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String employratedata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            Integer session5 = Integer.parseInt(session);
            Integer session4 = session5-1;
            Integer session3 = session5-2;
            Integer session2 = session5-3;
            Integer session1 = session5-4;
            
            Integer all5 = bIDateService.countallByinfos(session5.toString(),null, null, collegeId, professionId,-1);
            Integer has5 = bIDateService.countemployByinfos(session5.toString(), collegeId, professionId, -1);
            Integer all4 = bIDateService.countallByinfos(session4.toString(),null, null, collegeId, professionId,-1);
            Integer has4 = bIDateService.countemployByinfos(session4.toString(), collegeId, professionId, -1);
            Integer all3 = bIDateService.countallByinfos(session3.toString(),null, null, collegeId, professionId,-1);
            Integer has3 = bIDateService.countemployByinfos(session3.toString(), collegeId, professionId, -1);
            Integer all2 = bIDateService.countallByinfos(session2.toString(),null, null, collegeId, professionId,-1);
            Integer has2 = bIDateService.countemployByinfos(session2.toString(), collegeId, professionId, -1);
            Integer all1 = bIDateService.countallByinfos(session1.toString(),null, null, collegeId, professionId,-1);
            Integer has1 = bIDateService.countemployByinfos(session1.toString(), collegeId, professionId, -1);
            double r5,r4,r3,r2,r1;
            DecimalFormat df=new DecimalFormat("#0.00");
            if(all5!=0){
                r5 = (double)has5/all5*100;
            }
            else{
                r5 = 0;
            }
            if(all4!=0){
                r4 = (double)has4/all4*100;
            }
            else{
                r4 = 0;
            }
            if(all3!=0){
                r3 = (double)has3/all3*100;
            }
            else{
                r3 = 0;
            }
            if(all2!=0){
                r2 = (double)has2/all2*100;
            }
            else{
                r2 = 0;
            }
            if(all1!=0){
                r1 = (double)has1/all1*100;
            }
            else{
                r1 = 0;
            }
            
            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
            
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("method",session1.toString());
            map1.put("counts",df.format(r1));
            lists.add(map1);

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("method",session2.toString());
            map2.put("counts",df.format(r2));
            lists.add(map2);
            
            
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("method",session3.toString());
            map3.put("counts",df.format(r3));
            lists.add(map3);
            
            Map<String, Object> map4 = new HashMap<String, Object>();
            map4.put("method",session4.toString());
            map4.put("counts",df.format(r4));
            lists.add(map4);

            Map<String, Object> map5 = new HashMap<String, Object>();
            map5.put("method",session5.toString());
            map5.put("counts",df.format(r5));
            lists.add(map5);
            
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(lists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultVO.setSuccess(true);
            resultVO.setCode("500");
            resultVO.setMessage("错误");
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/paydata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String paydata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            
            Integer session5 = Integer.parseInt(session);
            Integer session4 = session5-1;
            Integer session3 = session5-2;
            Integer session2 = session5-3;
            Integer session1 = session5-4;
            
            BICountPay biCountPay5 = new BICountPay();
            biCountPay5.setLess4(bIDateService.countPayByinfos(0, 40000, session, collegeId, professionId, -1));
            biCountPay5.setMore4less6(bIDateService.countPayByinfos(40000, 60000, session, collegeId, professionId, -1));
            biCountPay5.setMore6less8(bIDateService.countPayByinfos(60000, 80000, session, collegeId, professionId, -1));
            biCountPay5.setMore8less10(bIDateService.countPayByinfos(80000, 100000, session, collegeId, professionId, -1));
            biCountPay5.setMore10less12(bIDateService.countPayByinfos(100000, 120000, session, collegeId, professionId, -1));
            biCountPay5.setMore12less15(bIDateService.countPayByinfos(120000, 150000, session, collegeId, professionId, -1));
            biCountPay5.setMore15less20(bIDateService.countPayByinfos(150000, 200000, session, collegeId, professionId, -1));
            biCountPay5.setMore20less30(bIDateService.countPayByinfos(200000, 300000, session, collegeId, professionId, -1));
            biCountPay5.setMore30(bIDateService.countPayByinfos(300000, -1, session, collegeId, professionId, -1));
            
            BICountPay biCountPay4 = new BICountPay();
            biCountPay4.setLess4(bIDateService.countPayByinfos(0, 40000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore4less6(bIDateService.countPayByinfos(40000, 60000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore6less8(bIDateService.countPayByinfos(60000, 80000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore8less10(bIDateService.countPayByinfos(80000, 100000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore10less12(bIDateService.countPayByinfos(100000, 120000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore12less15(bIDateService.countPayByinfos(120000, 150000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore15less20(bIDateService.countPayByinfos(150000, 200000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore20less30(bIDateService.countPayByinfos(200000, 300000, session4.toString(), collegeId, professionId, -1));
            biCountPay4.setMore30(bIDateService.countPayByinfos(300000, -1, session4.toString(), collegeId, professionId, -1));
            
            BICountPay biCountPay3 = new BICountPay();
            biCountPay3.setLess4(bIDateService.countPayByinfos(0, 40000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore4less6(bIDateService.countPayByinfos(40000, 60000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore6less8(bIDateService.countPayByinfos(60000, 80000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore8less10(bIDateService.countPayByinfos(80000, 100000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore10less12(bIDateService.countPayByinfos(100000, 120000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore12less15(bIDateService.countPayByinfos(120000, 150000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore15less20(bIDateService.countPayByinfos(150000, 200000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore20less30(bIDateService.countPayByinfos(200000, 300000, session3.toString(), collegeId, professionId, -1));
            biCountPay3.setMore30(bIDateService.countPayByinfos(300000, -1, session3.toString(), collegeId, professionId, -1));
            
            BICountPay biCountPay2 = new BICountPay();
            biCountPay2.setLess4(bIDateService.countPayByinfos(0, 40000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore4less6(bIDateService.countPayByinfos(40000, 60000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore6less8(bIDateService.countPayByinfos(60000, 80000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore8less10(bIDateService.countPayByinfos(80000, 100000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore10less12(bIDateService.countPayByinfos(100000, 120000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore12less15(bIDateService.countPayByinfos(120000, 150000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore15less20(bIDateService.countPayByinfos(150000, 200000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore20less30(bIDateService.countPayByinfos(200000, 300000, session2.toString(), collegeId, professionId, -1));
            biCountPay2.setMore30(bIDateService.countPayByinfos(300000, -1, session2.toString(), collegeId, professionId, -1));
            
            BICountPay biCountPay1 = new BICountPay();
            biCountPay1.setLess4(bIDateService.countPayByinfos(0, 40000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore4less6(bIDateService.countPayByinfos(40000, 60000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore6less8(bIDateService.countPayByinfos(60000, 80000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore8less10(bIDateService.countPayByinfos(80000, 100000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore10less12(bIDateService.countPayByinfos(100000, 120000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore12less15(bIDateService.countPayByinfos(120000, 150000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore15less20(bIDateService.countPayByinfos(150000, 200000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore20less30(bIDateService.countPayByinfos(200000, 300000, session1.toString(), collegeId, professionId, -1));
            biCountPay1.setMore30(bIDateService.countPayByinfos(300000, -1, session1.toString(), collegeId, professionId, -1));
            
            List<Map<String, BICountPay>> lists = new ArrayList<Map<String, BICountPay>>();
            Map<String, BICountPay> map = new HashMap<String, BICountPay>();
            map.put(session5.toString(), biCountPay5);
            map.put(session4.toString(), biCountPay4);
            map.put(session3.toString(), biCountPay3);
            map.put(session2.toString(), biCountPay2);
            map.put(session1.toString(), biCountPay1);
            lists.add(map);
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
    
    @RequestMapping(value = "/methoddata", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String methoddata(
            @RequestParam(value = "session", required = false) String session,
            @RequestParam(value = "collegeId", required = false) Integer collegeId,
            @RequestParam(value = "professionId", required = false) Integer professionId,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            if(collegeId==null){
                collegeId=-1;
            }
            if(professionId==null){
                professionId=-1;
            }
            
            Integer session5 = Integer.parseInt(session);
            Integer session4 = session5-1;
            Integer session3 = session5-2;
            Integer session2 = session5-3;
            Integer session1 = session5-4;
            
            List<Map<String, Object>> lists5 =  bIDateService.countMethodByinfos(session5.toString(), collegeId, professionId,-1);
            List<Map<String, Object>> lists4 =  bIDateService.countMethodByinfos(session4.toString(), collegeId, professionId,-1);
            List<Map<String, Object>> lists3 =  bIDateService.countMethodByinfos(session3.toString(), collegeId, professionId,-1);
            List<Map<String, Object>> lists2 =  bIDateService.countMethodByinfos(session2.toString(), collegeId, professionId,-1);
            List<Map<String, Object>> lists1 =  bIDateService.countMethodByinfos(session1.toString(), collegeId, professionId,-1);
            
            List<Map<String,List<Map<String, Object>>>> lists= new ArrayList<Map<String,List<Map<String, Object>>>>();
            Map<String,List<Map<String, Object>>> map = new HashMap<String, List<Map<String,Object>>>();
            map.put(session5.toString(), lists5);
            map.put(session4.toString(), lists4);
            map.put(session3.toString(), lists3);
            map.put(session2.toString(), lists2);
            map.put(session1.toString(), lists1);
            lists.add(map);
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
    
    

}
