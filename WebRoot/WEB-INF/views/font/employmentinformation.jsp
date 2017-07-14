<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>CEMAP-高校就业管理分析平台-招聘信息</title>
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
							<li><a href="#">招聘服务</a></li>
							<li><a href="#">招聘信息</a></li>
						</ol>
						<br>
						<center>
						<a = href="${ctxStatic}/show/employmentinformation/_1/">
                        <button type="button" class="btn btn-success">All</button></a>
						<a = href="${ctxStatic}/show/employmentinformation国有企业/_1/">
						<button type="button" class="btn btn-primary">国有企业</button></a>
						<a = href="${ctxStatic}/show/employmentinformation民营企业/_1/">
                        <button type="button" class="btn btn-default">民营企业</button></a>
                        <a = href="${ctxStatic}/show/employmentinformation党政机关/_1/">
                        <button type="button" class="btn btn-info">党政机关</button></a>
                        <a = href="${ctxStatic}/show/employmentinformation高等教育单位/_1/">
                        <button type="button" class="btn btn-warning">高等教育单位</button></a>
                        <a = href="${ctxStatic}/show/employmentinformation其他事业单位/_1/">
                        <button type="button" class="btn btn-danger">其他事业单位</button></a>
                        <a = href="${ctxStatic}/show/employmentinformation三资企业/_1/">
                        <button type="button" class="btn btn-success">三资企业</button></a>
                        <a = href="${ctxStatic}/show/employmentinformation科研设计单位/_1/">
                        <button type="button" class="btn btn-primary">科研设计单位</button></a>
                        <a = href="${ctxStatic}/show/employmentinformation中初教育单位/_1/">
                        <button type="button" class="btn btn-default">中初教育单位</button></a>
                        </center>
                        <br/>
                        
                        <table class="table table-hover">
                        	<thead>
	                            <tr>
	                                <th>状态</th>
	                                <th>主题</th>
	                                <th>单位</th>
	                                <th>单位类型</th>
	                                <th>截止日期</th>
	                                <th>操作</th>
	                            </tr>
	                        </thead>
	                       	<tbody>
	                       		<c:forEach items="${recruitmentInformations}" var="recruitmentInformation">
		                        <tr>
		                            <td class="project-status">
		                                <span class="label label-primary">进行中
		                            </td>
		                            <td class="project-title">
		                                <a href="${ctxStatic}/show/employmentinformationdetail/${recruitmentInformation.uuid}">${recruitmentInformation.theme }</a>
		                                <br/>
		                            </td>
		                            <td class="project-completion">
		                                    <small>${recruitmentInformation.companyName }</small>
		                            </td>
		                            <td class="project-completion">
		                                ${recruitmentInformation.companyType }
		                            </td>
		                            <td class="project-completion">
		                            	${recruitmentInformation.deadline }
		                            </td>
		                            <td class="project-completion">
                                        <a href="${ctxStatic}/show/employmentinformationdetail/${recruitmentInformation.uuid}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                    </td>
		                        </tr>
		                        </c:forEach>
	                        </tbody>
                       </table>
                       
                       <hr>
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
				var pagesize = 10;
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