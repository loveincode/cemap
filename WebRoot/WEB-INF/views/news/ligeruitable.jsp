<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<title>INDEX</title>
   <%@include file="/WEB-INF/views/common/commoncss.jsp" %>
   <link href="${ctxStatic}/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	
       <!-- ************************* Search Form Start  ************************* -->
    	
       <!-- *************************   Search Form End  ************************* -->

	   <!-- *************************     Table Start    ************************* -->
       <div id="maingrid">
       
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
    
    <!-- ligerUI -->
    <script src="${ctxStatic}/ligerUI/js/core/base.js"></script>
    <script src="${ctxStatic}/ligerUI/js/plugins/ligerGrid.js"></script>
    
    <script type="text/javascript">

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
			pageSize : 20,
			//width : "100%",
			rownumbers : true,
			checkbox : true,
			//autoCheckChildren: false,
			//onReload:gridreload,
			//groupColumnName:'newsType.name',
			//groupColumnDisplay:'类型',
			
			/*toolbar : {
				items : [
						{
							text : '增加新类别',
							click : gridreload,
							icon : 'add'
						},
						{
							line : true
						},
						{
							text : '修改',
							click : gridreload,
							icon : 'modify'
						},
						{
							line : true
						},
						{
							text : '删除',
							click : gridreload,
							img : '${baseURL}/ligerUI/lib/ligerUI/skins/icons/delete.gif'
						} ]
			}
			*/
		})
    });
    
    function gridreload()
    { 
    	console.log("123");
    	$("#maingrid").ligerGrid().reload();
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