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
    <link rel="stylesheet" href="<%=basePath%>css/decorationSubmit.css" media="all">
</head>
<body class="layui-layout-body">

<form id="decoration_form" class="layui-form" action="">
    <c:if test="${method =='update'}">
        <input type="text" name="id" value="${decoration.id}" style="display: none">
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label">装修住址</label>
        <div class="layui-input-block">
            <input type="text" name="address" required lay-verify="required" placeholder="请输入装修住址" autocomplete="off"
                   class="layui-input" value="${decoration.address}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
            <input type="text" name="startTime" required lay-verify="required" placeholder="请输入开始时间" autocomplete="off"
                   class="layui-input" value="${decoration.startTime}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户账号</label>
        <div class="layui-input-block">
            <c:if test="${method=='add'}">
                <select name="userId" lay-verify="required" lay-search>
                    <c:forEach items="${users}" var="user" begin="0" end="${usersSize}" step="1">
                        <option value="${user.id}">${user.name}</option>
                    </c:forEach>
                </select>
            </c:if>
            <c:if test="${method=='update'}">
                <select name="userId" lay-verify="required" disabled="disabled">
                    <option value="${decoration.userId}">${decoration.userName}</option>
                </select>
            </c:if>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <button id="form" method="add" class="layui-btn" lay-submit lay-filter="decorationform">提交</button>
            </c:if>
            <c:if test="${method =='update'}">
                <button id="form" method="update" class="layui-btn" lay-submit lay-filter="decorationform">保存</button>
            </c:if>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/decorationSubmit.js"></script>
</html>

