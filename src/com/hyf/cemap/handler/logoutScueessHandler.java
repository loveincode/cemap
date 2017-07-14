package com.hyf.cemap.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.hyf.cemap.bean.po.log.LogInOut;
import com.hyf.cemap.service.log.LogInOutServiceImpl;

public class logoutScueessHandler implements LogoutSuccessHandler{

    @Autowired
    private LogInOutServiceImpl logInOutService;
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
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
        //退出
        logInOut.setAction("退出");
        try {
            logInOutService.save(logInOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SavedRequestAwareAuthenticationSuccessHandler ash = new SavedRequestAwareAuthenticationSuccessHandler();
        ash.onAuthenticationSuccess(request, response, authentication);
    }
}
