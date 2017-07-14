<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<link href="${ctxStatic}/show/css/common.css" rel="stylesheet" type="text/css">
<script src="${ctxStatic}/show/js/jquery.js" type="text/javascript" ></script>

    <!-- ******************* 顶部导航栏 Start ********************* -->
    <div class="top_line"></div>
    <div class="menuer">
        <div class="menu">
            <div class="menu_logo">
                <a href="#">
                <h1><a href="${ctxStatic}/show/index">高校就业管理分析平台</a></h1></a>
            </div>
            <div class="menu_menu">
                <div class="nav1">
                    <ul>
                        <li><a id="index" href="${ctxStatic}/show/index">首页</a></li>
                        
                        <li class="livea"><a id="news" href="#">资讯公告</a>
                            <div class="livs libg3" style="display: none;">
                                <ul>
                                    <li><a href="${ctxStatic}/show/news32/_1">通知公告</a></li>
                                    <li><a href="${ctxStatic}/show/news33/_1">就业资讯</a></li>
                                    <li><a href="${ctxStatic}/show/news34/_1">最新政策</a></li>
                                </ul>
                            </div>
                        </li>
                        
                        <li class="livea"><a id="reploy" class="" href="#">招聘服务</a>
                            <div class="livs libg3" style="display:none;">
                                <ul>
                                	<li><a href="${ctxStatic}/show/preachingmeeting/_1">宣讲会</a></li>
                                    <li><a href="${ctxStatic}/show/employmentinformation/_1">招聘信息</a></li>
                                </ul>
                            </div></li>
                        
                        <%-- <li><a class="" id="quality" href="${ctxStatic}/show/quality" >分析报表</a></li> --%>
                        <%-- <li><a class="" id="studentService" href="${ctxStatic}/show/studentService" >学生服务</a></li> --%>
                        <li><a class="" id="about" href="${ctxStatic}/show/about" >高校信息</a></li>
                        
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(function() {
            $(".livea").hover(function() {
                $(this).find(".livs").show();
            }, function() {
                $(this).find(".livs").hide()
            })
        });
        
        console.log(window.location.href);
        http://localhost:8080/cemap/show/quality
        url1 = window.location.href.split("/");
        console.log(url1[5]);
        console.log(url1[5].indexOf("news"));
        if(url1[5].indexOf("index") >= 0){
        	console.log("首页")
        	$("#index").addClass("cure");
        }
        if(url1[5].indexOf("news") >= 0){
        	console.log("资讯")
        	$("#news").addClass("cure");
        }
        if(url1[5].indexOf("preachingmeeting") >= 0){
       		console.log("招聘")
        	$("#reploy").addClass("cure");
        }
        if(url1[5].indexOf("employmentinformation") >= 0){
       		console.log("招聘")
        	$("#reploy").addClass("cure");
        }
        if(url1[5].indexOf("quality") >= 0){
       		console.log("分析")
        	$("#quality").addClass("cure");
        }
        if(url1[5].indexOf("studentService") >= 0){
       		console.log("学生服务")
        	$("#studentService").addClass("cure");
        }
        if(url1[5].indexOf("about") >= 0){
       		console.log("关于")
        	$("#about").addClass("cure");
        }
        
        /* $(".nav1>ul>li>a").click(function(){
        	console.log($(this).text());
        	$(this).addClass("cure");
        }); */
    </script>                
    <!-- ******************* 顶部导航栏  End  ********************* -->
