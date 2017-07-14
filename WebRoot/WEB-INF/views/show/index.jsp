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
<title>一凡科技</title>
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

	<div class="container-fluid" style="padding:80px;padding-bottom: 0;">
		<center>
			<!-- 轮播 -->
			<div class="row-fluid" id="lb">
				<div class="span12">
					<div class="carousel slide" id="carousel-272493">
						<ol class="carousel-indicators">
							<li class="active" data-slide-to="0"
								data-target="#carousel-272493"></li>
							<li data-slide-to="1" data-target="#carousel-272493"></li>
							<li data-slide-to="2" data-target="#carousel-272493"></li>
							<li data-slide-to="3" data-target="#carousel-272493"></li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<img id="lbimg" alt="" src="${indexPage.carouselImg1}" />
							</div>
							<div class="item">
								<img id="lbimg" alt="" src="${indexPage.carouselImg2}" />
							</div>
							<div class="item">
								<img id="lbimg" alt="" src="${indexPage.carouselImg3}" />
							</div>
							<div class="item">
								<img id="lbimg" alt="" src="${indexPage.carouselImg4}" />
							</div>
						</div>
						<a data-slide="prev" href="#carousel-272493"
							class="left carousel-control">‹</a> <a data-slide="next"
							href="#carousel-272493" class="right carousel-control">›</a>
					</div>
				</div>
			</div>

		</center>
		<!-- 9个模块 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-1" id="testborder1">
				<div class="bt">
					<h4>
						<a href="tongzhi.html" style="color:#004E72"><b>公司动态</b> </a>
					</h4>
				</div>
				<ul>
					<c:forEach items="${news1}" var="anew1">
						<li><span><fmt:formatDate value="${anew1.create_date}" pattern="yyyy.MM.dd HH:mm"/></span> <a href="newsdetail?id=${anew1.id}"
						title="${anew1.newsTitle}">${anew1.newsTitle}</a></li>
					</c:forEach>  
				</ul>
				<p style="float:right">
					<a href="newslist">更多...</a>
				</p>

			</div>
			<div class="col-md-2" id="testborder2"
				style="padding-left: 20px;">
				<h4>
					<a href="fsx.html" style="color:#004E72"><b>公司产品</b> </a>
				</h4>
				<ul>
					<c:forEach items="${products}" var="product">
						<li><a href="productdetail?id=${product.id}" >${product.productName }</a></li>
					</c:forEach>  
					
				</ul>
				
			</div>
			
			<div class="col-md-3" id="testborder3" style="padding-left: 20px;">
				<h4>
					<a href="video" style="color:#004E72"><b>宣传视频</b> <img
						src="<%=path%>/show/image/video.png" width="35px" height="30px"> </a>
				</h4>

				<div class="span12">
					<div class="media">
						<a href="video" class="pull-left"><img
							src="image/updown.png" class="media-object" alt='fc_video.html'
							height="130px" width="210px" class="img-rounded" /> </a>
						<div class="media-body">
							<a href="fc_video.html">最新宣传片《Rise》</a>
						</div>
					</div>
				</div>
			</div>
		</div>

			<br/>

		<div class="row">
			<div class="col-md-4 col-md-offset-1" id="testborder4">
				<h4>
					<a href="news.html" style="color:#004E72"><b>业界资讯</b> </a>
				</h4>
				<ul>
					<c:forEach items="${news2}" var="anew">
						<li><span><fmt:formatDate value="${anew.create_date}" pattern="yyyy.MM.dd HH:mm"/></span> <a href="newsdetail?id=${anew.id}"
						title="${anew.newsTitle}">${anew.newsTitle}</a></li>
					</c:forEach>  
				</ul>
				<p style="float:right">
					<a href="newslist">更多...</a>
				</p>
			</div>
			<div class="col-md-2" id="testborder5">
				<h4>
					<b><a href="about" style="color:#004E72">关于我们</a>
					</b>
				</h4>
				<ul style="padding-left:10px">
					<li><a href="about" title="我们是做什么的？">我们是做什么的？</a></li>
			
				</ul>
				<p style="float:right">
					<a href="about">更多...</a>
				</p>
			</div>
			<div class="col-md-3" id="testborder6" style="padding-left: 90px;">
				<a href="newsdetail?id=3"><h3 style="color:#ffffff">加入我们</h3> <img
					src="<%=path%>/show/image/help1.png" width="110px">
				</a>
			</div>
		</div>
		

		<br />
		<div class=" col-md-offset-1" id="friendlink">
			<span>友情链接&nbsp;: </span>&nbsp; <a target="_blank"
				href="https://www.aliyun.com">阿里巴巴集团</a> &nbsp; | &nbsp; <a
				target="_blank" href="http://www.ibm.com/cn-zh/">IBM</a> &nbsp;
			| &nbsp; <a target="_blank" href="http://www.facebook.com/">Facebook</a>
			&nbsp; | &nbsp; <a target="_blank" href="http://www.cas.cn/">
				中科院</a> &nbsp; | &nbsp; <a target="_blank"
				href="http://www.sei.cmu.edu/">卡耐基梅隆大学软件工程研究院</a> &nbsp; | &nbsp; 
		</div>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>

	

</body>

</html>

