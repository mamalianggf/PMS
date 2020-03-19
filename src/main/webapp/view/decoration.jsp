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
    <link rel="stylesheet" href="<%=basePath%>css/decoration.css" media="all">
</head>
<body>
<div id="container">
    <div class="layui-row">
        <form class="layui-form layui-col-md12">
            &nbsp;装修地址:
            <div class="layui-input-inline">
                <input type="text" name="address"
                       placeholder="请输入装修地址" autocomplete="off" class="layui-input">
            </div>
            &nbsp;&nbsp;装修住户账号:
            <div class="layui-input-inline">
                <input type="text" name="userName"
                       placeholder="请输入装修住户账号" autocomplete="off" class="layui-input">
            </div>
            &nbsp;
            <button class="layui-btn" lay-submit lay-filter="decorationSearch">
                <i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <table class="layui-hide" id="decoration" lay-filter="test"></table>

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
        {{#  if(d.status == 0){ }}
        未处理
        {{#  } else if(d.status == 1) { }}
        处理中
        {{#  } else{ }}
        已处理
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
    <script src="<%=basePath%>js/decoration.js"></script>
</div>
</body>


