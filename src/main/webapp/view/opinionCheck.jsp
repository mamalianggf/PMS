<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<table class="layui-hide" id="opinion" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div class="layui-tab layui-tab-brief" lay-filter="demo">
    <div class="layui-tab-content">
        <div class="layui-tab-item">
            <div id="pageDemo"></div>
        </div>
    </div>
</div>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/opinionCheck.js"></script>



