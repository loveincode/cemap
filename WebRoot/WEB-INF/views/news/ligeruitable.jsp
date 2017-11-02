<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<title>INDEX</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
   
	<link href="${ctxStatic}/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css">
	
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	
       <!-- ************************* Search Form Start  ************************* -->
    	<form id="search_form">
	    	<div id="searchbar">
	    		标题：<input id="stitle" type="text" placeholder="模糊">
	    		
	    		类型：<select name="snewsType" id="snewsType" >
	            	<option value="-1">全部</option>
	            	<c:forEach items="${newsTypes}" var="newsType">
						<option value="${newsType.id}">${newsType.name}</option>
					</c:forEach>
	            </select>
	              
	    		<input id="stitle" type="button" value="搜索" onclick="f_search()">
			</div>
		</form>
       <!-- *************************   Search Form End  ************************* -->

	   <!-- *************************     Table Start    ************************* -->
 		<a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a>
 
  
 		<div class="l-clear"></div>
 
    		<div id="maingrid"></div>
    
  		<div style="display:none;">
   
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
    
    
    <!-- ligerUI -->
    
    <script src="${ctxStatic}/ligerUI/js/core/base.js"></script>
    <script src="${ctxStatic}/ligerUI/js/plugins/ligerGrid.js"></script>
    <script src="${ctxStatic}/ligerUI/js/ligerui.all.js"></script>
    
    <script type="text/javascript">

    var newsTitle1;
    var newsTypeID1;
    
    $(function(){
		
	    grid = $("#maingrid").ligerGrid(
			{
				columns : [ {
					display : 'id',
					name : 'id',
					//width : 200,
					type: 'int',
					align: 'center'
				}, 
				{
					display : '标题',
					name : 'title',
					//width : 200,
					align: 'center'
				},
				{
					display : '类型',
					name:'newsType.name',
					//width: 200,
					align: 'center'
				},
				{
					display : '发布日期',
					name:'publishDate',
					//width: 200,
					align: 'center'
				},
				{
					display : '访问次数',
					name:'newsClick',
					//width: 200,
					align: 'center'
				},
				],
				dataAction : "server",
				url : "${ctxStatic}/news/dataui",
				//height : "98%",
				pageSize : 10,
				//width : "100%",
				rownumbers : true,
				checkbox : true,
				//autoCheckChildren: false,
				onReload:null,
				
				/* parms: [
					{ name: "newsTitle",value: newsTitle1},
					{ name: "newsTypeID",value: newsTypeID1},
				],  */
				
				//groupColumnName:'newsType.name',
				//groupColumnDisplay:'类型',
				toolbar: {
	                items: [
	                { text: '增加', click: add, icon: 'add' },
	                { line: true },
	                { text: '修改', click: updaterow, icon: 'modify' },
	                { line: true },
	                { text: '删除', click: deleterow, img: '${ctxStatic}/ligerUI/skins/icons/delete.gif' },
	                { line: true },
	                { text: '多选测试', click: readselecteds, }
	                
	                ]
	            },
		});
    
    });
    
    
    function f_search(){
    	newsTitle1 = $("#stitle").val();
		newsTypeID1 = $("#snewsType option:selected").val();
    	
    	grid.options.parms = [{ name: "newsTitle",value: newsTitle1},
				{ name: "newsTypeID",value: newsTypeID1}];
		grid.reload();		
    	
    }

    function itemclick(item)
    {
        alert(item.text);
    }
    
    function add(){
    	window.location.href = "${ctxStatic}/news/addPage";
    }
    
    function updaterow(){
    	
    	var rowdata = grid.getSelectedRow();
    	var rowdatas = grid.getSelectedRows();
    	if(rowdatas.length!=1){
    		alert("请选择一行修改");
    	}
    	else{
    		console.log(rowdata.Uuid);
    		window.location.href = "${ctxStatic}/news/modifyPage?uuid="+rowdata.Uuid
    	}
    	
    }
    
    function deleterow(){
	    var rowdata = grid.getSelectedRow();
	    var rowdatas = grid.getSelectedRows()
	    
	    if (!rowdata)
	        alert('请选择删除的咨询');
	    else{
	    	console.log(rowdatas);
	    	console.log(rowdatas.length);
	    	var result = confirm("确定要删除吗？");
	       	if(result){
		    	for(var i = 0;i<rowdatas.length;i++){
		    		console.log(rowdatas[i].Uuid);	
		    		//删除这些id
		    		$.ajax
		            ({ 
		               url: "delete?uuid="+rowdatas[i].Uuid,
		               method: 'GET',
		               dataType: "json", 
		               data: { 
		               },
		               success: function (data) {
		               		console.log(data);
		                    
		               }
		            })
		    	}
		    	window.location.reload();
	       	}
	       	
	    }
    }
    function readselecteds(){
    	
    	var rowdatas = grid.getSelectedRows()
    	
    	var rowdatassorted = grid.getSelectedRows("12");
    	
    	var len = rowdatas.length;
    	var len2 = rowdatassorted.length;
    	rowdatas = secondsort(rowdatas);
    	/*))
    	
    	for (var i = 0; i < len; i++) {
    	    for (var j = 0; j < len - 1 - i; j++) {
    	    	// 相邻元素两两对比，将大的换到后面 实现从小到大
    	        if (rowdatas[j].__index > rowdatas[j+1].__index) {
    	        	// 元素交换
    	            var temp = rowdatas[j+1];        
    	            rowdatas[j+1] = rowdatas[j];
    	            rowdatas[j] = temp;
    	        }
    	    }
    	}*/
    	console.log(rowdatas);
    	console.log(rowdatassorted);
    	for (var i = 0; i < len; i++) {
    	    console.log(rowdatas[i].__id);
    	    console.log(rowdatas[i].__index);
    	}
    	

    	
    	for(var i = 0;i<rowdatas.length;i++){
    		
    	}
    	
    }
	
    //ligerGrid 内部通过选择一个往selected 数组里塞一个 修改起来改动太大
	//返回的rows每个row带有__index属性,为当页的排序，由小到大
	//所以采取将获得的rows数据通过__index排序，则能实现 多选从上到下顺序
	//数据排序
    function secondsort(rowdatas){
    	var len = rowdatas.length;
    	for (var i = 0; i < len; i++) {
    	    for (var j = 0; j < len - 1 - i; j++) {
    	    	// 相邻元素两两对比，将大的换到后面 实现从小到大
    	        if (rowdatas[j].__index > rowdatas[j+1].__index) {
    	        	// 元素交换
    	            var temp = rowdatas[j+1];        
    	            rowdatas[j+1] = rowdatas[j];
    	            rowdatas[j] = temp;
    	        }
    	    }
    	}
    	return rowdatas;
    }
    /*
    $('#addBtn').click(function () {
    	 var columns =
             [
            	 //mintWidth 最小宽度
            	 //type 排序使用到的 包括int float string
                 { display: '主键', 
		           headerRender: function (column) {
						return "<u>" + column.display + "</u>";
                	},
                	name: 'id', type: 'int', mintWidth: 40, width: 100,editor: {type: 'text'}},
                 { display: '名字', name: 'name' },
                 { display: '性别', name: 'sex' },
                 { display: '生日', name: 'birthday', type: 'date' }
              ];
             $("#maingrid").ligerGrid({
            	 checkbox: true,
                 columns: columns,
                 data: jsonObj,
                 //标题
                 title:'我的标题',showTitle:true,
                 //宽度
                 width: '100%',
                 //分页 默认是使用分页的，如果不想使用分页，可以配置 userPager :false
                 //usePager:false,
                 //滚动条：内容有太多的行时会出现滚动体，如果不想显示，可以通过配置isScroll设置是否出现滚动体
                 isScroll:false,
                 frozenCheckbox:false,

                 
             });
    });
    */
</script>

</body>

</html>