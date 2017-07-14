<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>编辑</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<link href="${ctxStatic}/user/css/plugins/chosen/chosen.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
         <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row form-body form-horizontal m-t">
                        
                        <!-- ********* Form start  *********  -->
	                    <form class="form-horizontal m-t" id="modifyform">
	                    	<input type="hidden" id="memberid" value="${member.id}"/>
	                    	<input type="hidden" id="manageClass1" value="${member.manageClass}"/>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">用户名：</label>
									<div class="col-sm-4">
										<input type="text" id="username" name="username" class="form-control required"
											placeholder="请输入用户名" value="${member.username}" disabled="disabled">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">真实名：</label>
									<div class="col-sm-4">
										<input type="text" id="realname" name="realname" class="form-control required"
											placeholder="请输入真实名" value="${member.realname}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">手机号：</label>
									<div class="col-sm-4">
										<input type="text" id="phone" name="phone" class="form-control required isPhone"
											placeholder="请输入手机号" value="${member.phone}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">邮箱：</label>
									<div class="col-sm-4">
										<input type="text" id="email" name="email" class="form-control required email"
											placeholder="请输入邮箱" value="${member.email}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">管理班级权限：</label>
									<div class="col-sm-4">
										<select data-placeholder="选择班级" id="manageClass" name="manageClass"
											class="chosen-select" multiple style="width:350px;" tabindex="4">
		                                    <c:forEach items="${administrativeClasss}" var="administrativeClass">
				        					<option value="${administrativeClass.id}" hassubinfo="true">${administrativeClass.name}</option>
				        					</c:forEach> 
		                                </select>	
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
	
	<script src="${ctxStatic}/user/js/plugins/chosen/chosen.jquery.js"></script>
	
	<script>
	window.onload = afterload;
    function afterload(){
    	var manageClass = $("#manageClass1").val();
    	chse1('#manageClass',manageClass);
    	$(".chosen-select").chosen();
    	
    	/* $("#manageClass option[value='110000']").attr('selected','selected');
    	$("#manageClass").trigger("listzt:updated");
    	$(".chosen-select").chosen(); */
    }
    
    function chse1(select,values){
    	var arr = values.split(',');
    	var length = values.length;
    	var value ='';
    	for(i=0;i<length;i++){
    		value = arr[i];
    		$(select+" option[value='"+value+"']").attr('selected','selected');
    	}
    	$(select).trigger("listzt:updated");
    }
	</script>
	<script type="text/javascript">
	
	$().ready(function () {
		// 在键盘按下并释放及提交后验证提交表单
		$("#modifyform").validate({
			rules: {
				username: {
					maxlength: 10,
				},
				password: {
					maxlength: 15,
				},
			},
			submitHandler: function (form) {
			 	var manageClasss ='';
				$("#manageClass option:selected").each(function(){
					manageClasss += $(this).val()+",";
				});
				
				$.ajax({
					type : "post",
					url : "${ctxStatic}/teachermember/update",
					data : {
						id : $("#memberid").val(),
						username : $("#username").val(),
						realname : $("#realname").val(),
						phone : $("#phone").val(),
						email : $("#email").val(),
						manageClass: manageClasss,
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
		});
	});
	
	</script>
</body>

</html>