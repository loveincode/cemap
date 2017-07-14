<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<style type="text/css">

#bd {
	background-position: center 0;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	-webkit-background-size: cover
}

#mo {
	filter: alpha(opacity =             50);
	background-color: #fff;
}

#testborder23 {
	margin-left: 125px;
}
</style>

	<hr style="color:#33CCCC;" />
	<footer class="footer" style="font-size: 12px;font-family: 'Arial', 'Microsoft YaHei', '\5B8B\4F53';background: #fff;">
		<div class="container" id="mo">
			<div class="row footer-top">
				<div class="col-sm-4 col-lg-4" id="testborder23">
					<h4 style="color:#33CCCC">
						<img src="<%=path%>/show/image/yflogo.png" width="25px"><b>
							一凡科技</b>
					</h4>
					<p>
						联系方式<br/> 
						电话：<a href="tel:15201871932">15201871932</a> <br /> 
						邮箱：<a href="mailto:huyifan@sspu.edu.cn">huyifan@sspu.edu.cn</a><br />
						
						邮编：201209<br />
						传真：021-8888889<br /> 
						地址：上海市浦东新区金海路2360号小高层433室<br />
						

					</p>

				</div>
				<div class="col-sm-6  col-lg-6 ">
					<div class="row about">
						<div class="col-xs-4">
							<h5>官方微博</h5>
							<a href="http://weibo.com/#" target="_blank" title="去关注">
								<img src="<%=path%>/show/image/weibo.png" width="80px"> </a>

						</div>

						<div class="col-xs-4">
							<h5 >官方微信</h5>
							<img  id="weixin1" src="<%=path%>/show/image/wechat.png" width="70px" title="扫二维码关注"> <img
								id="code1" src="<%=path%>/show/image/wcode.png" width="90px"
								style="display: none;">
						</div>

						<div class="col-xs-4">
							<h5>技术支持</h5>
							<ul class="list-unstyled">
							<li><a href="http://www.java.com/" target="_blank">JavaEE</a>
								</li>
								<li><a href="http://www.mysql.com/" target="_blank">Mysql</a>
								</li>
								<li><a href="http://spring.io/" target="_blank">Spring</a>
								</li>
								<li><a href="http://www.bootcss.com/" target="_blank">BootStrap</a>
								</li>
								<li><a href="https://jquery.com/" target="_blank">JQuery</a>
								</li>
								<li><a href="http://www.html5cn.org/" target="_blank">HTML5/CSS3</a>
								</li>
								
							</ul>
						</div>

					</div>

				</div>
			</div>

			<div class="row footer-bottom">
				<ul class="list-inline text-center">
					<li>CopyRight 2016 © Java程序设计实训大作业 Design by 13软工A2 <a href="#">胡一凡</a> 指导老师：唐珊 <a href="<%=path%>/login/index">进入后台管理</a>
					</li>
					<script type="text/javascript">
						var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
								: " http://");
						document
								.write(unescape("%3Cspan id='cnzz_stat_icon_1258470763'%3E%3C/span%3E%3Cscript src='"
										+ cnzz_protocol
										+ "s11.cnzz.com/z_stat.php%3Fid%3D1258470763%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
					</script>
				</ul>
			</div>
		</div>
	</footer>
	
	<div style="display: none" id="goTopBtn">
		<img title="返回顶部" border=0 src="<%=path%>/show/image/up.png" width=60px height=60px>
	</div>
	<script type=text/javascript>
		goTopEx();
		$(document).ready(function() {
		$("#weixin1").mouseover(function() {
			$("#code1").css("display", "");
		});
		
		$("#weixin1").mouseout(function() {
			$("#code1").css("display", "none");
		});
	});
	</script>

</html>
