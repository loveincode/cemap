<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
<title>密码重置</title>
<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
         <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>密码强制重置</h5>
				</div>
				
				<div class="ibox-content">
              		<div class="row form-body form-horizontal m-t">
                       		
						<!-- ********* Form start  *********  -->
		                <form class="form-horizontal m-t" id="modifyform">
		                	
		                	<input type="hidden" id="memberid" value="${member.id}"/>
		                	
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">密码：</label>
									<div class="col-sm-4">
										<input type="password" id="password1" name="password1" class="form-control"
											placeholder="请输入密码">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">确认密码：</label>
									<div class="col-sm-4">
										<input type="password" id="password2" name="password2" class="form-control"
											placeholder="请确认密码"  onblur="onblus()"> 
										<font id="errorpass" style="display:none" color="red">两次输入的密码不同，请重新输入</font>
									</div>
								</div>
							</div>
							
							<div class="col-md-7 col-sm-offset-5">
								<button  class="btn btn-primary"  type="submit">
								<i class="fa fa-check"> </i>更新</button>
							</div>
						</form>
						<!-- ********* Form end  *********  -->
                   </div>
				</div>
			</div>
		</div>
	</div>
</div>



	<!-- 全局js -->
	<%@include file="/WEB-INF/views/common/commonjs.jsp" %>
	
	<!-- jQuery Validation plugin javascript-->
	<script src="${ctxStatic}/user/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctxStatic}/user/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctxStatic}/js/ajaxfileupload.js"></script>
	
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
	
	$().ready(function () {
		// 在键盘按下并释放及提交后验证提交表单
		$("#modifyform").validate({
			rules: {
				password: {
					maxlength: 15,
				},
				password1: {
				    minlength:6,
					maxlength: 15,
				},
				password2: {
				    minlength:6,
					maxlength: 15,
				},
			},
			submitHandler: function (form) {
				if($("#password1").val()==$("#password2").val()){
				$.ajax({
					type : "post",
					url : "${ctxStatic}/teachermember/passwordupdate",
					data : {
						id : $("#memberid").val(),
						password : $("#password2").val(),
					},
					dataType : "json",
					async : true,
					success : function(data) {
						alert(data.message);
	               		console.log(data);
						window.location.href = "${ctxStatic}/teachermember/index";
					},
					error : function(data) {
						alert("error");
					}
				});
			}
			else{
				$('#errorpass').css('display','block');
			}
			}
		});
	});
	</script>
</body>
</html>