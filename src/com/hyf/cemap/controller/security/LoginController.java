package com.hyf.cemap.controller.security;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.security.Role;
import com.hyf.cemap.dao.UserDao;
import com.hyf.cemap.service.UserDetailServiceImpl;

/**
 * 登陆相关操作
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.setViewName("login/index");
        return modelAndView;
    }
	
    @RequestMapping(value = "/register", method = {RequestMethod.GET})
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.setViewName("login/register");
        return modelAndView;
    }
    
    @RequestMapping(value = "/modify", method = {RequestMethod.GET})
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.setViewName("login/modify");
        return modelAndView;
    }
    
    @RequestMapping(value = "/profile", method = {RequestMethod.GET})
	public ModelAndView profile(HttpServletRequest request, HttpServletResponse response){
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = userDetails.getUsername();
	    Member member = userDetailServiceImpl.findUserByUsername(username);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("member", member);
    	modelAndView.setViewName("login/profile");
        return modelAndView;
    }
    
    @RequestMapping(value = "/registermb", method = {RequestMethod.GET})
	public ModelAndView registermb(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.setViewName("login/registermb");
        return modelAndView;
    }
    
    @RequestMapping(value = "/checkMobile", method = {RequestMethod.GET}, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String checkMobile(HttpServletRequest request, HttpServletResponse response){
    	String mobile = request.getParameter("mobile");
    	Member member = userDetailServiceImpl.findUserByMobile(mobile);
    	if(member != null){
    		return "true";
    	}
    	else{
    		return "false";
    	}
    }
    
    @RequestMapping(value = "/isMobileCanModify", method = {RequestMethod.GET}, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String isMobileCanModify(HttpServletRequest request, HttpServletResponse response){
    	String mobile = request.getParameter("mobile");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	Member member = userDetailServiceImpl.findUserByUsername(username);
    	
    	Member mobilemember = userDetailServiceImpl.findUserByMobile(mobile);
    	
    	if(mobilemember == null){
    		return "true";
    	}
    	else if(mobilemember.getId() == member.getId()){
    		return "true";
    	}
    	else{
    		return "false";
    	}
    }
    
    @RequestMapping(value = "/auth", method = {RequestMethod.GET})
	public ModelAndView auth(HttpServletRequest request, HttpServletResponse response){
    	String path = "";
    	try{
           UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           String username = userDetails.getUsername();
           System.out.println("用户已登录"+username);
        	path = "login/index";
    	}
    	catch(Exception e){
    		path = "login/auth";
    	}

        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.setViewName(path);
        return modelAndView;
    }
    
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    @ResponseBody
    public String addMember(HttpServletRequest request, HttpServletResponse response){
        Member member = new Member();
        member.setUsername("admin");
    	Md5PasswordEncoder md5 = new Md5PasswordEncoder(); 
    	md5.setEncodeHashAsBase64(false);
    	String password = md5.encodePassword("admin", null); 
    	member.setPassword(password);
        
        Set<Role> set = new HashSet<Role>();
        Role ro = userDao.findRoleByRoleCode("ADMIN");
        set.add(ro);
        member.setRoles(set);
        userDao.saveUser(member);
        
        return "true";
    }
    
    /**
     * 异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("serverError");
        mv.addObject("exception", ex.getMessage());
        ex.printStackTrace();
        return mv;
    }
    
}
