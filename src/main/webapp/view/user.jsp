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
    <link rel="stylesheet" href="<%=basePath%>css/user.css" media="all">
</head>
<body>
<div id="container">
    <div class="layui-row">
        <form class="layui-form layui-col-md12">
            &nbsp;&nbsp;账号:
            <div class="layui-input-inline">
                <input type="text" name="name"
                       placeholder="请输入账号" autocomplete="off" class="layui-input">
            </div>
            &nbsp;姓名:
            <div class="layui-input-inline">
                <input type="text" name="rname"
                       placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
            &nbsp;&nbsp;角色:
            <div class="layui-input-inline">
                <select name="roleId">
                    <option value="">---请选择---</option>
                    <option value="1">保安</option>
                    <option value="2">业主</option>
                    <option value="3">维修人员</option>
                    <option value="4">软件管理人员</option>
                    <option value="5">物业管理人员</option>
                </select>
            </div>
            &nbsp;
            <button class="layui-btn" lay-submit lay-filter="userSearch">
                <i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <table class="layui-hide" id="user" lay-filter="test"></table>

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

    <div class="layui-tab layui-tab-brief" lay-filter="demo">
        <div class="layui-tab-content">
            <div class="layui-tab-item">
                <div id="pageDemo"></div>
            </div>
        </div>
    </div>
    <script src="<%=basePath%>layui/layui.js"></script>
    <script src="<%=basePath%>js/user.js"></script>
</div>
</body>


