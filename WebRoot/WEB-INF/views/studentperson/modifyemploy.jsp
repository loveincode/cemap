<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>编辑</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
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
	                    	<input type="hidden" id="employmentInformationid" value="${employmentInformation.id}"/>
	                    	<input type="hidden" id="studentId" value="${employmentInformation.studentId}"/>
	                    	
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">就业方式：</label>
									<div class="col-sm-4">
										<select name="employmentMethod" id="employmentMethod" class="form-control required">
								        	<option>签就业协议书</option>
								        	<option>签合同</option>
								        	<option>升学</option>
								        	<option>出国</option>
								        	<option>自主创业</option>
								        	<option>参军</option>
								        </select>
									</div>
									<input type="hidden" id="employmentMethod2" value="${employmentInformation.employmentMethod}">
								</div>
							</div>
							
							<!--**************** 就业 Start ****************-->
							<div  style="display:block" id="type1">
							
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">用人单位名称（全称）：</label>
											<div class="col-sm-8">
												<input type="text" id="companyFullName" name="companyFullName" class="form-control required"
													placeholder="请输入全称" value="${employmentInformation.companyFullName}">
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">组织机构代码：</label>
											<div class="col-sm-8">
												<input type="text" id="organizationCode" name="organizationCode" class="form-control required isOrganizationCode"
													placeholder="请输入组织机构代码" value="${employmentInformation.organizationCode}">
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">单位性质：</label>
											<div class="col-sm-8">
												<select class="form-control required" id="nature" name="nature" >
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
											<input type="hidden" id="nature2" value="${employmentInformation.nature}">
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">单位联系人：</label>
											<div class="col-sm-8">
												<input type="text" id="companyContactName" name="companyContactName" class="form-control required"
													placeholder="请输入联系人" value="${employmentInformation.companyContactName}">
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">单位电话：</label>
											<div class="col-sm-8">
												<input type="text" id="companyContactPhone" name="companyContactPhone" class="form-control required isPhone"
													placeholder="请输入电话" value="${employmentInformation.companyContactPhone}">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">单位地址：</label>
											<div class="col-sm-8">
												<input type="text" id="companyPlace" name="companyPlace" class="form-control required"
													placeholder="请输入地址" value="${employmentInformation.companyPlace}">
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">单位省份：</label>
											<div class="col-sm-8">
												<select class="form-control required" id="companyProvince" name="companyProvince">
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
											<input type="hidden" id="companyProvince2" value="${employmentInformation.companyProvince}">
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">邮政编码：</label>
											<div class="col-sm-8">
												<input type="text" id="postcode" name="postcode" class="form-control required isPostCode"
													placeholder="请输入邮政编码" value="${employmentInformation.postcode}">
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">单位email：</label>
											<div class="col-sm-8">
												<input type="text" id="email" name="email" class="form-control required email"
													placeholder="请输入email" value="${employmentInformation.email}">
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">职位类别：</label>
											<div class="col-sm-8">
												<input type="text" id="occupationType" name="occupationType" class="form-control required"
													placeholder="请输入职位列别" value="${employmentInformation.occupationType}">
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">行业类别：</label>
											<div class="col-sm-8">
												<select class="form-control required" id="industryType" name="industryType">
													<option>金融业</option>
													<option>公共管理、社会保障和社会组织</option>
													<option>信息传输、软件和信息技术服务业</option>
													<option>教育</option>
													<option>科学研究和技术服务业</option>
													<option>文化、体育和娱乐业</option>
													<option>租赁和商务服务业</option>
													<option>制造业</option>
													<option>交通运输、仓储和邮政业</option>
													<option>房地产业</option>
													<option>电力、热力、燃气及水生产和供应业</option>
													<option>建筑业</option>
													<option>批发和零售业</option>
													<option>水利、环境和公共设施管理业</option>
													<option>军队</option>
													<option>卫生和社会工作</option>
													<option>采矿业</option>
													<option>农、林、牧、渔业</option>
													<option>居民服务、修理和其他服务业</option>
													<option>住宿和餐饮业</option>
												</select>
												<input type="hidden" id="industryType2" value="${employmentInformation.industryType}">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label">就业薪资：</label>
											<div class="col-sm-8">
												<input type="text" id="employmentPay" name="employmentPay" class="form-control required digits"
													placeholder="请输入就业薪资" value="${employmentInformation.employmentPay}">
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--**************** 就业 End ****************-->
							
							<!--**************** 升学 Start ****************-->
							<div  style="display:none" id="type2">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-sm-4 control-label">升学学校：</label>
										<div class="col-sm-4">
											<input type="text" id="university" name="university" class="form-control required"
												placeholder="请输入升学学校" value="${employmentInformation.university}">
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-sm-4 control-label">升学专业：</label>
										<div class="col-sm-4">
											<input type="text" id="major" name="major" class="form-control required"
												placeholder="请输入升学专业" value="${employmentInformation.major}">
										</div>
									</div>
								</div>
							</div>
							<!--**************** 升学 End ****************-->
							
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
		$(window).load(function() {
		});
	</script>
	<script type="text/javascript">
	
		window.onload = afterload;
        function afterload(){
        	var tmp = $("#employmentMethod2").val();
			$("#employmentMethod").val(tmp);
			if(tmp=="签就业协议书"||tmp=="签合同"){
				$('#type1').css('display','block'); 
				$('#type2').css('display','none'); 
			}
			else{
				$('#type1').css('display','none'); 
			}
			if(tmp=="升学"){
				$('#type2').css('display','block'); 
				$('#type1').css('display','none'); 
			}
			else{
				$('#type2').css('display','none'); 
			}
			if(tmp==""){
				$('#type1').css('display','block'); 
			}
			var tmp1 = $("#nature2").val();
			$("#nature").val(tmp1);
			var tmp2 = $("#companyProvince2").val();
			$("#companyProvince").val(tmp2);
			var tmp3 = $("#industryType2").val();
			$("#industryType").val(tmp3);
        }
		
		$('#employmentMethod').change(function(){ 
			if($(this).children('option:selected').html()=="签就业协议书"||$(this).children('option:selected').html()=="签合同"){
				$('#type1').css('display','block'); 
				$('#type2').css('display','none'); 
			}
			else{
				$('#type1').css('display','none'); 
			}
			if($(this).children('option:selected').html()=="升学"){
				$('#type2').css('display','block'); 
				$('#type1').css('display','none'); 
			}
			else{
				$('#type2').css('display','none'); 
			}
		});
		
		//数据校验
		$().ready(function () {
			// 在键盘按下并释放及提交后验证提交表单
			$("#modifyform").validate({
				rules: {
					companyFullName:{
						maxlength:20,
					},
					university:{
						maxlength:20,
					},
					major:{
						maxlength:10,
					},
				},
				submitHandler: function (form) {
					$.ajax({
					type : "post",
					url : "saveorupdateEmploy",
					data : {
						id : $("#employmentInformationid").val(),
						studentId : $("#studentId").val(),
						employmentMethod:$("#employmentMethod").val(),
						companyFullName : $("#companyFullName").val(),
						organizationCode : $("#organizationCode").val(),
						nature : $("#nature").val(),
						companyContactName : $("#companyContactName").val(),
						companyContactPhone : $("#companyContactPhone").val(),
						postcode : $("#postcode").val(),
						email : $("#email").val(),
						occupationType : $("#occupationType").val(),
						companyPlace : $("#companyPlace").val(),
						companyProvince : $("#companyProvince").val(),
						industryType : $("#industryType").val(),
						employmentPay : $("#employmentPay").val(),
						university : $("#university").val(),
						major : $("#major").val(),
					},
					dataType : "json",
					async : true,
					success : function(data) {
						alert(data.message);
	               		console.log(data);
						window.location.href = "../studentperson/saveorupdateEmployPage";
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