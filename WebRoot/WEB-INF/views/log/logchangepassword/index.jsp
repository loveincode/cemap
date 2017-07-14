<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<title>INDEX</title>
   <%@include file="/WEB-INF/views/common/commoncss.jsp" %>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	
    	<!-- ************************* Search Form Start  ************************* -->
    	<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TEACHER_ADMIN">
    	<div class="row">
    		<div class="col-md-3">
				<div class="form-group">
					<label class="col-sm-3 control-label" style="padding-left:5px;padding-top:6px;">名称：</label>
					<div class="col-sm-9">
						<input id="sname" name="sname" class="form-control"
								type="text" aria-required="true" aria-invalid="true"
								class="error" value="" placeholder="模糊">
					</div>
				</div>
			</div>
			
		    <div class="col-md-3" style="padding-left:45px;">
            <button type="button"  id="query-btn" class="btn btn-success" ><i class="fa fa-search"></i> 搜索</button>
          	 </div>
       </div>
       </security:authorize>
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
										<th>用户名</th>
										<th>操作用户</th>
										<th>修改时间</th>
										<th>修改类型</th>
										<th>操作</th>
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
    
    var sname1;

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
	        	url:"${ctxStatic}/logchangepassword/data",
	    		headers: {
				        Accept: "application/json; charset=utf-8"
				    },
				type: "post",
			    data: function(request){   	
				    	if(sname1 !=null && sname1!="")
				    	{
				    		request.name = sname1;
				    	}
			    	return request;
			        }
	        },
	        "columns": [
	        	{ "data": "id","orderable":false},
	            { "data": "username_c","orderable":false},
	            { "data": "username_x","orderable":false},
	            { "data": "date","orderable":false},
	            { "data": "changeType","orderable":false},
	            { "data": "id","orderable":false}
	        ], 
	        "columnDefs": [
	            {
	            "defaultContent":"",
	            "targets":['_all']
	            },
	            {
	            "render": function(data, type, row) {
	                return "<a><i class=\"fa fa-search-plus text-navy\"></i>查看</a>";
	            },
	            "targets": 5}
	        ]
	    } );
	    
	    //综合搜索
	    $("#query-btn").on("click",function(){
			sname1 = $("#sname").val();
	    	table.api().ajax.reload();
	    });
  
	}); 
	

</script>

</body>

</html>