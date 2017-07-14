<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>CEMAP-高校就业管理分析平台-宣讲会详情</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<meta name="viewport" content="user-scalable=no",width="device-width">
	
	<style type="text/css">
	
	.dl-horizontal dt{
		font-size:20px
	}
	.dl-horizontal dd{
		font-size:22px
	}
	
	</style>
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
							<li><a href="#">招聘服务</a></li>
							<li><a href="${ctxStatic}/show/preachingmeeting/_1">宣讲会</a></li>
						</ol>
                        
                        <div class="text-center article-title">
                            <h1>
                                   ${preachingMeeting.companyName} 宣讲会
                            </h1>
                        </div>
                        
                        <div class="col-sm-5">
                                <dl class="dl-horizontal">
                                    <dt>日期:</dt><dd>${preachingMeeting.dayDate}</dd>
			                        <dt>时间段:</dt><dd>${preachingMeeting.period}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-7" id="cluster_info">
                                <dl class="dl-horizontal">
                                	<dt>地点:</dt><dd>${preachingMeeting.place}</dd>
									<dt>单位类型:</dt><dd>${preachingMeeting.companyType}</dd>
                                </dl>
                            </div>
                        
                        <p>
                        	${preachingMeeting.description}
                        </p>
                        
                        <hr>



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