/** 
* Copyright 2016 Shudao Info Tech Co. Ltd.  All rights reserved.
* @author lhzeng
* @date 2016-4-14 上午9:14:27 
* @version V1.0.1
*/
package com.hyf.cemap.util.common;

import org.apache.commons.lang.SystemUtils;

/**
 * 
*  @author  huyifan
*  @ClassName  CEMAPConstants  
*  @date  2016年11月24日 上午10:14:23
 */
public class CEMAPConstants {
	
	//--------------------------- 角色  code------------------------------------------
	// 超级管理员
	public final static String CEMAP_ROLE_CODE_ADMIN = "ROLE_ADMIN";
	
	// 老师 - 就业办
	public final static String CEMAP_ROLE_CODE_TEACHER_ADMIN = "ROLE_TEACHER_ADMIN";
	
	// 学院领导 
    public final static String CEMAP_ROLE_CODE_COLLEGE_ADMIN = "ROLE_COLLEGE_ADMIN";
	
	// 老师 - 辅导员
	public final static String CEMAP_ROLE_CODE_TEACHER = "ROLE_TEACHER";
	
	// 学生
	public final static String CEMAP_ROLE_CODE_STUDENT = "ROLE_STUDENT";
	
	
	//---------------------------File Directory-------------------------------------------
	//  图片目录
	public final static String imgDir = SystemUtils.USER_HOME + "/img/";
	
	//	附件目录
	public final static String fileDir = SystemUtils.USER_HOME + "/file/";
	
	
}
