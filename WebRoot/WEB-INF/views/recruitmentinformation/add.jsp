<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
<title>添加</title>
<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
<link href="${ctxStatic}/user/css/plugins/summernote/summernote.css" rel="stylesheet">
<link href="${ctxStatic}/user/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
<link href="${ctxStatic}/user/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
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
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-sm-4 control-label">单位名称：</label>
										<div class="col-sm-8">
											<input type="text" id="companyName" name="companyName" class="form-control required"
												placeholder="请输入单位名称">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-sm-3 control-label">单位类型：</label>
										<div class="col-sm-7">
											<select class="form-control" id="companyType" name="companyType">
													<option>国有企业</option>
													<option>民营企业</option>
													<option>党政机关</option>
													<option>高等教育单位</option>
													<option>其他事业单位</option>
													<option>三资企业</option>
													<option>科研设计单位</option>
													<option>中初教育单位</option>
													<option>部队单位</option>
													<option>医疗卫生单位</option>
												</select>
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-sm-4 control-label">主题：</label>
										<div class="col-sm-8">
											<input type="text" id="theme" name="theme" class="form-control required"
												placeholder="请输入主题">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group" id="data_1">
										<label class="col-sm-3 control-label">招聘截止日期：</label>
										<div class="col-sm-7">
												<div class="input-group date">
													<span class="input-group-addon"><i
														class="fa fa-calendar"></i></span> 
														<input type="text" name="deadline" id="deadline"
														class="form-control required dateISO" value="" readonly="readonly">
												</div>
											</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-sm-4 control-label">投递邮箱：</label>
										<div class="col-sm-8">
											<input type="text" id="email" name="email" class="form-control required email"
												placeholder="请输入邮箱">
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
					    		<div class="col-md-1"></div>
							    <div class="col-md-10">
					                <div class="ibox float-e-margins">
			    		                <div class="ibox-title">
			            	            <h5>招聘描述</h5>
			                	    	    <div class="ibox-tools">
			                            		<a class="collapse-link">
			                                	<i class="fa fa-chevron-up"></i>
			                            		</a>
			                    	    	</div>
			                    		</div>
			                    		<div class="ibox-content no-padding">
			                        		<div class="summernote"></div>
			                    		</div>
			                		</div>
			            		</div>
			        	    </div>
							
							<div class="col-md-6 col-sm-offset-5">
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
	
	<!-- SUMMERNOTE -->
    <script src="${ctxStatic}/user/js/plugins/summernote/summernote.min.js"></script>
    <script src="${ctxStatic}/user/js/plugins/summernote/summernote-zh-CN.js"></script>
    
    <!-- DataPicker -->
    <script src="${ctxStatic}/user/js/plugins/datapicker/bootstrap-datepicker.js"></script>
	
	<script src="${ctxStatic}/js/ajaxfileupload.js"></script>
	
	<script type="text/javascript">
	
		 $("#data_1 .input-group.date").datepicker({
            todayBtn : "linked",
            keyboardNavigation : !1,
            forceParse : !1,
            calendarWeeks : !0,
            autoclose : !0,
            startDate:new Date(),
        })
		
		$(document).ready(function(){
            $('.summernote').summernote({
                  lang:"zh-CN",
				  height: 230,
				  callbacks: {
				    onImageUpload: function(files) {
				      //上传图片到服务器，使用了formData对象，至于兼容性...据说对低版本IE不太友好
				      var formData = new FormData();
				      formData.append('file',files[0]);
				      $.ajax({
				        url : '../image/uploadImage',//后台文件上传接口
				        type : 'POST',
				        data : formData,
				        processData : false,
				        contentType : false,
				        success : function(data) {
				          var imageurl = "../image/readImage?name="+data.fileName;
				          $('.summernote').summernote('insertImage',imageurl,'img');
				          alert($('.summernote').summernote('code'));
				        },
				        error: function (data, status, e) { 
		                 alert(123);
		                }
				      });
				    }
				  }
			});
        });
	
		//数据校验
	$().ready(function () {
		// 在键盘按下并释放及提交后验证提交表单
		$("#addform").validate({
			rules: {
				companyName: {
					maxlength: 20,
				},
				theme: {
					maxlength: 30,
				},
			},
			submitHandler: function (form) {
				var sHTML = $('.summernote').summernote('code');
				 $.ajax({
					type : "post",
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					url : "add",
					data : {
						companyName : $("#companyName").val(),
						companyType : $("#companyType").val(),
						theme: $("#theme").val(),
						deadline: $("#deadline").val(),
						email: $("#email").val(),
						description:sHTML,
					},
					dataType : "json",
					async : true,
					success : function(data) {
						alert(data.message);
	               		console.log(data);
						window.location.href = "../recruitmentinformation/index";
					},
					error : function(data) {
						alert("error");
					}
				});
			}
		})
		;
	});
	</script>
</body>
</html>