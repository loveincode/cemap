package com.hyf.cemap.controller.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.service.UserDetailServiceImpl;
import com.hyf.cemap.service.log.LogChangePasswordServiceImpl;
import com.hyf.cemap.util.common.DataTableUtil;

@Controller
@RequestMapping(value = "/logchangepassword")
public class LogChangePasswordController {

    @Autowired
    private LogChangePasswordServiceImpl logChangePasswordService;
    
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    /**
     * 主页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public ModelAndView indexPage(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("log/logchangepassword/index");
        return modelAndView;
    }
    
    /**
     * 获取数据
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/data", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String data(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 获取查询参数
         */
        String name = request.getParameter("name");
        if(name != null && name.equals("")){
            name = null;
        }
        DataTableUtil dataTableUtil = new DataTableUtil(request);
        /**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //辅导员和学生只能看到自己的登录登出信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]") || userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_STUDENT]")) {
            try {
                // 总记录数
                Integer recordsTotal = logChangePasswordService.listLogChangePasswordByinfo(memberonline.getUsername(),-1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = logChangePasswordService.listLogChangePasswordByinfo(memberonline.getUsername(),-1, -1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        logChangePasswordService.listLogChangePasswordByinfo(memberonline.getUsername(),dataTableUtil.getPage(),
                                    dataTableUtil.getLength()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                // 总记录数
                Integer recordsTotal = logChangePasswordService.listAll(-1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = logChangePasswordService.listLogChangePasswordByinfo(name,-1, -1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        logChangePasswordService.listLogChangePasswordByinfo(name,dataTableUtil.getPage(),
                                    dataTableUtil.getLength()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataTableUtil.result();
    }
    
}
