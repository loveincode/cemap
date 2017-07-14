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
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head>
<title>产品：${product.productName }</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=path%>/show/bootstrap/css/bootstrap.min.css">
<!-- Buttons 库的核心文件 -->
<link rel="stylesheet" type="text/css" href="<%=path%>/show/bootstrap/css/buttons.css">
<script src="<%=path%>/show/bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=path%>/show/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="bootstrap/js/html5shiv.min.js"></script>
      <script src="bootstrap/js/respond.min.js"></script>
    <![endif]-->


<!-- 只有使用字体图标时才需要加载 Font-Awesome -->
<link
	href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<link href="<%=path%>/show/css/c.css" rel="stylesheet">
<link href="<%=path%>/show/css/dht.css" rel="stylesheet">
<script src="<%=path%>/show/js/dht.js"></script>    
<script src="<%=path%>/show/js/gotop.js"></script>   
 <link href="<%=path%>/show/css/index.css" rel="stylesheet">
<link rel="apple-touch-icon" href="<%=path%>/show/image/schoollogo.png" />
<link rel="shortcut icon" href="<%=path%>/show/image/yflogo.png">

</head>
<body>

	<jsp:include page="top.jsp"></jsp:include>

	<body style="padding:70px; " id="bd">


	<div class="row">
		<div class="col-md-8  col-md-offset-2" id="content">
			<ol class="breadcrumb">
				<li><a href="index">首页</a></li>
				<li><a href="productlist">${product.productType.name } 公司产品</a></li>
				<li><a href="#">${product.productName }</a></li>
			</ol>
			<h3 style="text-align:center"><b>${product.productName }</b></h3>
			
			<h4>产品图片</h4>
			<img src="${product.productImg }" style="width:50%"/>
<p>
			<h4>产品类型</h4>${product.productType.name}
			<h4>产品信息</h4>
			${product.productInfo}
			<h4>产品价格</h4>${product.productPrice}￥
</p>
		</div>
	</div>
	
	<jsp:include page="bottom.jsp"></jsp:include>


</body>

</html>

