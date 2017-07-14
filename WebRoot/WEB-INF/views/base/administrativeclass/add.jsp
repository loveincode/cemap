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
									<label class="col-sm-4 control-label">所属学院：</label>
									<div class="col-sm-4">
										<select name="collegeId" id="collegeId" class="form-control required">
				        			 	<c:forEach items="${colleges}" var="college">
				        				<option value="${college.id}">${college.name}</option>
				        				</c:forEach>   
				      					</select>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">所属专业：</label>
									<div class="col-sm-4">
										<select name="professionId" id="professionId" class="form-control required">
				        			 	<c:forEach items="${professions}" var="profession">
				        					<option value="${profession.id}">${profession.name}</option>
				        				</c:forEach>   
				      					</select>
									</div>
								</div>
							</div>
	                    	
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">班级code：</label>
									<div class="col-sm-4">
										<input type="text" id="code" name="code" class="form-control required"
											placeholder="请输入班级code">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">班级名称：</label>
									<div class="col-sm-4">
										<input type="text" id="name" name="name" class="form-control required"
											placeholder="请输入班级名称">
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
		window.onload = afterload;
        function afterload(){
        }
		//下面是写二级联动的代码
		$('#collegeId').change(function(){ 
			var collegename = $(this).children('option:selected').html();
			var collegeid = $(this).children('option:selected').val();
			getprofession(collegeid);
		});
		
		function getprofession(collegeId){
			$.ajax
			({ 
			    url: "${ctxStatic}/administrativeclass/getprofession?collegeId="+collegeId,
			    method: 'GET',
			    dataType: "json", 
			    data: { 
			    },
			    success: function (data) {
		    		var array = data.data; 
		    		$('#professionId option').remove();
		    		if(array.length>0){
		     		for(var o in array){
		     			$('#professionId').append("<option value="+array[o].id+">"+array[o].name+"</option>");
		     		}
		    		}
			    }
			})		
		}
	
	</script>
	
	
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
					maxlength: 15,
				}
			},
			submitHandler: function (form) {
			 $.ajax({
				type : "post",
				contentType:"application/x-www-form-urlencoded; charset=utf-8",
				url : "${ctxStatic}/administrativeclass/add",
				data : {
					name : $("#name").val(),
					code : $("#code").val(),
					collegeId : $("#collegeId").val(),
					professionId : $("#professionId").val(),
				},
				dataType : "json",
				async : true,
				success : function(data) {
					alert(data.message);
               		console.log(data);
					window.location.href = "${ctxStatic}/administrativeclass/index";
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