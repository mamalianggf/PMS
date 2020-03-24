<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>PMS</title>
    <!-- 该资源在IDE环境中无法找到，是因为视图解析器的原因 -->
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>css/opinion.css" media="all">
</head>
<body>
<div id="container">
    <div class="layui-row">
        <form class="layui-form layui-col-md12">
            &nbsp;id:
            <div class="layui-input-inline">
                <input type="text" name="id"
                       placeholder="请输入id" autocomplete="off" class="layui-input" value="${id}">
            </div>
            &nbsp;简介:
            <div class="layui-input-inline">
                <input type="text" name="intro"
                       placeholder="请输入简介" autocomplete="off" class="layui-input">
            </div>
            &nbsp;&nbsp;状态:
            <div class="layui-input-inline">
                <select name="status">
                    <option value="">---请选择---</option>
                    <option value="0">未处理</option>
                    <option value="1">处理中</option>
                    <option value="2">已处理</option>
                </select>
            </div>
            &nbsp;
            <button id="opinionSearch" class="layui-btn" lay-submit lay-filter="opinionSearch">
                <i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <table class="layui-hide" id="opinion" lay-filter="test"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
            <c:if test="${sessionScope.role.id=='4'}"><!-- 只有管理员可以删除 -->
            <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
            </c:if>
            <%--<c:if test="${sessionScope.role.id=='5'}"><!-- 只有物业管理员可以解决 -->
            <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="solve">解决</button>
            </c:if>--%>
        </div>
    </script>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <c:if test="${sessionScope.role.id=='4'}"><!-- 只有管理员可以删除 -->
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </c:if>
        <c:if test="${sessionScope.role.id=='5'}"><!-- 只有物业管理员可以解决 -->

        {{#  if(d.status.toString().substr(0,1) == 2){ }}
        <button class="layui-btn layui-btn-xs layui-btn-disabled">已解决</button>
        {{#  } else { }}
        <button class="layui-btn layui-btn-xs layui-btn-warn" lay-event="solve">解决</button>
        {{#  } }}
        </c:if>
    </script>

    <script type="text/html" id="titleTpl">
        {{#  if(d.status.toString().substr(0,1) == 0){ }}
        未处理
        {{#  } else if(d.status.toString().substr(0,1) == 1) { }}
        处理中
        {{#  } else if(d.status.toString().substr(0,1) == 2){ }}
        已处理
        {{#  } else { }}
        未知数据
        {{#  } }}
    </script>

    <%--<div class="layui-tab layui-tab-brief" lay-filter="demo">
        <div class="layui-tab-content">
            <div class="layui-tab-item">
                <div id="pageDemo"></div>
            </div>
        </div>
    </div>--%>
    <script src="<%=basePath%>layui/layui.js"></script>
    <script src="<%=basePath%>js/opinion.js"></script>
</div>
</body>


