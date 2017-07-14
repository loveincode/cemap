<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=no">
    <link rel="stylesheet" type="text/css" href="<%=path%>/show/bootstrap/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="<%=path%>/about/css/animate.css"> -
    <title>关于我们</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/about/css/index.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/about/css/about.css" /> 
    <link href="<%=path%>/show/css/c.css" rel="stylesheet">
	<link href="<%=path%>/show/css/dht.css" rel="stylesheet">
	<script src="<%=path%>/show/js/gotop.js"></script>    
 	<link href="<%=path%>/show/css/index.css" rel="stylesheet">
 	<link rel="apple-touch-icon" href="<%=path%>/show/image/schoollogo.png" />
<link rel="shortcut icon" href="<%=path%>/show/image/yflogo.png">
</head>
<body>
<jsp:include page="top.jsp" />
<section class="container" style="padding-top:80px">
    <div class="company-summary">
        <h1>公司简介</h1>
        <div class="row summary-control">
            <div class="col-xs-9">
                <p>上海一凡信息科技有限公司成立于2016年12月。2017年正式开放运营。一凡科技是一家专注于Web网站技术及
                    云服务领域的创新型企业，结合科研能力及商业实践经验，不断寻求大数据的价值实现，尤其是在工业领域
                    实现数据应用闭环。
                </p>
                <p>一凡科技希望成为顶尖的工业大数据一站式解决方案提供商，并与客户共同成长，形成各细分领域的专业大数
                    据应用模型和最佳实践。
                </p>
            </div>
            <div class="col-xs-3 logo-control">
                <img src="../show/image/yflogo.png" style="width: 96px;" alt="YIFAN LOGO">
                <p>一凡科技</p>
            </div>
        </div>
    </div>
    <div class="company-summary">
        <h1>我们做什么？</h1>
        <div class="row summary-control">
            <div class="col-xs-9">
                <img src="../show/image/about.jpg"/>
            </div>

        </div>
    </div>
    <div class="company-history">
        <h1>公司发展历史</h1>
        <div class="row summary-control">
            <div class="col-xs-9 history-control">
                    <h4>2016</h4>
                    <li><span>12月</span>上海一凡信息科技正式成立</li>
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp" /> 
</body>
</html>