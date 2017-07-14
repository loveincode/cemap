<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>高校就业管理分析平台-CEMAP</title>
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link href="../user/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="../user/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="../user/css/animate.min.css" rel="stylesheet">
    <link href="../user/css/style.min.css?v=4.0.0" rel="stylesheet">
	<link href="../show/image/yflogo.ico" rel="shortcut icon" >
</head>

<body class="fixed-sidebar full-height-layout gray-bg  pace-done" style="overflow:hidden">
<div class="pace  pace-inactive">
    <div class="pace-progress" data-progress-text="100%" data-progress="99" style="width: 100%;">
        <div class="pace-progress-inner"></div>
    </div>
    <div class="pace-activity"></div>
</div>
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side navbar-static-side-gray" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
            <div class="sidebar-collapse" style="width: auto; height: 100%;">
                <ul class="nav nav-gray" id="side-menu">
                    <li class="nav-header navbar-gray logo">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <img src="../user/img/logo-yifan-houtai.png" alt="LOGO">
                                <span>高校就业管理分析平台</span>
                            </a>
                        </div>
                        <div class="logo-element">CEMAP
                        </div>
                    </li>
                    <!-- 
	                    管理员：	ROLE_ADMIN	
			        	就业处：	ROLE_TEACHER_ADMIN  
			        	辅导员：	ROLE_TEACHER
			        	学生：  	ROLE_STUDENT  
		        	-->
		                    
                    <li>
                        <a class="J_menuItem" href="../login/profile"><i class="fa fa-home"></i> <span class="nav-label">主页</span></a>
                    </li>
                    
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN">
                   <li>
                        <a href="index.html#"><i class="fa fa-cubes"></i> <span class="nav-label">学校基础信息管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            
                            <li>
                                <a class="J_menuItem" href="../college/index">学院管理 </a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="../profession/index">专业管理 </a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="../administrativeclass/index">班级管理 </a>
                            </li>
                           
                        </ul>
                    </li> 
                    </security:authorize>
                    
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN,ROLE_TEACHER">
                    <li>
                        <a href="index.html#"><i class="fa fa-book"></i> <span class="nav-label">毕业生基础数据</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            
                            <li>
                                <a class="J_menuItem" href="../graduateinformation/index">毕业生信息管理 </a>
                                <a class="J_menuItem" href="../graduateinformation/notemploy">未登记就业信息统计 </a>
                                <a class="J_menuItem" href="../graduateinformation/notfile">未登记档案信息统计 </a>
                                <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN">
                                <a class="J_menuItem" href="../excel/index">基础数据导入(excel)</a>
                                </security:authorize>
                            </li>
                           
                        </ul>
                    </li>
                    </security:authorize>
                    
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN">
                      <li class="">
                        <a href="#">
                            <i class="fa fa-newspaper-o"></i>
                            <span class="nav-label">资讯管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse" aria-expanded="false" style="height: 0px;">
                            <li>
    							<a class="J_menuItem" href="../news/index">资讯管理 </a>
    							<a class="J_menuItem" href="../news/ligeruitable">资讯管理（ligerui测试） </a>
                                <a class="J_menuItem" href="../newstype/index">资讯类型管理 </a>                           
                            </li>
                        </ul>
                    </li>
                    </security:authorize>
                    
                     <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN,ROLE_TEACHER">
                      <li class="">
                        <a href="#">
                            <i class="fa fa-handshake-o"></i>
                            <span class="nav-label">招聘管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse" aria-expanded="false" style="height: 0px;">
                            <li>
    							<a class="J_menuItem" href="../recruitmentinformation/index">招聘信息管理 </a>
                                <a class="J_menuItem" href="../preachingmeeting/index">宣讲会管理 </a>                           
                            </li>
                        </ul>
                    </li>
                    </security:authorize>
                    
                    
                    
                    
                    <li>
                        <a href="index.html#"><i class="fa fa-database"></i> <span class="nav-label">数据可视化展现</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="../bischool/index">学校可视化展现 </a>
                                <a class="J_menuItem" href="../bicollege/index">学院可视化展现 </a>
                                <a class="J_menuItem" href="../biprofession/index">专业可视化展现 </a>
                                <a class="J_menuItem" href="../biclass/index">班级可视化展现 </a>
                            </li>
                        </ul>
                    </li> 
                    
                    <li>
                        <a href="index.html#"><i class="fa fa-line-chart"></i> <span class="nav-label">历年对比分析</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="../bicompareschool/index">学校Compare </a>
                                <a class="J_menuItem" href="../bicomparecollege/index">学院Compare</a>
                                <a class="J_menuItem" href="../bicompareprofession/index">专业Compare </a>
                            </li>
                        </ul>
                    </li> 
                    
                    
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN,ROLE_TEACHER">
                     <li>
                        <a href="index.html#"><i class="fa fa-user"></i> <span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            
                            <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <li>
                                <a class="J_menuItem" href="../adminmember/index">管理员管理 </a>
                            </li>
                            </security:authorize>
                            <security:authorize ifAnyGranted="ROLE_ADMIN">
                             <li>
                                <a class="J_menuItem" href="../teacheradminmember/index">就业处老师管理 </a>
                            </li>
                            </security:authorize>
                            <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN">
                            <li>
                                <a class="J_menuItem" href="../collegeadminmember/index">学院领导管理 </a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="../teachermember/index">辅导员管理 </a>
                            </li>
                            </security:authorize>
                            <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN,ROLE_TEACHER">
                             <li>
                                <a class="J_menuItem" href="../studentmember/index">学生管理 </a>
                            </li>
                           </security:authorize>
                        </ul>
                    </li> 
                    </security:authorize>
                    
                    <li>
                        <a href="index.html#"><i class="fa fa-black-tie"></i> <span class="nav-label">个人中心</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            
                            <li>
                                <a class="J_menuItem" href="../personal/modifyPage">个人信息 </a>
                            </li>
                           <li>
                                <a class="J_menuItem" href="../personal/passwordModifyPage">密码修改 </a>
                            </li>
                        </ul>
                    </li> 
                    
                    <security:authorize ifAnyGranted="ROLE_STUDENT">
                    <li>
                        <a href="index.html#"><i class="fa fa-address-card-o"></i><span class="nav-label">毕业生个人中心</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                               <a class="J_menuItem" href="../studentperson/saveorupdateInformationPage">毕业生个人信息</a>
                            </li>
                            <li>
                               <a class="J_menuItem" href="../studentperson/saveorupdateFilePage">档案转送信息</a>
                            </li>
                            <li>
                               <a class="J_menuItem" href="../studentperson/saveorupdateEmployPage">就业信息登记</a>
                            </li>
                            
                        </ul>
                    </li>
                    </security:authorize>

					 <!-- <li>
                        <a href="index.html#"><i class="fa fa-cogs"></i><span class="nav-label">高校信息配置</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                               <a class="J_menuItem" href="../indexPage/information">高校信息</a>
                            </li>
                            <li>
                               <a class="J_menuItem" href="../indexPage/information">主页配置</a>
                            </li>
                            
                        </ul>
                    </li>   -->  
                    
                    <li>
                        <a href="index.html#"><i class="fa fa-cogs"></i><span class="nav-label">日志管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                               <a class="J_menuItem" href="../loginout/index">登录登出</a>
                            </li>
                            <li>
                               <a class="J_menuItem" href="../logchangepassword/index">密码修改记录</a>
                            </li>
                        </ul>
                    </li>                 
                   
                </ul>
                <div class="copyright copyright-gray">
                    <!--<a data-toggle="dropdown" class="dropdown-toggle" href="#">-->
                    <div class="profile-element">
                        <img style="width: 30px;" src="../user/img/logo-yifan-houtai.png" alt="LOGO">
                        <span>高校就业管理分析平台</span>
                         ©️Yifan.all rights reserved.
                    </div>
                    <div class="logo-element">CEMAP</div>
                    <!--</a>-->
                   
                </div>
            </div>
            <div class="slimScrollBar"
                 style="background: rgb(0, 0, 0); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 682px;"></div>
            <div class="slimScrollRail"
                 style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.9; z-index: 90; right: 1px;"></div>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top blue-bg" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-gray hexagon hexagon-gray" href="#">
                        <i class="fa fa-bars"></i>
                    </a>
                </div>
                <!--class="nav navbar-top-links navbar-right-->
                <div class="dropdown navUserAvatar navbar-right">
                    <span><img alt="image" class="img-circle" src="../user/img/logo-yifan-houtai.png"></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">
                                <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold"><security:authentication property='principal.username'/></strong></span>
                                <security:authorize ifAnyGranted="ROLE_SUPER_ADMIN">
                                <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_ADMIN">
                                <span class="text-muted text-xs block">管理员<b class="caret"></b></span>
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_TEACHER_ADMIN">
                                <span class="text-muted text-xs block">就业处<b class="caret"></b></span>
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_COLLEGE_ADMIN">
                                <span class="text-muted text-xs block">学院领导<b class="caret"></b></span>
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_TEACHER">
                                <span class="text-muted text-xs block">辅导员<b class="caret"></b></span>
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_STUDENT">
                                <span class="text-muted text-xs block">学生<b class="caret"></b></span>
                                </security:authorize>
                                </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="../personal/modifyPage" data-index="1">个人信息</a>
                        </li>
                        <li><a class="J_menuItem" href="../personal/passwordModifyPage" data-index="1">密码修改</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="../j_spring_security_logout">安全退出</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-angle-double-left"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-angle-double-right"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="../j_spring_security_logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="../login/profile" frameborder="0" data-id="index_v1.html" seamless=""></iframe>
        </div>
		
    </div>
    <!--右侧部分结束-->


</div>
<script src="../user/js/jquery.min.js?v=2.1.4"></script>
<script src="../user/js/bootstrap.min.js?v=3.3.5"></script>
<script src="../user/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="../user/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="../user/js/plugins/layer/layer.min.js"></script>
<script src="../user/js/hplus.min.js?v=4.0.0"></script>
<script type="text/javascript" src="../user/js/contabs.min.js"></script>
<script src="../user/js/plugins/pace/pace.min.js"></script>


</body>

</html>
