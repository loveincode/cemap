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

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>密码修改</title>

<link href="<%=path%>/user/css/bootstrap.min.css?v=3.4.0"
	rel="stylesheet">
<link href="<%=path%>/user/css/font-awesome.min.css?v=4.3.0"
	rel="stylesheet">
<link href="<%=path%>/user/css/animate.min.css" rel="stylesheet">
<link href="<%=path%>/user/css/style.min.css?v=3.2.0" rel="stylesheet">
<style type="text/css">
.form-single-img .img-box {
    width: 130px;
    height: 0;
    padding-bottom: 82px;
    position: relative;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    background-color: #fff;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    box-shadow: 0 0 0 1px #a89e99 inset;
}
.form-single-img .img-box.plus:after {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    z-index: 2;
    width: 100%;
    height: 100%;
    background-image: url(<%=path%>/user/img/icon-cross.png);
    background-repeat: no-repeat;
    background-size: 34px;
    background-position: center;
}
.form-single-img .img-box input[type=file] {
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    z-index: 100;
    opacity: 0;
}

</style>
</head>

<body class="gray-bg">
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>密码重置</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> <a
						class="close-link"> <i class="fa fa-times"></i> </a>
				</div>
			</div>
			<div class="ibox-content">
				<form class="form-horizontal m-t" id="signupForm">
					
					<div class="form-group">
						<label class="col-sm-3 control-label">密码：</label>
						<div class="col-sm-3">
							<input id="password1" name="password1"
								class="form-control" type="password" aria-required="true"
								aria-invalid="true" class="error"  placeholder="输入密码">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">确认密码：</label>
						<div class="col-sm-3">
							<input id="password2" name="password2" 
								class="form-control" type="password" aria-required="true"
								aria-invalid="true" class="error"  onblur="onblus()" placeholder="输入与上面相同的密码">
							<font id="errorpass" style="display:none" color="red">两次输入的密码不同，请重新输入</font>
						</div>
					</div>

					
					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-3">
							<button id="subbutton"  onClick="user_submit();" class="btn btn-primary" type="button">提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>

	</div>


	<!-- 全局js -->
	<script src="<%=path%>/user/js/jquery-2.1.1.min.js"></script>
	<script src="<%=path%>/user/js/bootstrap.min.js?v=3.4.0"></script>

	<!-- 自定义js -->
	<script src="<%=path%>/user/js/content.min.js?v=1.0.0"></script>

	<!-- jQuery Validation plugin javascript-->
	<script src="<%=path%>/user/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="<%=path%>/user/js/plugins/validate/messages_zh.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<script>
		function onblus(){ 
			if($("#password1").val()!=$("#password2").val()){
				$('#errorpass').css('display','block');
			}
			else{
				$('#errorpass').css('display','none');
			}
		}
	</script>
	<script type="text/javascript">
		var type = '${type}';
		function user_submit() {
		if($("#password1").val()==$("#password2").val()){
			$.ajax({
				type : "post",
				url : "<%=path%>/member/passwordupdate",
				data : {
					password : $("#password2").val(),
				},
				dataType : "text",
				async : true,
				success : function(data) {
					alert("succeed");
				},
				error : function(data) {
					alert("error");
				}
			});
		}
		else{
			$('#errorpass').css('display','block');
		}
		};
		
	</script>
</body>

</html>