<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
<title>excel导入</title>
<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
         <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>excel导入</h5>
				</div>
				
				<div class="ibox-content">
              		<div class="row form-body form-horizontal m-t">
                       	
                       		
						<!-- ********* Form start  *********  -->
		                <form class="form-horizontal m-t" id="addform">
							
							<div class="col-md-12">
								<div class="form-group">	
									<h3 class="col-sm-4 control-label">Excel导入：</h3>
									<div class="col-sm-4">
										<div class="form-single-file">
											<div style="display:none" id="fileinfo"></div>
											<input type="file" id="file1" name="file1">
											<span>(文件大小:小于20M,文件格式:xls，xlsx)</span>
											<br/><br/>
											<input type="hidden" name="attach" id="attach" value="">
											<input type="hidden"  class="form-control" name="attachName" id="attachName" value="">
											<button class="btn btn-primary" id="upfile" onClick="user_submit();" type="button" disabled="disabled">
											<i class="fa fa-check"> </i>开始导入</button>
										</div>
									</div>
								</div>
							</div>
							
							<br/>
							<br/>
							
								<center id="errornewscenter" style="display:none;">
									<h4>导入日志：</h4>
									<p id="errornews" style="height:200px;overflow-y:auto;">
									</p>
									<button class="btn btn-primary"  onClick="reload()" type="button">确定,重新选择文件导入</button>
								</center>
							
							<div class="col-md-12">
								<div class="form-group">	
									<h3 class="col-sm-4 control-label">Excel模板下载：</h3>
									<div class="col-sm-4">
									<a href="${ctxStatic}/file/downloadFile?fileName=graduate_info.xls&oldFileName=graduate_info.xls">
									<button class="btn btn-info dim btn-large-dim" type="button">下</button>
									</a>
									</div>
							</div>
								
							
								<h3>PS:</h3>
								<font color="red">
									1.请参考模板的数据填写<br/>
									2.填写好之后点击导入<br/>
									3.一次最多3000条数据<br/>
								</font>
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
	
	
	<script type="text/javascript">
	
		 //文件上传
		$('.form-single-file').on('change', 'input[type=file]', function(){
			var fileid = $(this).attr('id');
			//校验
			var s =$(this).val();
			var f1 = s.split(".");
			//常用的文件格式
			//var arg=["doc","docx","xls","xlsx","ppt","gif","jpeg","png","txt","wps","pdf"];
			var arg=["xls","xlsx"];
			var t = $.inArray(f1[1],arg);
			console.log(t);	
			//不是以上的格式
			if(t==-1){
				$('#fileinfo').css('display','block');
				$('#fileinfo').html('<font color="red">选择文件失败，请选择xls，xlsx文件</font>');
				$('#upfile').attr("disabled","true");
			}
			else{
				$.ajaxFileUpload({
					url:'../file/uploadFile',
					secureuri:false,
					dataType:'text',
					fileElementId:fileid,//file标签的id
					success: function (data, status) {
							var jsonobj=eval('('+data+')');
							console.log(jsonobj);
							if(jsonobj.error==0){
								var fileName = jsonobj.fileName;
								var oldFileName = jsonobj.oldFileName;
								//提示上传成功
								$('#fileinfo').css('display','block');
								$('#fileinfo').html('<font color="green">'+oldFileName+' 上传成功 点击按钮开始导入</font>');
								
								//将后台保存的文件名保存到前端
								$('#attach').val(fileName);
								$('#attachName').val(oldFileName);
								$('#upfile').removeAttr("disabled");
							} 
					},
					error: function (data, status, e) {
						$('#fileinfo').html('<font color="red">失败，请选择小于20M的文件</font>');
						$('#fileinfo').css('display','block');
					}
				});
			}
		}); 
		
	
		function user_submit()
		{
			$.ajax({
				type : "post",
				url : "${ctxStatic}/excel/saveData",
				data : {
					attach:$("#attach").val(),
				},
				dataType : "json",
				async : true,
				success : function(data) {
					alert(data.message);
					if(data.success){
		              	console.log(data);
		              	console.log(data.data);
		              	$('#errornews').html(data.data);
		              	$('#errornewscenter').css('display','block');
		              	$('#upfile').attr("disabled","true");
						//window.location.href = "${ctxStatic}/excel/index";
					}
				},
				error : function(data) {
					alert("error");
				}
			});	
		}
		
		function reload(){
			window.location.href = "${ctxStatic}/excel/index";
		}

	</script>
</body>
</html>