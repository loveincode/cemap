<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<title>INDEX</title>
   <%@include file="/WEB-INF/views/common/commoncss.jsp" %>
</head>
<style>
th{
	text-align:center
}
.dataTable{
	text-align:center
}
</style>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	
    	<!-- ************************* Search Form Start  ************************* -->
    	<div class="row">
    		
    		<security:authorize ifAnyGranted='ROLE_ADMIN,ROLE_TEACHER_ADMIN'>
    		<div class="col-md-3">
				<div class="form-group">
						<label class="col-sm-3 control-label" style="padding-left:5px;padding-top:6px;">学院：</label>
						<div class="col-sm-9">
							<select name="scollegeId" id="scollegeId" class="form-control">
								<option value="-1">全部</option>
		        			 	<c:forEach items="${colleges}" var="college">
		        				<option value="${college.id}">${college.name}</option>
		        				</c:forEach>  	 
	      					</select>
						</div>
					</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group">
						<label class="col-sm-3 control-label" style="padding-left:5px;padding-top:6px;">专业：</label>
						<div class="col-sm-9">
						<select name="sprofessionId" id="sprofessionId" class="form-control">
							<option value="-1">全部</option>
	     					</select>
					</div>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group">
						<label class="col-sm-3 control-label" style="padding-left:5px;padding-top:6px;">班级：</label>
						<div class="col-sm-9">
						<select name="sadministrativeClassId" id="sadministrativeClassId" class="form-control">
							<option value="-1">全部</option>
	     					</select>
					</div>
				</div>
			</div>
			</security:authorize>
			
			<security:authorize ifAnyGranted='ROLE_TEACHER'>
			<div class="col-md-3">
				<div class="form-group">
						<label class="col-sm-4 control-label" style="padding-left:5px;padding-top:6px;">负责班级：</label>
						<div class="col-sm-8">
						<select name="f_sadministrativeClassId" id="f_sadministrativeClassId" class="form-control">
							<option value="-1">全部</option>
							<c:forEach items="${administrativeClasss}" var="administrativeClass">
		        				<option value="${administrativeClass.id}">${administrativeClass.name}</option>
		        			</c:forEach>
	     					</select>
					</div>
				</div>
			</div>
			</security:authorize>
			
			<div class="col-md-3" style="padding-left:45px;">
            <button type="button"  id="query-btn" class="btn btn-success" ><i class="fa fa-search"></i> 搜索</button>
          	 </div>
			
			<br/>
			<br/>

		    
       </div>
       <!-- *************************   Search Form End  ************************* -->


	   <!-- *************************     Table Start    ************************* -->
       <div class="row">
           <div class="col-sm-12">
               <div class="ibox float-e-margins">
						</br>
						<div class="mt-20">
							<table id="data-Table" class="table table-border table-bordered table-bg table-hover table-sort">
								<thead>
									<tr class="text-c">
										<th>id</th>
										<th>学号</th>
										<th>姓名</th>
										<th>手机号</th>
										<th>班级</th>
										<th>学历</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
             </div>
       </div>
       <!-- *************************     Table End    ************************* -->
   </div>
   
    <!-- 全局js -->
    <%@include file="/WEB-INF/views/common/commonjs.jsp" %>
    
    <script src="${ctxStatic}/user/js/plugins/footable/footable.all.min.js"></script>
	<script src="${ctxStatic}/js/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script src="${ctxStatic}/user/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${ctxStatic}/user/js/demo/layer-demo.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctxStatic}/user/js/content.min.js?v=1.0.0"></script>
    <script type="text/javascript">
    
    //下面是写三级联动的代码
	$('#scollegeId').change(function(){ 
		var collegename = $(this).children('option:selected').html();
		var collegeid = $(this).children('option:selected').val();
		getprofession(collegeid);
	});
	
	$('#sprofessionId').change(function(){ 
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
	    		$('#sprofessionId option').remove();
	    		$('#sadministrativeClassId option').remove();
	    		$('#sprofessionId').append("<option value='-1'>全部</option>");
	    		$('#sadministrativeClassId').append("<option value='-1'>全部</option>");
	    		if(array.length>0){
	    			getadministrativeclass(array[0].id)
		     		for(var o in array){
		     			$('#sprofessionId').append("<option value="+array[o].id+">"+array[o].name+"</option>");
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
	    		$('#sadministrativeClassId option').remove();
	    		$('#sadministrativeClassId').append("<option value='-1'>全部</option>");
	    		if(array.length>0){
		     		for(var o in array){
		     			$('#sadministrativeClassId').append("<option value="+array[o].id+">"+array[o].name+"</option>");
		     		}
	    		}
		    }
		})		
	}
    
    $('#addBtn').click(function () {
        window.location.href = "${ctxStatic}/graduateinformation/addPage";
    });
    
    var scollegeId1;
    var sprofessionId1;
    var sadministrativeClassId1;
    var f_sadministrativeClassId1;
    var sname1;
    var sstudentId1;
    
	$(document).ready(function(){
		var table = $('#data-Table').dataTable( {
		    "lengthChange":true, //是否允许用户自定义显示数量
		    "bPaginate":true, //翻页功能
		    "info": true, //页脚信息
			"pagingType": "full_numbers",
			"searching":false, //本地搜索
			"ordering":true, //排序功能
			"autoWidth":true, //自动宽度
			//是否开启不限制长度的滚动条
			"processing": false,
			"serverSide": true,
	        "ajax": {
	        	url:"${ctxStatic}/graduateinformation/notemploydata",
	    		headers: {
				        Accept: "application/json; charset=utf-8"
				    },
				type: "post",
			    data: function(request){ 
				    	if(scollegeId1 !=null && scollegeId1!="")
				    	{
				    		request.scollegeId = scollegeId1;
				    	}
				    	if(sprofessionId1 !=null && sprofessionId1!="")
				    	{
				    		request.sprofessionId = sprofessionId1;
				    	}
				    	if(sadministrativeClassId1 !=null && sadministrativeClassId1!="")
				    	{
				    		request.sadministrativeClassId = sadministrativeClassId1;
				    	}
				    	if(f_sadministrativeClassId1 !=null && f_sadministrativeClassId1!="")
				    	{
				    		request.f_sadministrativeClassId = f_sadministrativeClassId1;
				    	}
			    	return request;
			        }
	        },
	        "columns": [
	        	{ "data": "id","orderable":false},
	            { "data": "studentId","orderable":false},
	            { "data": "name","orderable":false},
	            { "data": "phone","orderable":false},
	            { "data": "administrativeClassName","orderable":false},
	            { "data": "educationBackground","orderable":false},
	        ], 
	        "columnDefs": [
	            {
	            "defaultContent":"",
	            "targets":['_all']
	            },
	        ]
	    } );
	    
	    //综合搜索
	    $("#query-btn").on("click",function(){
			scollegeId1 = $("#scollegeId").val();
			sprofessionId1 = $("#sprofessionId").val();
			sadministrativeClassId1 = $("#sadministrativeClassId").val();
			f_sadministrativeClassId1 = $("#f_sadministrativeClassId").val();
			sname1 = $("#sname").val();
			sstudentId1 = $("#sstudentId").val();
	    	table.api().ajax.reload();
	    });
	    
	}); 
	
	function deletefunc(data){
	    var result = confirm("确定要删除吗？");
       	if(result){
           $.ajax
           ({ 
               url: "delete?uuid="+data,
               method: 'GET',
               dataType: "json", 
               data: { 
               },
               success: function (data) {
               		alert(data.message);
               		console.log(data);
                    window.location.reload();
               }
           })
       	}
	}

</script>

</body>

</html>