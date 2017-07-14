<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>CEMAP-高校就业管理分析平台-资讯详情</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<meta name="viewport" content="user-scalable=no",width="device-width">
</head>
<style>
</style>
<body class="gray-bg top-navigation">
	<%@include file="/WEB-INF/views/font/top.jsp" %>
    
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">
                    
                    	<ol class="breadcrumb">
							<li><a href="${ctxStatic}/show/index">首页</a></li>
							<li><a href="#">资讯公告</a></li>
							<!-- <li><a href="#">新闻名</a></li> -->
						</ol>
                        
                        <div class="text-center article-title">
                            <h1>
                                 ${newsone.title}
                            </h1>
                        </div>
                        <p>
                            ${newsone.content}
                        </p>
                        
                        <div class="pull-right">
                            <button class="btn btn-white btn-xs" type="button">发布时间 ：<fmt:formatDate value="${newsone.publishDate}" pattern="yyyy-MM-dd HH:mm"/> </button>
                        </div>
                        <div class="pull-right">
                            <button class="btn btn-white btn-xs" type="button">浏览次数 ： ${newsone.newsClick}次</button>
                        </div>
                        <hr>

                        <div class="row">
                            <div class="col-lg-12">
								<c:if test="${newsone.attachName!='' }">
                                <h2>附件：</h2>
                                <div class="social-feed-box">
                                    <div class="social-avatar">
                                        <a href="${ctxStatic}/file/downloadFile?fileName=${newsone.attach}&oldFileName=${newsone.attachName}" class="pull-left">
                                            <button class="btn btn-primary dim" type="button">
                                    		<i class="fa fa-file"></i>
                                    		</button>
                                        </a>
                                        <div class="media-body">
                                        	${newsone.attachName}
                                            <small class="text-muted"><a href="${ctxStatic}/file/downloadFile?fileName=${newsone.attach}&oldFileName=${newsone.attachName}">下载</a></small>
                                        </div>
                                    </div>
                                    <div class="social-body">
                                    </div>
                                </div>
								</c:if>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>

    </div>
    
    
    
    <%@include file="/WEB-INF/views/font/bottom.jsp" %>
    
	<!-- 全局js -->
	<%@include file="/WEB-INF/views/common/commonjs.jsp" %>
	
</body>
</html>