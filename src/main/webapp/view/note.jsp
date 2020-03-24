<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>PMS</title>
    <!-- 该资源在IDE环境中无法找到，是因为视图解析器的原因 -->
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>
<body>
<div id="container">
    <c:if test="${opinions !=null}">
        <div class="layui-card">
            <div class="layui-card-header" style="font-size: 21px;">反馈</div>
            <div class="layui-card-body">
                <c:forEach items="${opinions}" var="opinion" begin="0" end="${opinionsSize}" step="1">
                    <a href="/PMS/opinion?id=${opinion.id}"
                       target="iframeName"><strong>${opinion.name}于${opinion.createTime}反馈了${opinion.intro},点击查看进程</strong></a><br>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>
</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script>
    layui.use(['jquery'], function () {
        let $ = layui.$;
        /*if ($("#container").innerText == "") {
            $("#container").innerText = "当前无通知，若存在延迟请点击通知刷新"
        }*/
    })
</script>
</html>
