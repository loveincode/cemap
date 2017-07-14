<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>CEMAP-高校就业管理分析平台</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<meta name="viewport" content="user-scalable=no",width="device-width">
</head>
<style>
	.white{
		color:#fff;
	}
	.ibox-content ul{
		list-style-type:circle;
	}
	.ibox-content ul li{
		margin-top:5px;
		margin-left:-15px;
	}
	.ibox-content ul li span{
		float:right;
	}
</style>
<body class="gray-bg top-navigation">
	<%@include file="/WEB-INF/views/font/top.jsp" %>
    <!-- ******************* 轮播图 Start ********************* -->
        <div class="row" id="back1" style="background: url(${ctxStatic}/show/image/biye1.jpg) center no-repeat;background-size: cover;color: cadetblue;" >
        </br>
            <div class="col-xs-5 col-xs-offset-1" style="background-color: white;opacity:0.8" >
                <div>
                    <h2><b>快捷通道</b></h2>
                    <table class="table" >                        
                        <tbody>
                        	<tr>
                                <td>
                                    <button class="btn btn-primary dim btn-large-dim" type="button">
                                   	<a href="${ctxStatic}/show/news32/_1"><font color="white">公</font></a>
                                    </button>
                                    公告
                                </td>
                                <td>
									<button class="btn btn-warning dim btn-large-dim" type="button">
                                    <a href="${ctxStatic}/show/news33/_1"><font color="white">资</font></a>
                                    </button>
                                    资讯
                                </td>
                                <td>
	                               <button class="btn btn-danger dim btn-large-dim" type="button">
                                    <a href="${ctxStatic}/show/news34/_1"><font color="white">政</font></a>
                                    </button>
                                    政策
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-info dim btn-large-dim" type="button">
                                    <a href="${ctxStatic}/show/preachingmeeting/_1"><font color="white">宣</font></a>
                                    </button>
                                    宣讲会
                                </td>
                                <td>
                                    <button class="btn btn-default dim btn-large-dim" type="button">
                                    <a href="${ctxStatic}/show/employmentinformation/_1"><i class="fa fa-handshake-o"></i></a>
                                    </button>
                                    招聘信息
                                </td>
                                <td>
                                    <button class="btn btn-primary dim btn-large-dim" type="button">
									<a href="${ctxStatic}/login/auth" target="_blank"><font color="white">后</font></a>
									</button>
                                    后台入口
                                </td>
                            </tr>
                            <!-- <tr>
                                <td>
                                    <button type="button" class="btn btn-info m-r-sm">宣</button>
                                    宣讲会
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success m-r-sm">招</button>
                                    招聘信息
                                </td>
                                <td>
                                    <button class="btn btn-info btn-circle btn-lg" type="button"><i class="fa fa-industry"></i></button>
                                    分析报表
                                </td>
                            </tr> -->
                            
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-xs-5 " style="opacity:0.9">
                <div class="ibox float-e-margins">  
                    <div class="ibox-content ">
                        <div class="carousel slide" id="carousel2">
                            <ol class="carousel-indicators">
                                <li data-slide-to="0" data-target="#carousel2" class="active"></li>
                                <li data-slide-to="1" data-target="#carousel2"></li>
                                <li data-slide-to="2" data-target="#carousel2" class=""></li>
                            </ol>
                            <div class="carousel-inner" >
                                <div class="item active">
                                    <img alt="image" class="img-responsive" src="${ctxStatic}/show/image/biye1.jpg" style="width:540px;height:295px">
                                    <div class="carousel-caption" >
                                        <h3>关于高校毕业生档案传递有关事项</h3>
                                    </div>
                                </div>
                                <div class="item ">
                                    <img alt="image" class="img-responsive" src="${ctxStatic}/show/image/biye2.jpg" style="width:540px;height:295px">
                                    <div class="carousel-caption" >
										<h3>关于高校毕业生档案传递有关事项</h3>
                                    </div>
                                </div>
                                <div class="item">
                                    <img alt="image" class="img-responsive" src="${ctxStatic}/show/image/biye3.jpg" style="width:540px;height:295px">
                                    <div class="carousel-caption" >
										<h3>关于高校毕业生档案传递有关事项</h3>
                                    </div>
                                </div>
                            </div>
                            <a data-slide="prev" href="carousel.html#carousel2" class="left carousel-control">
                                <span class="icon-prev"></span>
                            </a>
                            <a data-slide="next" href="carousel.html#carousel2" class="right carousel-control">
                                <span class="icon-next"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ******************* 轮播图  End  ********************* -->

        
        <div id="wrapper">
            <div id="page-wrapper" class="gray-bg">

                <!-- ******************* middle Start ********************* -->
                <div class="wrapper wrapper-content">
                    <div class="container">
                    <!-- ******************* row1 Start ********************* -->
                        <div class="row">
                            <!-- ******************* 公告 Start ********************* -->
                            <div class="col-md-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <span class="label label-success pull-right"><a class="white" href="${ctxStatic}/show/news32/_1">MORE</a></span>
                                        <h3>公告</h3>
                                    </div>
                                    <div class="ibox-content">
                                        <ul>
                                       		<c:forEach items="${newsgg}" var="newsonegg">
                                       			 <li>  <a href="${ctxStatic}/show/newsdetail/${newsonegg.id}"
                                                title="${newsonegg.title}">
                                                <c:if test="${fn:length(newsonegg.title) > 15 }">${fn:substring(newsonegg.title,0,15)}...</c:if>
                                                <c:if test="${fn:length(newsonegg.title) <= 15}">${newsonegg.title}</c:if>
                                                </a><span><fmt:formatDate value="${newsonegg.publishDate}" pattern="yyyy-MM-dd"/></span></li>
											</c:forEach>  
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- ******************* 公告  End  ********************* -->

                            <!-- ******************* 资讯 Start ********************* -->
                            <div class="col-md-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <span class="label label-info pull-right"><a class="white" href="${ctxStatic}/show/news33/_1">MORE</a></span>
                                        <h3>资讯</h3>
                                    </div>
                                    <div class="ibox-content">
                                       <ul>
                                           <c:forEach items="${newszx}" var="newsonezx">
                                       			 <li>  <a href="${ctxStatic}/show/newsdetail/${newsonezx.id}"
                                                title="${newsonezx.title}">
                                                <c:if test="${fn:length(newsonezx.title) > 15 }">${fn:substring(newsonezx.title,0,15)}...</c:if>
                                                <c:if test="${fn:length(newsonezx.title) <= 15}">${newsonezx.title}</c:if>
                                                </a><span><fmt:formatDate value="${newsonezx.publishDate}" pattern="yyyy-MM-dd"/></span></li>
											</c:forEach>  
                                        </ul>
                                        
                                    </div>
                                </div>
                            </div>
                            <!-- ******************* 资讯  End  ********************* -->

                            <!-- ******************* 政策 Start ********************* -->
                            <div class="col-md-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <span class="label label-warning pull-right"><a class="white" href="${ctxStatic}/show/news34/_1">MORE</a></span>
                                        <h3>政策</h3>
                                    </div>
                                    <div class="ibox-content">
                                        <ul>
                                           <c:forEach items="${newszc}" var="newsonezc">
                                       			 <li>  <a href="${ctxStatic}/show/newsdetail/${newsonezc.id}"
                                                title="${newsonezc.title}">
                                                <c:if test="${fn:length(newsonezc.title) > 15 }">${fn:substring(newsonezc.title,0,15)}...</c:if>
                                                <c:if test="${fn:length(newsonezc.title) <= 15}">${newsonezc.title}</c:if>
                                                </a><span><fmt:formatDate value="${newsonezc.publishDate}" pattern="yyyy-MM-dd"/></span>
                                                </li>
											</c:forEach>  
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- ******************* 政策  End  ********************* -->
                        </div>
                        <!-- ******************* row1 End ********************* -->

                        <!-- ******************* row2 Start ********************* -->
                        <div class="row">
                            <!-- ******************* 招聘信息 Start ********************* -->
                            <div class="col-lg-5">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <span class="label label-danger pull-right"><a class="white" href="${ctxStatic}/show/employmentinformation/_1">MORE</a></span>
                                        <h5>招聘信息</h5>
                                    </div>
                                    <div class="ibox-content">
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>主题</th>
                                                        <th>单位</th>
                                                        <th>截止日期</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${recruitmentInformations}" var="recruitmentInformation">
                                                	<tr>
                                                        <td><a href="${ctxStatic}/show/employmentinformationdetail/${recruitmentInformation.uuid}" title="${recruitmentInformation.theme}">
                                                        <c:if test="${fn:length(recruitmentInformation.theme) > 15 }">${fn:substring(recruitmentInformation.theme,0,15)}...</c:if>
                                                		<c:if test="${fn:length(recruitmentInformation.theme) <= 15}">${recruitmentInformation.theme}</c:if>
                                                        </a></td>
                                                        <td title="${recruitmentInformation.companyName}">
                                                        <c:if test="${fn:length(recruitmentInformation.companyName) > 5 }">${fn:substring(recruitmentInformation.companyName,0,5)}...</c:if>
                                                		<c:if test="${fn:length(recruitmentInformation.companyName) <= 5}">${recruitmentInformation.companyName}</c:if>
                                                        </td>
                                                        <td>${recruitmentInformation.deadline}</td>
                                                    </tr>
												</c:forEach>  
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ******************* 招聘信息  End  ********************* -->

                            <!-- ******************* 宣讲会 Start ********************* -->
                            <div class="col-lg-7">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <span class="label label-default pull-right"><a class="white" href="${ctxStatic}/show/preachingmeeting/_1">MORE</a></span>
                                        <h5>宣讲会</h5>
                                    </div>
                                    <div class="ibox-content">
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>单位</th>
                                                        <th>地点</th>
                                                        <th>日期</th>
                                                        <th>时间段</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${preachingMeetings}" var="preachingMeeting">
                                                	<tr>
                                                        <td><a href="${ctxStatic}/show/preachingmeetingdetail/${preachingMeeting.uuid}" title="${preachingMeeting.companyName}">
                                                        <c:if test="${fn:length(preachingMeeting.companyName) > 10 }">${fn:substring(preachingMeeting.companyName,0,10)}...</c:if>
                                                		<c:if test="${fn:length(preachingMeeting.companyName) <= 10}">${preachingMeeting.companyName}</c:if>
                                                        </a></td>
                                                        <td title="${preachingMeeting.place}">
                                                        <c:if test="${fn:length(preachingMeeting.place) > 8 }">${fn:substring(preachingMeeting.place,0,8)}...</c:if>
                                                		<c:if test="${fn:length(preachingMeeting.place) <= 8}">${preachingMeeting.place}</c:if>
                                                        </td>
                                                        <td>${preachingMeeting.dayDate}</td>
                                                        <td>${preachingMeeting.period}</td>
                                                    </tr>
												</c:forEach>  
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ******************* 宣讲会 End ********************* -->
                        </div>
                        <!-- ******************* row2 End ********************* -->
                    </div>
                </div>
                <!-- ******************* middle End ********************* -->
               
                <!-- ******************* 友情链接 Start ********************* -->
                <div class="row">
                    <center>
                    <span>友情链接&nbsp;: </span>&nbsp; 
                    <a target="_blank" href="http://www.ncss.org.cn/">教育部就业平台</a> &nbsp; | &nbsp; 
                    <a target="_blank" href="http://www.shehr.com.cn/">上海市教育就业</a> &nbsp;| &nbsp; 
                    <a target="_blank" href="http://www.firstjob.com.cn/">上海市就业处</a>&nbsp; | &nbsp; 
                    <a target="_blank" href="http://www.yjbys.com/">校园网就业</a> &nbsp; | &nbsp; 
                    <a target="_blank" href="http://jwc.sspu.edu.cn/">就业网</a> &nbsp; | &nbsp; 
                    <a target="_blank" href="${ctxStatic}/login/auth">学生就业管理系统</a>
                    <br/>
                    <br/>
                    </center>
                </div>
                <!-- ******************* 友情链接 End ********************* -->
            </div>
        </div>
        
        <%@include file="/WEB-INF/views/font/bottom.jsp" %>
    
	<!-- 全局js -->
	<%@include file="/WEB-INF/views/common/commonjs.jsp" %>
	
</body>

</html>