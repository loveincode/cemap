package com.hyf.cemap.util.persistence;

import java.beans.PropertyEditorSupport;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
/**
 * 
*  @author  huyifan
*  @ClassName  BaseController  
*  @date  2017年3月7日 下午6:59:08
 */
public abstract class BaseController {
	
	protected Logger LOG = LoggerFactory.getLogger(getClass());
	
	/**
	 * 初始化数据绑定
	 * @param WebDataBinder binder
	 * @return
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				//过滤非法符号
				//setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
	}
	
	/**
	 * 异常页面跳转400页面
	 * @param BindException
	 * 		  ConstraintViolationException
	 * 		  ValidationException
	 * @return 400
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String exceptionHandler400() {
        return "error/400";
    }
	
	/**
	 * 异常页面跳转403页面
	 * @param AuthenticationException
	 * @return 403
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String exceptionHandler403() {
        return "error/403";
    }
	
	/**
	 * 异常页面跳转404页面
	 * @param ClassNotFoundException
	 * @return 404
	 */
	@ExceptionHandler({ClassNotFoundException.class})
    public String exceptionHandler404() {
        return "error/404";
    }
	
	/**
	 * 异常页面跳转403页面
	 * @param Exception
	 * @return 500
	 */
	@ExceptionHandler({Exception.class})
    public String exceptionHandler500() {
        return "error/500";
    }

	/**
	 * 调试用
	 * 打印接收参数
	 * @param request
	 */
	protected void debugPrintParameters(HttpServletRequest request)
	{
		Enumeration<String> en =  request.getParameterNames();
		while(en.hasMoreElements())
		{
			String name = en.nextElement();
			System.out.println(name+" : "+request.getParameter(name));
		}
	}

	/*
	 * 将HTTPRequest中所有参数转为可读Map形态
	 */
	@SuppressWarnings("unchecked")
	public Map getParameterMap(HttpServletRequest request) {  
	    // 参数Map  
	    Map properties = request.getParameterMap();  
	    // 返回值Map  
	    Map returnMap = new HashMap();  
	    Iterator entries = properties.entrySet().iterator();  
	    Map.Entry entry;  
	    String name = "";  
	    String value = "";  
	    while (entries.hasNext()) {  
	        entry = (Map.Entry) entries.next();  
	        name = (String) entry.getKey();  
	        Object valueObj = entry.getValue();  
	        if(null == valueObj){  
	            value = "";  
	        }else if(valueObj instanceof String[]){  
	            String[] values = (String[])valueObj;  
	            for(int i=0;i<values.length;i++){  
	                value = values[i] + ",";  
	            }  
	            value = value.substring(0, value.length()-1);  
	        }else{  
	            value = valueObj.toString();  
	        }  
	        System.out.println("getParameterMap() Name : " + name + "; Value : " + value);
	        returnMap.put(name, value);  
	    }  
	    return returnMap;  
	}  	
}
