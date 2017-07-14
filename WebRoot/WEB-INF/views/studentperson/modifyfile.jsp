<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>编辑</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<link href="${ctxStatic}/user/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
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
	                    	<input type="hidden" id="graduationFileid" value="${graduationFile.id}"/>
	                    	<input type="hidden" id="studentId" value="${graduationFile.studentId}"/>
	                    	
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">接受档案机构名称：</label>
									<div class="col-sm-5">
										<input type="text" id="institutionName" name="institutionName" class="form-control required"
											placeholder="接受档案机构名称" value="${graduationFile.institutionName}">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">机构地址：</label>
									<div class="col-sm-5">
										<input type="text" id="institutionPlace" name="institutionPlace" class="form-control required"
											placeholder="机构地址" value="${graduationFile.institutionPlace}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">机构电话：</label>
									<div class="col-sm-5">
										<input type="text" id="institutionPhone" name="institutionPhone" class="form-control required isTel"
											placeholder="机构电话" value="${graduationFile.institutionPhone}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12 ">
								<div class="form-group">
									<label class="col-sm-4 control-label">转出方式：</label>
									<div class="col-sm-5">
										<input type="text" id="outWay" name="outWay" class="form-control required"
											placeholder="转出方式" value="${graduationFile.outWay}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group" id="data_1">
									<label class="col-sm-4 control-label">转出时间：</label>
									<div class="col-sm-5">
										<div class="input-group date">
										<span class="input-group-addon"><i
												class="fa fa-calendar"></i></span> 
												<input type="text" name="outDate" id="outDate"
												class="form-control required dateISO" value="${graduationFile.outDate}" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">描述：</label>
									<div class="col-sm-5">
										<textarea id="description" name="description" class="form-control">${graduationFile.description}</textarea>
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
	
	<!-- DataPicker -->
    <script src="${ctxStatic}/user/js/plugins/datapicker/bootstrap-datepicker.js"></script>

	<script src="${ctxStatic}/js/ajaxfileupload.js"></script>
	<script>
		$(window).load(function() {
		});
	</script>
	<script type="text/javascript">
		
		$("#data_1 .input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,calendarWeeks:!0,autoclose:!0})
		
		//数据校验
		$().ready(function () {
			// 在键盘按下并释放及提交后验证提交表单
			$("#modifyform").validate({
				rules: {
					institutionName:{
						maxlength:20,
					},
				},
				submitHandler: function (form) {
					$.ajax({
					type : "post",
					url : "saveorupdateFile",
					data : {
						id : $("#graduationFileid").val(),
						Uuid : $("#Uuid").val(),
						studentId : $("#studentId").val(),
						institutionName : $("#institutionName").val(),
						institutionPlace : $("#institutionPlace").val(),
						institutionPhone : $("#institutionPhone").val(),
						outWay : $("#outWay").val(),
						outDate : $("#outDate").val(),
						description : $("#description").val(),
					},
					dataType : "json",
					async : true,
					success : function(data) {
						alert(data.message);
	               		console.log(data);
						window.location.href = "../studentperson/saveorupdateFilePage";
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