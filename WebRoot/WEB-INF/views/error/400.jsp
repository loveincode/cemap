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
	<title>400-错误请求</title>
</head>

<body>

<section class="wrap-global">
	<div class="error">
		<img src="<%=basePath%>/static/images/400.png" alt="400, 错误的请求">
		<div class="link">
			<a href="#">刷新一下</a>
			<a href="<%=basePath%>">回到首页</a>
			<a href="#">联系管理员</a>
		</div>
	</div>
</section>

</body>

</html>
