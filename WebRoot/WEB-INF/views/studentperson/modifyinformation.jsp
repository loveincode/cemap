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
	                    	<input type="hidden" id="graduateInformationid" value="${graduateInformation.id}"/>
	                    	<%-- <input type="hidden" id="graduateInformationuuid" value="${graduateInformation.Uuid}"/> --%>
	                    	
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-3 control-label">毕业届数：</label>
									<div class="col-sm-7">
										<input type="text" id="graduationSession" name="graduationSession" class="form-control required digits"
											placeholder="请输入毕业届数" value="${graduateInformation.graduationSession}" disabled="disabled">
									</div>
								</div>
							</div>
	                    	
	                    	<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">学号：</label>
									<div class="col-sm-7">
										<input type="text" id="studentId" name="studentId" class="form-control required digits"
											placeholder="请输入学号" value="${graduateInformation.studentId}" disabled="disabled">
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名：</label>
									<div class="col-sm-7">
										<input type="text" id="name" name="name" class="form-control required"
											placeholder="请输入姓名" value="${graduateInformation.name}" disabled="disabled">
									</div>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">性别：</label>
									<div class="col-sm-7">
										<select class="form-control required" id="sex" name="sex" disabled="disabled">
											<option value ="男">男</option>
											<option value ="女">女</option>
										</select>
									</div>
									<input type="hidden" id="sex2" value="${graduateInformation.sex}">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group" id="data_1">
									<label class="col-sm-3 control-label">出生日期：</label>
									<div class="col-sm-7">
										<div class="input-group date">
										<span class="input-group-addon"><i
												class="fa fa-calendar"></i></span> 
												<input type="text" name="birthday" id="birthday"
												class="form-control required dateISO" value="${graduateInformation.birthday}" readonly="readonly" disabled="disabled">
										</div>
									</div>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-3 control-label">学院：</label>
									<div class="col-sm-7">
										<select name="collegeId" id="collegeId" class="form-control required" disabled="disabled">
					        			 	<%-- <c:forEach items="${colleges}" var="college"> --%>
					        				<option value="${college.id}">${college.name}</option>
					        				<%-- </c:forEach> --%>  	 
				      					</select>
									</div>
									<input type="hidden" id="collegeId2" value="${graduateInformation.collegeId}">
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-3 control-label">专业：</label>
									<div class="col-sm-7">
										<select name="professionId" id="professionId" class="form-control required" disabled="disabled">
					        			 	<%-- <c:forEach items="${professions}" var="profession"> --%>
					        					<option value="${profession.id}">${profession.name}</option>
					        				<%-- </c:forEach> --%>   
				      					</select>
									</div>
									<input type="hidden" id="professionId2" value="${graduateInformation.professionId}">
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-3 control-label">班级：</label>
									<div class="col-sm-7">
										<select name="administrativeClassId" id="administrativeClassId" class="form-control required" disabled="disabled">
					        			 	<%-- <c:forEach items="${administrativeClasss}" var="administrativeClass"> --%>
					        					<option value="${administrativeClass.id}">${administrativeClass.name}</option>
					        				<%-- </c:forEach> --%>   
				      					</select>
									</div>
									<input type="hidden" id="administrativeClassId2" value="${graduateInformation.administrativeClassId}">
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">政治面貌：</label>
									<div class="col-sm-7">
											<select class="form-control required" id="politicalStatus" name="politicalStatus" disabled="disabled">
												<option value="共青团员">共青团员</option>
												<option value="群众">群众</option>
												<option value="中共党员">中共党员</option>
											</select>
									</div>
									<input type="hidden" id="politicalStatus2" value="${graduateInformation.politicalStatus}">
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯：</label>
									<div class="col-sm-7">
										<input type="text" id="nativePlace" name="nativePlace" class="form-control required"
											placeholder="籍贯 省 市"  value="${graduateInformation.nativePlace}" disabled="disabled">
									</div>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">民族：</label>
									<div class="col-sm-7">
											<select class="form-control required" id="nation" name="nation" disabled="disabled">
											<!-- <select id="nation" name="nation" class="chosen-select form-control" > -->
												<option>汉族</option>  
											    <option>蒙古族</option>  
											    <option>回族</option>  
											    <option>藏族</option>  
											    <option>维吾尔族</option>  
											    <option>苗族</option>  
											    <option>彝族</option>  
											    <option>壮族</option>  
											    <option>布依族</option>  
											    <option>朝鲜族</option>  
											    <option>满族</option>  
											    <option>侗族</option>  
											    <option>瑶族</option>  
											    <option>白族</option>  
											    <option>土家族</option>  
											    <option>哈尼族</option>  
											    <option>哈萨克族</option>  
											    <option>傣族</option>  
											    <option>黎族</option>  
											    <option>傈僳族</option>
											    <option>佤族</option>    
											    <option>畲族</option>  
											    <option>高山族</option>  
											    <option>拉祜族</option>  
											    <option>水族</option>  
											    <option>东乡族</option>  
											    <option>纳西族</option>  
											    <option>景颇族</option>  
											    <option>柯尔克孜族</option>  
											    <option>土族</option>  
											    <option>达斡尔族</option>  
											    <option>仫佬族</option>  
											    <option>羌族</option>  
											    <option>布朗族</option>  
											    <option>撒拉族</option>  
											    <option>毛南族</option>  
											    <option>仡佬族</option>  
											    <option>锡伯族</option>  
											    <option>阿昌族</option>  
											    <option>普米族</option>  
											    <option>塔吉克族</option>  
											    <option>怒族</option>  
											    <option>乌孜别克族</option>  
											    <option>俄罗斯族</option>  
											    <option>鄂温克族</option>  
											    <option>德昂族</option>  
											    <option>保安族</option>  
											    <option>裕固族</option>  
											    <option>京族</option>  
											    <option>塔塔尔族</option>  
											    <option>独龙族</option>  
											    <option>鄂伦春族</option>  
											    <option>赫哲族</option>  
											    <option>门巴族</option>  
											    <option>珞巴族</option>  
											    <option>基诺族</option>
											</select>
									</div>
									<input type="hidden" id="nation2" value="${graduateInformation.nation}">
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">身份证号码：</label>
									<div class="col-sm-7">
										<input type="text"  id="identificationNumber" name="identificationNumber" class="form-control required isIdCardNo"
											placeholder="请输入身份证号码"  value="${graduateInformation.identificationNumber}" disabled="disabled">
									</div>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">户口性质：</label>
									<div class="col-sm-7">
										<select class="form-control required" id="accountproperty" name="accountproperty" disabled="disabled">
												<option>农村</option>
												<option>城市</option>
											</select>
									</div>
									<input type="hidden" id="accountproperty2" value="${graduateInformation.accountproperty}">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">户口所在地：</label>
									<div class="col-sm-7">
										<input type="text" id="accountLocation" name="accountLocation" class="form-control required"
											placeholder="请输入户口所在地"  value="${graduateInformation.accountLocation}" >
									</div>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">生源地：</label>
									<div class="col-sm-7">
										<select class="form-control required" id="originPlace" name="originPlace" value="${graduateInformation.originPlace}" disabled="disabled">
												<option>上海</option>
												<option>北京</option>
												<option>天津</option>
												<option>重庆</option>
												<option>河北</option>
												<option>山西</option>
												<option>吉林</option>
												<option>辽宁</option>
												<option>黑龙江</option>
												<option>陕西</option>
												<option>甘肃</option>
												<option>青海</option>
												<option>山东</option>
												<option>福建</option>
												<option>浙江</option>
												<option>河南</option>
												<option>湖北</option>
												<option>湖南</option>
												<option>江西</option>
												<option>江苏</option>
												<option>安徽</option>
												<option>广东</option>
												<option>海南</option>
												<option>四川</option>
												<option>贵州</option>
												<option>云南</option>
												<option>内蒙古</option>
												<option>新疆</option>
												<option>宁夏</option>
												<option>广西</option>
												<option>西藏</option>
											</select>
									</div>
									<input type="hidden" id="originPlace2" value="${graduateInformation.originPlace}">
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">学历：</label>
									<div class="col-sm-7">
										<select class="form-control required" id="educationBackground" name="educationBackground" disabled="disabled">
												<option>专科</option>
												<option>本科</option>
												<option>硕士</option>
												<option>博士</option>
												<option>博士后</option>
											</select>
									</div>
									<input type="hidden" id="educationBackground2" value="${graduateInformation.educationBackground}">
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">手机号码：</label>
									<div class="col-sm-7">
										<input type="text" id="phone" name="phone" class="form-control required isPhone"
											placeholder="请输入手机号码"  value="${graduateInformation.phone}" >
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">电子邮箱：</label>
									<div class="col-sm-7">
										<input type="text" id="email" name="email" class="form-control required email"
											placeholder="请输入电子邮箱"  value="${graduateInformation.email}" >
									</div>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">家庭地址：</label>
									<div class="col-sm-7">
										<input type="text" id="homeAddress" name="homeAddress" class="form-control required"
											placeholder="请输入家庭地址"  value="${graduateInformation.homeAddress}" >
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-3 control-label">家庭联系电话：</label>
									<div class="col-sm-7">
										<input type="text" id="homePhone" name="homePhone" class="form-control required isPhone"
											placeholder="请输入家庭联系方式"  value="${graduateInformation.homePhone}" >
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
	
	<!-- DataPicker -->
    <script src="${ctxStatic}/user/js/plugins/datapicker/bootstrap-datepicker.js"></script>

	<!-- jQuery Validation plugin javascript-->
	<script src="${ctxStatic}/user/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctxStatic}/user/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctxStatic}/js/ajaxfileupload.js"></script>
	<script>
		$(window).load(function() {
		});
	</script>
	<script type="text/javascript">
		$("#data_1 .input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,calendarWeeks:!0,autoclose:!0})
		
		window.onload = afterload;
        function afterload(){
			//基础信息 学院专业班级
        	var tmp = $("#collegeId2").val();
          	$("#collegeId").val(tmp);
        	var tmp1 = $("#professionId2").val();
          	$("#professionId").val(tmp1);
          	var tmp2 = $("#administrativeClassId2").val();
          	$("#administrativeClassId").val(tmp2);
          	//政治面貌
          	var tmp3 = $("#politicalStatus2").val();
          	$("#politicalStatus").val(tmp3);
          	//民族
          	var tmp4 = $("#nation2").val();
          	$("#nation").val(tmp4);
			//学历
          	var tmp5 = $("#educationBackground2").val();
          	$("#educationBackground").val(tmp5);
          	//生源地
          	var tmp6 = $("#originPlace2").val();
          	$("#originPlace").val(tmp6);
          	//户口性质
          	var tmp7 = $("#accountproperty2").val();
          	$("#accountproperty").val(tmp7);
          	//性别
          	var tmp7 = $("#sex2").val();
          	$("#sex").val(tmp7);
        }
		
		//下面是写三级联动的代码
		$('#collegeId').change(function(){ 
			var collegename = $(this).children('option:selected').html();
			var collegeid = $(this).children('option:selected').val();
			getprofession(collegeid);
		});
		
		$('#professionId').change(function(){ 
			var professionname = $(this).children('option:selected').html();
			var professionid = $(this).children('option:selected').val();
			getadministrativeclass(professionid);
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
			    		console.log(array.length);
			    		$('#professionId option').remove();
			    		$('#administrativeClassId option').remove();
			    		if(array.length>0){
			    			getadministrativeclass(array[0].id)
			     		for(var o in array){
			     			console.log(array[o].id);
			     			console.log(array[o].name);
			     			$('#professionId').append("<option value="+array[o].id+">"+array[o].name+"</option>");
			     		}
			    		}
			    }
			})		
		}
		
		function getadministrativeclass(professionId){
			$.ajax
			({ 
			    url: "${ctxStatic}/administrativeclass/getadministrativeclass?professionId="+professionId,
			    method: 'GET',
			    dataType: "json", 
			    data: { 
			    },
			    success: function (data) {
			    		var array = data.data; 
			    		console.log(array.length);
			    		$('#administrativeClassId option').remove();
			    		if(array.length>0){
			     		for(var o in array){
			     			console.log(array[o].id);
			     			console.log(array[o].name);
			     			$('#administrativeClassId').append("<option value="+array[o].id+">"+array[o].name+"</option>");
			     		}
			    		}
			    }
			})		
		}
		
		
		//数据校验
		$().ready(function () {
			// 在键盘按下并释放及提交后验证提交表单
			$("#modifyform").validate({
				rules: {
					studentId:{
						maxlength:15,
					},
					name:{
						maxlength:15,
					}
				},
				submitHandler: function (form) {
					$.ajax({
					type : "post",
					url : "${ctxStatic}/graduateinformation/update",
					data : {
						id : $("#graduateInformationid").val(),
						studentId : $("#studentId").val(),
						name : $("#name").val(),
						sex : $("#sex").val(),
						birthday : $("#birthday").val(),
						collegeId : $("#collegeId").val(),
						professionId : $("#professionId").val(),
						administrativeClassId : $("#administrativeClassId").val(),
						politicalStatus : $("#politicalStatus").val(),
						nation : $("#nation").val(),
						nativePlace : $("#nativePlace").val(),
						identificationNumber : $("#identificationNumber").val(),
						accountLocation : $("#accountLocation").val(),
						accountproperty : $("#accountproperty").val(),
						originPlace : $("#originPlace").val(),
						educationBackground : $("#educationBackground").val(),
						phone : $("#phone").val(),
						email : $("#email").val(),
						homeAddress : $("#homeAddress").val(),
						homePhone : $("#homePhone").val(),
					},
					dataType : "json",
					async : true,
					success : function(data) {
						alert(data.message);
	               		console.log(data);
						window.location.href = "${ctxStatic}/studentperson/saveorupdateInformationPage";
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