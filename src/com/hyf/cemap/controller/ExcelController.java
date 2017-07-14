package com.hyf.cemap.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.security.Role;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.dao.UserDao;
import com.hyf.cemap.service.GraduateInformationServiceImpl;
import com.hyf.cemap.service.MemberServiceImpl;
import com.hyf.cemap.util.common.CEMAPConstants;
import com.hyf.cemap.util.poi.ReadExcel;

/**
 * excel导入操作
*  @author  huyifan
*  @ClassName  ExcelController  
*  @date  Mar 28, 2017 11:07:04 AM
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private GraduateInformationServiceImpl graduateInformationService;
    
    @Autowired 
    private ReadExcel readExcel;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private MemberServiceImpl memberService;
    
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
        modelAndView.setViewName("excel/index");
        return modelAndView;
    }
    
    
    
    
    /**
     * 保存
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveData", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveData(
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        int yescount = 0;
        try {
            String attach = request.getParameter("attach");
            List<Object> list = readExcel.read(attach);
            GraduateInformation graduateInformation = null;
            System.out.println("过滤后传过来的毕业生信息数据量"+list.size());
            for (int i = 0; i < list.size()-1; i++) {
               graduateInformation = (GraduateInformation)list.get(i);
               graduateInformation.setUuid(UUID.randomUUID().toString());
               graduateInformationService.save(graduateInformation);
               //新增一个 student用户
               Set<Role> roles = new HashSet<Role>();
               Role role = new Role();
               role = userDao.findRoleByRoleCode(CEMAPConstants.CEMAP_ROLE_CODE_STUDENT);
               roles.add(role);
               Member member = new Member();
               // 学号 为 用户登录名 密码 为 默认123456
               member.setUsername(graduateInformation.getStudentId());
               member.setRealname(graduateInformation.getName());
               member.setPhone(graduateInformation.getPhone());
               member.setEmail(graduateInformation.getEmail());
               member.setClassId(graduateInformation.getAdministrativeClassId());
               member.setUuid(UUID.randomUUID().toString());
               member.setPassword("123456");
               member.setRoles(roles);
               member.setTempRoleId(4);
               memberService.save(member);
               yescount++;
            }
            String s = "成功导入 "+yescount+"条信息 </br>"
                    + "以下为错误信息报告：<br/>";
            s += (String)list.get(list.size()-1);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
            resultVO.setData(s);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
        }
        return resultVO.toString();
    }
}
