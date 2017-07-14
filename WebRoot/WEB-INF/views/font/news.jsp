<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>CEMAP-高校就业管理分析平台-资讯</title>
	<%@include file="/WEB-INF/views/common/commoncss.jsp" %>
	<meta name="viewport" content="user-scalable=no",width="device-width">
</head>
<style>
</style>
<body class="gray-bg top-navigation">
	<%@include file="/WEB-INF/views/font/top.jsp" %>
    
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">
                    
                    	<ol class="breadcrumb">
							<li><a href="${ctxStatic}/show/index">首页</a></li>
							<li><a href="#">资讯公告</a></li>
						</ol>
						
						<br/>
						
						<div class="row">
						<c:forEach items="${news}" var="newsone">
					        <div class="col-sm-4">
				                <div class="ibox float-e-margins">
				                    <div class="ibox-content">
			                            <a href="${ctxStatic}/show/newsdetail/${newsone.id}" title = "${newsone.title}"><p>
			                            <c:if test="${fn:length(newsone.title) > 15 }">${fn:substring(newsone.title,0,15)}...</c:if>
										<c:if test="${fn:length(newsone.title) <= 15}">${newsone.title}</c:if>
			                            </p></a>
			                            <p class="text-right"><i class="fa fa-calendar text-navy"></i>   <fmt:formatDate value="${newsone.publishDate}" pattern="yyyy-MM-dd HH:mm"/>  <i class="fa fa-eye text-navy"></i> ${newsone.newsClick} </p>
				                    </div>
				                </div>
				            </div>
					    </c:forEach>
                        </div>
                        
                        <!--  *************   分页码    **************** -->
                        <ul class="pagination" id="pagination"></ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%@include file="/WEB-INF/views/font/bottom.jsp" %>
    
	<!-- 全局js -->
	<%@include file="/WEB-INF/views/common/commonjs.jsp" %>
	<script>
		
		$(document).ready(function(){
			if($("#pagination")){
				var counts = ${counts};
				var pagesize = 9;
				var currentpage = ${currentpage}; //传过来
				var pagecount,pagehtml="";
				if(counts%pagesize==0){
					pagecount = parseInt(counts/pagesize);
				}else{
					pagecount = parseInt(counts/pagesize) + 1;
				}
				//只有一页内容
				if(counts <= pagesize){pagehtml="";}
				//大于一页内容
				if(counts > pagesize){
					if(currentpage > 1){
						pagehtml+= '<li><a href="_'+(currentpage-1)+'">上一页</a></li>';
					}
					for(var i = 0;i<pagecount;i++){
						if(i >= (currentpage-5) && i < (currentpage + 5)){
							if(i==currentpage-1){
								pagehtml+= '<li class = "active"><a href="_'+(i+1)+'">'+(i+1)+'</a></li>';
							}
							else{
								pagehtml+= '<li><a href="_'+(i+1)+'">'+(i+1)+'</a></li>';
							}
						}
					}
					if(currentpage<pagecount){
						pagehtml+= '<li><a href="_'+(currentpage+1)+'">下一页</a></li>';
					}
					if(pagecount>1){
						pagehtml+= '<li><span>共 '+pagecount +'页</span></li>';
					}
				}
				$("#pagination").html(pagehtml);
 			}
		});
	
	</script>
	
</body>
</html>