<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
<title>公司产品</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/show/bootstrap/css/bootstrap.min.css">
<!-- Buttons 库的核心文件 -->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/show/bootstrap/css/buttons.css">
	
<script src="<%=path%>/show/bootstrap/js/jquery.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/show/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>

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
    <link rel="shortcut icon" href="../user/img/logo-sico.ico">
<style type="text/css">
.rank-block:before,rank-block:after{ content:""; display:block; clear:both;}
.rank-block li{ box-sizing:border-box; padding:25px 10px; background-color:#fff; margin-bottom:10px;}
.rank-block li:before,.rank-block li:after{ content:""; display:block; clear:both;}
.rank-block li > div:before,.rank-block li > div:after{content:""; display:block; clear:both;}
.rank-block li .info{ color:#8694a0; font-size:12px;}
.rank-block li img{ display:block; width:75px; height:75px; float:left; border-radius:10px; margin-right:20px;}
.rank-block li .title{ display:block; float:left; width:78%;}
.rank-block li .title h5{ margin:0; margin-bottom:10px; padding:0; color:#12161b; font-size:18px;}

</style>
</head>
<body>

	<jsp:include page="top.jsp"></jsp:include>
<body style="padding:70px; " id="bd">


	<div class="row">
		<center>
			<div class="row" style="width: 50%">
				<ol class="breadcrumb">
					<li><a href="index">首页</a></li>
					<li><a href="productlist">公司产品</a></li>
				</ol>
			</div>

			<div class="col-sm-8 col-sm-offset-2">
		<div class="mt-20">
		<table id="data-Table" class="table table-border table-bordered table-bg table-hover table-sort" >
								<thead>
									<tr class="info">
										<th>产品名称</th>
										<th>缩略图</th>
										<th>产品类型</th>
										<th>价格</th>
										<th>详情</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
		</table>
		</div>
		</div>

		</center>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>


</body>
<script src="<%=path%>/js/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script>

	var username;
    var mobile;
	$(document).ready(function(){
		
		var table = $('#data-Table').dataTable( {
		    "lengthChange":false,
		    "info": false,
			"pagingType": "full_numbers",
			"searching":false,
			"processing" : false,
			"serverSide" : true,
	        "ajax": {
	        	url:"<%=path%>/product/data",
	    		headers: {
				        Accept: "application/json; charset=utf-8"
				    },
				type: "post",
			    data: function(request){   	
				    	if(username !=null && username!="")
				    	{
				    		request.f_username = username;
				    	}
				    	if(mobile !=null && mobile!="")
				    	{
				    		request.mobile = mobile;
				    	}
			    	return request;
			        }
	        },
	        "columns": [
	            { "data": "productName" },
	            { "data": "productImg","orderable":false},
	            { "data": "productType.name","orderable":false},
	            { "data": "productPrice","orderable":false},
	            { "data": "id","orderable":false},
	        ], 
	        "columnDefs": [
	            {
	            "defaultContent":"",
	            "targets":['_all']
	            },
	            {
	            "render": function(data, type, row) {
	                return "<img src=\'"+data+"\' height='100' width=‘100'></img>";
	            },
	            "targets": 1},
	            {
	            "render": function(data, type, row) {
	                return "<a href='<%=path%>/show/productdetail?id=" + data + "'><i class=\"fa fa-search-plus text-navy\"></i></a>";
	            },
	            "targets": 4}
	        ]
	    } );
	    }); 
</script>

</html>

