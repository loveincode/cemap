<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
<title>添加</title>
<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
         <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>新增</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row form-body form-horizontal m-t">
                       		
                        <!-- ********* Form start  *********  -->
	                    <form class="form-horizontal m-t" id="addform">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">类型code：</label>
									<div class="col-sm-4">
										<input type="text" id="code" name="code" class="form-control required" 
											placeholder="请输入类型code">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">类型名称：</label>
									<div class="col-sm-4">
										<input type="text" id="name" name="name" class="form-control required"
											placeholder="请输入类型名称">
									</div>
								</div>
							</div>
							
							<div class="col-md-7 col-sm-offset-5">
								<button  class="btn btn-primary"  type="submit"   >
								<i class="fa fa-check"> </i>新增</button>
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
	
	<script type="text/javascript">
		//数据校验
	$().ready(function () {
		// 在键盘按下并释放及提交后验证提交表单
		$("#addform").validate({
			rules: {
				code: {
					maxlength: 10,
				},
				name:{
					maxlength: 10,
				}
			},
			submitHandler: function (form) {
				  $.ajax({
					type : "post",
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					url : "add",
					data : {
						name : $("#name").val(),
						code : $("#code").val(),
					},
					dataType : "json",
					async : true,
					success : function(data) {
						alert(data.message);
	               		console.log(data);
						window.location.href = "../newstype/index";
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