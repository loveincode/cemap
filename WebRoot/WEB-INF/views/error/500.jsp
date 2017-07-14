<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String basePath = request.getContextPath();
%>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>/static/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>/static/css/wrong.css">
	<title>服务器异常</title>
</head>

<body>

<section class="wrap-global">
	<div class="error">
		<img src="<%=basePath%>/static/images/500.png" alt="500, 该页面暂时无法显示">
		<div class="link">
			<a href="#">刷新一下</a>
			<a href="<%=basePath%>">回到首页</a>
			<a href="#">联系管理员</a>
		</div>
	</div>
</section>

</body>

</html>
