<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

	<hr style="color:#33CCCC;" />
	<footer class="footer" style="font-size: 12px;font-family: 'Arial', 'Microsoft YaHei', '\5B8B\4F53';background: #fff;">
		<div class="container" id="mo">
			<div class="row footer-top">
				</br>
				<div class="col-sm-4 col-lg-4" id="testborder23">
					<h3 style="color:#343F54">
						<img src="${ctxStatic}/show/image/yflogo.png" width="25px">
						<b>高校就业管理分析平台</b>
					</h3>
					<p style="font-size:14px">
						电话：<a href="tel:">1588888888</a> <br /> 
						邮箱：<a href="mailto:">career@sspu.edu.cn</a><br />
						邮编：201209<br />
						传真：400-888-9999<br /> 
						地址：**大学 - 综合楼4F学生就业服务中心<br />
					</p>

				</div>
				<div class="col-sm-6  col-lg-6 ">
					<div class="row about">
						<div class="col-xs-4">
							<h5>官方微博</h5>
							<a href="http://weibo.com/#" target="_blank" title="去关注">
								<img src="${ctxStatic}/show/image/weibo.png" width="80px"> </a>

						</div>
						<div class="col-xs-4">
							<h5 >官方微信</h5>
							<img  id="weixin1" src="${ctxStatic}/show/image/wechat.png" width="70px" title="扫二维码关注"> <img
								id="code1" src="${ctxStatic}/show/image/wcode.png" width="90px"
								style="display: none;">
						</div>
						<div class="col-xs-4">
							<h5>技术支持</h5>
							<ul class="list-unstyled">
								<li><a href="http://www.html5cn.org/" target="_blank">Ehcache</a>
								</li>
								<li><a href="http://spring.io/" target="_blank">Spring</a>
								</li>
								<li><a href="http://www.bootcss.com/" target="_blank">SpringMVC</a>
								</li>
								<li><a href="https://jquery.com/" target="_blank">Hibernate</a>
								</li>
								<li><a href="http://www.html5cn.org/" target="_blank">Druid</a>
								</li>
								<li><a href="http://www.html5cn.org/" target="_blank">Redis</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<hr/>
			<div class="row footer-bottom">
				<ul class="list-inline text-center">
					<li>CopyRight 2016-2017 © 毕业设计 - 通用性高校就业管理分析平台 Design by 13软工A2  loveincode 
					</li>
				</ul>
			</div>
		</div>
	</footer>
	
	<div style="display: none" id="goTopBtn">
		<img title="返回顶部" border=0 src="${ctxStatic}/show/image/up2.png" width=60px height=60px>
	</div>
	<script src="${ctxStatic}/show/js/gotop.js" type="text/javascript" ></script>
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
