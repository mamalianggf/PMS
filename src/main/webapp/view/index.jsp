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
    <link rel="stylesheet" href="<%=basePath%>css/index.css" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">PMS 小区物业管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <%--<ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${sessionScope.role.name}
                    ${sessionScope.user.name}
                </a>
                <%--<dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>--%>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/exit">注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <c:if test="${sessionScope.role.id=='2' ||sessionScope.role.id=='5'||sessionScope.role.id=='4'}"><!-- 仅业主，物业管理员，admin展示 -->
                <li class="layui-nav-item">
                    <a href="javascript:;">业主反馈</a>
                    <dl class="layui-nav-child">
                        <c:if test="${sessionScope.role.id=='2'}"><!-- 仅业主展示 -->
                        <dd><a href="/PMS/opinion/submit?method=add" target="iframeName">意见反馈</a></dd>
                        </c:if>
                        <dd><a href="/PMS/opinion" target="iframeName">意见查阅</a></dd>
                    </dl>
                </li>
                </c:if>

                <c:if test="${sessionScope.role.id=='5'||sessionScope.role.id=='4'}"><!-- 仅物业管理员，admin展示 -->
                <li class="layui-nav-item">
                    <a href="javascript:;">住户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/PMS/user" target="iframeName">住户信息查询</a></dd>
                        <dd><a href="/PMS/decoration" target="iframeName">装修登记查询</a></dd>
                        <dd><a href="javascript:;">车辆车位登记</a></dd>
                    </dl>
                </li>
                </c:if>

                <c:if test="${sessionScope.role.id=='1'||sessionScope.role.id=='4'||sessionScope.role.id=='5'}"><!-- 仅保安，物业管理员，admin展示 -->
                <li class="layui-nav-item">
                    <a href="javascript:;">出入管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">出入人员车辆登记</a></dd>
                    </dl>
                </li>
                </c:if>

                <c:if test="${sessionScope.role.id=='3'||sessionScope.role.id=='4'||sessionScope.role.id=='5'}">
                    <li class="layui-nav-item">
                        <a href="javascript:;">维修管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;">设备报修</a></dd>
                            <dd><a href="javascript:;">装修清单</a></dd>
                            <dd><a href="javascript:;">维修记录</a></dd>
                        </dl>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role.id=='5'||sessionScope.role.id=='4'}">
                    <li class="layui-nav-item">
                        <a href="javascript:;">缴费管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/PMS/pay" target="iframeName">缴费查询</a></dd>
                        </dl>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="iframeMain" name="iframeName" src="/PMS/note"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © PMS - 小区物业管理系统
    </div>
</div>
</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/index.js"></script>
</html>