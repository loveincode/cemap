package com.hyf.cemap.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest )req;
		HttpSession session = request.getSession();  
		
		String href = request.getRequestURL().toString();
		String url = request.getRequestURL().toString();
		
		if(request.getQueryString()!=null){
			href += ("?")+request.getQueryString();
		}
		
		//记录未登陆时，最后一次访问的url，因为会重定向到/weixin/login_mobile,login_type_check 所以，该url不需要被记录
		if( !url.endsWith(".css") && !url.endsWith(".js") && 
			!url.endsWith(".png") && !url.endsWith(".jpg") && 
			!url.endsWith(".gif") && !url.endsWith(".map") && 
			!url.endsWith(".min") && !url.endsWith(".txt") && 
			!url.endsWith(".ttf") && !url.endsWith(".ico") &&
			!url.endsWith("login") && !url.contains("login_type_check")){
			session.setAttribute("LOGIN_REDIRECT_URL", href);
		}
		filterChain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("初始化拦截过滤器！");
	}

}
