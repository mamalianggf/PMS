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
    <link rel="stylesheet" href="<%=basePath%>css/outIn.css" media="all">
</head>
<body>
<div id="container">
    <div class="layui-row">
        <form class="layui-form layui-col-md12">
            &nbsp;进入时间:
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="startTime" required lay-verify="required" id="startTime">
            </div>
            &nbsp;&nbsp;出去时间:
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="endTime" required lay-verify="required" id="endTime">
            </div>
            &nbsp;方式:
            <div class="layui-input-inline">
                <select name="type" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="0">人员</option>
                    <option value="1">车辆</option>
                </select>
            </div>
            &nbsp;
            <button class="layui-btn" lay-submit lay-filter="outInSearch">
                <i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <table class="layui-hide" id="outIn" lay-filter="test"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        </div>
    </script>

    <script type="text/html" id="barDemo">
        <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="titleTpl">
        {{#  if(d.type == 0){ }}
        行人
        {{#  } else{ }}
        车辆
        {{#  } }}
    </script>

    <div class="layui-tab layui-tab-brief" lay-filter="demo">
        <div class="layui-tab-content">
            <div class="layui-tab-item">
                <div id="pageDemo"></div>
            </div>
        </div>
    </div>
    <script src="<%=basePath%>layui/layui.js"></script>
    <script src="<%=basePath%>js/outIn.js"></script>
</div>
</body>


