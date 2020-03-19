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
    <link rel="stylesheet" href="<%=basePath%>css/userSubmit.css" media="all">
</head>
<body class="layui-layout-body">

<form id="user_form" class="layui-form" action="">
    <c:if test="${method =='update'}">
        <input type="text" name="id" value="${user.id}" style="display: none">
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-block">
            <c:choose>
                <c:when test="${user.name != ''&& user.name!=null}">
                    <input type="text" name="name" required lay-verify="required" placeholder="请输入账号" autocomplete="off"
                           class="layui-input" value="${user.name}" disabled="disabled">
                </c:when>
                <c:otherwise>
                    <input type="text" name="name" required lay-verify="required" placeholder="请输入账号" autocomplete="off"
                           class="layui-input">
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="text" name="pwd" required lay-verify="required" placeholder="请输入密码" autocomplete="off"
                   class="layui-input" value="${user.pwd}">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
            <input type="text" name="phone" required lay-verify="required" placeholder="请输入电话" autocomplete="off"
                   class="layui-input" value="${user.phone}">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" name="address" required lay-verify="required" placeholder="请输入地址" autocomplete="off"
                   class="layui-input" value="${user.address}">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="rname" required lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                   class="layui-input" value="${user.rname}">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select name="roleId">
                <option value="">---请选择---</option>
                <c:choose>
                    <c:when test="${user.roleId == '1'}">
                        <option value="1" selected>保安</option>
                    </c:when>
                    <c:otherwise>
                        <option value="1">保安</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.roleId == '2'}">
                        <option value="2" selected>业主</option>
                    </c:when>
                    <c:otherwise>
                        <option value="2">业主</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.roleId == '3'}">
                        <option value="3" selected>维修人员</option>
                    </c:when>
                    <c:otherwise>
                        <option value="3">维修人员</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.roleId == '4'}">
                        <option value="4" selected>软件管理人员</option>
                    </c:when>
                    <c:otherwise>
                        <option value="4">软件管理人员</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.roleId == '5'}">
                        <option value="5" selected>物业管理人员</option>
                    </c:when>
                    <c:otherwise>
                        <option value="5">物业管理人员</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <button id="form" method="add" class="layui-btn" lay-submit lay-filter="userform">提交</button>
            </c:if>
            <c:if test="${method =='update'}">
                <button id="form" method="update" class="layui-btn" lay-submit lay-filter="userform">保存</button>
            </c:if>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/userSubmit.js"></script>
</html>

