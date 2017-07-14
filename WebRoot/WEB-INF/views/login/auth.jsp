<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" href="../user/css/bootstrap.min.css">
    <link rel="stylesheet" href="../user/css/login.css">
    <link href="${ctxStatic}/show/image/yflogo.ico" rel="shortcut icon" >
	<%-- <%@include file="/WEB-INF/views/common/commoncss.jsp" %> --%>
    <title>登录</title>
</head>

<body>
    <span class="logo">
        <h2 style="margin:60px"><b>高校就业分析管理平台</b></h2>
    </span>

    <section class="login-wrap">
        <div class="row">
            <div class="col-xs-6 ">
                <div class="title">
                </div>
            </div>
            <div class="col-xs-6 ">
                <form class="login-box" action="../j_spring_security_check" method="post" id='loginForm' autocomplete='off'>
                    <p>CEMAP Welcome, 欢迎您 ！</p><span id="errorpass" style="display:none"  style="color:red">用户名或密码，请重新输入</span>
                    <input class="user" type="text" value="" placeholder="用户名"  name='j_username' id='j_username'>
                    <input class="psd" type="password" value="" placeholder="密  码" name='j_password' id='j_password'>
                    <div class="btn-wrap">
                        <button type="submit" class="btn-submit" style="margin-left:90px">登录</button>
                        <!-- <span><input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>记住密码</span> -->
                        <!-- <a href="#">忘记密码？</a> -->
                    </div>
                    
                </form>
            </div>
        </div>
    </section>
<!-- 全局js -->
	<%@include file="/WEB-INF/views/common/commonjs.jsp" %>
<script>
	function GetRequest(){
		var url = location.search;
		var theRequest = new Object();
		if(url.indexOf("?")!=-1){
			var str = url.substr(1);
			strs = str.split("&");
			for(var i =0;i<strs.length;i++){
				theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
	
	var Request = new Object();
	Request = GetRequest();
	console.log(Request);
	console.log(Request['error']);
	if(Request['error']=="true"){
		console.log("密码错误");
		$('#errorpass').css('display','block');
	}
	else{
		$('#errorpass').css('display','none');
	}
	
</script>
</body>
</html>
