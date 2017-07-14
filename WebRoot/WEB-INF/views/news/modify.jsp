<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>编辑</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<link href="${ctxStatic}/user/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="${ctxStatic}/user/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
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
	
	                    	<div style="display:none" id="destext">${news.content}</div>
	                    	
	                    	<input type="hidden" id="newsid" value="${news.id}"/>
	                    	
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">资讯标题：</label>
									<div class="col-sm-5">
										<input type="text" id="title" name="title" class="form-control required"
											value="${news.title}">
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">资讯类型：</label>
									<div class="col-sm-5">
										<select class="form-control required name="newsType" id="newsType">
					        			 	<c:forEach items="${newsTypes}" var="newsType">
					        				<option value="${newsType.id}">${newsType.name}</option>
					        				</c:forEach>   
					      				</select>
									</div>
									<input type="hidden" id="newsTypeID" value="${news.newsType.id}">
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">附件：</label>
									<div class="col-sm-6">
										<div class="form-single-file">
											<div class="img-box plus">
												<div style="display:none" id="fileinfo"><font color="green"> 上传成功 选择文件重新上传</font></div>
												<div id="fileinfo1"><font color="green">${news.attachName}</font></div>
												<input type="file" id="file1" name="file1" >
												<span>(文件大小:小于20M,文件格式:doc,docx,xls,xlsx,ppt,txt,pdf,zip,rar,7z)</span>
												<input type="hidden" name="attach" id="attach" value="${news.attach}">
												<input type="hidden" name="attachName" id="attachName" value="${news.attachName}">
					   				   		</div>
									</div>
								</div>
							</div>
							
							<div class="row">
					    		<div class="col-sm-2"></div>
							    <div class="col-sm-9">
					                <div class="ibox float-e-margins">
			    		                <div class="ibox-title">
			            	            <h5>资讯内容</h5>
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
			        	    
			        	    <div class="col-md-7 col-sm-offset-5">
								<button  class="btn btn-primary"  type="submit"   >
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
	
	<!-- SUMMERNOTE -->
    <script src="../user/js/plugins/summernote/summernote.min.js"></script>
    <script src="../user/js/plugins/summernote/summernote-zh-CN.js"></script>

	<script src="${ctxStatic}/js/ajaxfileupload.js"></script>
	
	<script type="text/javascript">
	
	window.onload = afterload;
    function afterload(){
	    var tmp = $("#destext").html();
	    $('.summernote').summernote('code', tmp);
	    var tmp = $("#newsTypeID").val();
	    $("#newsType").val(tmp);
    }
	
	$(document).ready(function(){
    	$('.summernote').summernote({
			lang:"zh-CN",
			callbacks: {
			    onImageUpload: function(files) {
					//上传图片到服务器，使用了formData对象，至于兼容性...据说对低版本IE不太友好
					var formData = new FormData();
					formData.append('file',files[0]);
					$.ajax({
						url : '${ctxStatic}/image/uploadImage',//后台文件上传接口
						type : 'POST',
						data : formData,
						processData : false,
						contentType : false,
						success : function(data) {
							var imageurl = "${ctxStatic}/image/readImage?name="+data.fileName;
							$('.summernote').summernote('insertImage',imageurl,'img');
						},
				        error: function (data, status, e) { 
		               		alert(123); 
		                }
				    });
		    	}
		  	}
		});
	});

	//文件上传
	$('.form-single-file').on('change', 'input[type=file]', function(){
		var fileid = $(this).attr('id');
		//校验
		var s =$(this).val();
		var f1 = s.split(".");
		//常用的文件格式
		//var arg=["doc","docx","xls","xlsx","ppt","gif","jpeg","png","txt","wps","pdf"];
		var arg=["doc","docx","xls","xlsx","ppt","txt","pdf","zip","rar","7z"];
		var t = $.inArray(f1[1],arg);
		console.log(t);	
		//不是以上的格式
		if(t==-1){
			$('#fileinfo').css('display','block');
			$('#fileinfo').html('<font color="red">选择文件失败，请选择doc,docx,xls,xlsx,ppt,txt,pdf,zip,rar,7z文件</font>');
			$('#sbbutton').attr("disabled","true");
		}
		else{
			$.ajaxFileUpload({
				url:'${ctxStatic}/file/uploadFile',
				secureuri:false,
				dataType:'text',
				fileElementId:fileid,//file标签的id
				success: function (data, status) {
					var jsonobj=eval('('+data+')');
					var fileName = jsonobj.fileName;
					var oldFileName = jsonobj.oldFileName;
					//提示上传成功
					$('#fileinfo').css('display','block');
					$('#fileinfo').html('<font color="green">'+oldFileName+' 上传成功 选择文件重新上传</font>');
					$('#fileinfo1').css('display','none');
					//将后台保存的文件名保存到前端
					$('#attach').val(fileName);
					$('#attachName').val(oldFileName);
					$('#sbbutton').removeAttr("disabled");
				},
				error: function (data, status, e) {
					$('#fileinfo').html('<font color="red">失败，请选择小于20M的文件</font>');
					$('#fileinfo').css('display','block');
				}
			});
		}
	}); 
        
    //数据校验
	$().ready(function () {
		// 在键盘按下并释放及提交后验证提交表单
		$("#modifyform").validate({
			rules: {
				title: {
					maxlength: 30,
				},
			},
			submitHandler: function (form) {
			    var sHTML = $('.summernote').summernote('code');
				$.ajax(
				{
					type: "post",
			    	url: "${ctxStatic}/news/update",
			        data: {
			            id:$("#newsid").val(),
			        	title:$("#title").val(),
			        	newsTypeID:$("#newsType option:selected").val(),
			        	attach:$("#attach").val(),
						attachName:$("#attachName").val(),
			        	content:sHTML,
			            },
			        dataType: "json",
			        async : true,
			        success:function(data){
						alert(data.message);
	               		console.log(data);
			            window.location.href = "${ctxStatic}/news/index";
				        },
				    error:function(data){
					    alert("error");
					    }
				});
			}
		});
	});
</script>
</body>
</html>