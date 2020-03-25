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
    <link rel="stylesheet" href="<%=basePath%>css/outInSubmit.css" media="all">
</head>
<body class="layui-layout-body">

<form id="outIn_form" class="layui-form" action="">
    <c:if test="${method =='update'}">
        <input type="text" name="id" value="${outIn.id}" style="display: none">
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red">*</span>姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                   class="layui-input" value="${outIn.name}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red">*</span>电话</label>
        <div class="layui-input-block">
            <input type="text" name="phone" required lay-verify="required|phone" placeholder="请输入电话" autocomplete="off"
                   class="layui-input" value="${outIn.phone}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red">*</span>方式</label>
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <select name="type" lay-verify="required" lay-filter="type">
                    <option value="">请选择</option>
                    <option value="0">人员</option>
                    <option value="1">车辆</option>
                </select>
            </c:if>
            <c:if test="${method =='update'}">
                <select name="type" lay-verify="required" lay-filter="type" disabled="disabled">
                    <c:if test="${outIn.type == 0}">
                        <option value="0" selected="selected">人员</option>
                    </c:if>
                    <c:if test="${outIn.type == 1}">
                        <option value="1" selected="selected">车辆</option>
                    </c:if>
                </select>
            </c:if>
        </div>
    </div>
    <div id="license">
        <c:if test="${method =='update'&&outIn.license!='无'}">
            <div class="layui-form-item">
                <label class="layui-form-label"><span style="color: red">*</span>车牌</label>
                <div class="layui-input-block">
                    <input type="text" name="license" required lay-verify="required|license" placeholder="请输入车牌"
                           autocomplete="off"
                           class="layui-input" value="${outIn.license}">
                </div>
            </div>
        </c:if>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red">*</span>进入时间</label>
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <input type="text" class="layui-input" name="startTime" required lay-verify="required" id="startTime">
            </c:if>
            <c:if test="${method =='update'}">
                <input type="text" class="layui-input" name="startTime" required lay-verify="required" id="startTime"
                       value="${outIn.startTime}" disabled="disabled">
            </c:if>

        </div>
    </div>

    <c:if test="${method =='update'}">
        <div class="layui-form-item">
            <label class="layui-form-label">离开时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="endTime" id="endTime" value="${outIn.endTime}">
            </div>
        </div>
    </c:if>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <button id="form" method="add" class="layui-btn" lay-submit lay-filter="outInform">提交</button>
            </c:if>
            <c:if test="${method =='update'}">
                <button id="form" method="update" class="layui-btn" lay-submit lay-filter="outInform">保存</button>
            </c:if>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/outInSubmit.js"></script>
</html>

