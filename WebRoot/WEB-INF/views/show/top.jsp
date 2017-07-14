<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	<nav class="navbar navbar-default navbar-fixed-top" id="dht">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=path%>/show/index"><img id="blogo1"
					src="<%=path%>/show/image/logo.png"> </a> <a class="navbar-brand" href="index.html">
				</a>
				<p class="navbar-text">
					<a href="<%=path%>/show/index">欢迎广大客户！</a>
				</p>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="bt1"><a href="<%=path%>/show/index">首页</a>
						
					<li class="bt2"><a href="<%=path%>/show/newslist">公司新闻</a>
						
					<li class="bt3"><a href="<%=path%>/show/productlist">公司产品</a>
					
					<li class="bt6"><a href="<%=path%>/show/about">关于我们</a>
						
					<li class="bt6"><a href="newsdetail?id=3">加入我们</a>
				</ul>
				<%-- <form action="##" class="navbar-form navbar-left" rol="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入关键词"
							style="width:120px" />
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form> --%>
				
			</div>
		</div>
	</nav>