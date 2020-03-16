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
    <link rel="stylesheet" href="<%=basePath%>css/opinionSubmit.css" media="all">
</head>
<body class="layui-layout-body">

<form id="opinion_form" class="layui-form" action="">
    <c:if test="${method =='update'}">
        <input type="text" name="id" value="${id}" style="display: none">
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <input type="text" name="intro" required lay-verify="required" placeholder="请输入简介" autocomplete="off"
                   class="layui-input" value="${intro}">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">详情</label>
        <div class="layui-input-block">
            <textarea name="details" placeholder="请输入内容" class="layui-textarea">${details}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <button id="form" method="add" class="layui-btn" lay-submit lay-filter="opinionform">提交</button>
            </c:if>
            <c:if test="${method =='update'}">
                <button id="form" method="update" class="layui-btn" lay-submit lay-filter="opinionform">保存</button>
            </c:if>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/opinionSubmit.js"></script>
</html>

