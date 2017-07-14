<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>zy</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="../user/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../user/css/font-awesome.min.css?v=4.3.0" rel="stylesheet">
    <link href="../user/css/animate.min.css" rel="stylesheet">
    <link href="../user/css/style.min.css?v=3.2.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>个人资料</h5>
                    </div>
                    <div>
                        <div class="ibox-content profile-content">
                            欢迎 <font size=10px>${member.realname } </font>进入高校就业管理分析平台
                            <br/>
                            <!-- <p><i class="fa fa-map-marker"></i></p> -->
                            <p>
                            	<security:authorize ifAnyGranted="ROLE_ADMIN">
                                权限：管理员
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_TEACHER_ADMIN">
                                权限：就业处
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_TEACHER">
                                权限：辅导员权限
                                </security:authorize>
                                <security:authorize ifAnyGranted="ROLE_STUDENT">
                                权限：学生
                                </security:authorize>
                            </p>    
                            <p>
                            </p>
                            <p>
                                手机号：${member.phone }
                            </p>
                            <p>
                                邮箱：${member.email }
                            </p>
                            <br/>
                            <div class="user-button">
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <a href="../personal/modifyPage"><button type="button" class="btn btn-primary btn-sm btn-block"><i class="fa fa-child"></i>个人信息</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="../user/js/jquery-2.1.1.min.js"></script>
    <script src="../user/js/bootstrap.min.js?v=3.4.0"></script>



    <!-- 自定义js -->
    <script src="../user/js/content.min.js?v=1.0.0"></script>


    <!-- Peity -->
    <script src="../user/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Peity -->
    <script src="../user/js/demo/peity-demo.min.js"></script>


</body>

</html>