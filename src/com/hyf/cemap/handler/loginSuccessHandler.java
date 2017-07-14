package com.hyf.cemap.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.hyf.cemap.bean.po.log.LogInOut;
import com.hyf.cemap.service.log.LogInOutServiceImpl;

public class loginSuccessHandler implements AuthenticationSuccessHandler{
    
    @Autowired
    private LogInOutServiceImpl logInOutService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        UserDetails uds = (UserDetails)authentication.getPrincipal();
        WebAuthenticationDetails wauth = (WebAuthenticationDetails)authentication.getDetails();
       
        LogInOut logInOut = new LogInOut();
        //获得用户名
        logInOut.setUsername(uds.getUsername());
        //记录登录时间
        logInOut.setDate(new Date());
        //获得IP地址
        logInOut.setIpAdress(wauth.getRemoteAddress());
        //登录
        logInOut.setAction("登录");
        try {
            logInOutService.save(logInOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用spring security 跳转的方法
        SavedRequestAwareAuthenticationSuccessHandler ash = new SavedRequestAwareAuthenticationSuccessHandler();
        ash.onAuthenticationSuccess(request, response, authentication);
    }
    
}
