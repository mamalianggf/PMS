<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>PMS</title>
    <!-- 该资源在IDE环境中无法找到，是因为视图解析器的原因 -->
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <%--<link rel="stylesheet" href="<%=basePath%>css/login.css" media="all">--%>
</head>
<body>
<div id="container">
    通知页面
</div>
</body>
<%--<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/login.js"></script>--%>
</html>
