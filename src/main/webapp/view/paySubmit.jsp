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
    <link rel="stylesheet" href="<%=basePath%>css/paySubmit.css" media="all">
</head>
<body class="layui-layout-body">

<form id="pay_form" class="layui-form" action="">
    <c:if test="${method =='update'}">
        <input type="text" name="id" value="${id}" style="display: none">
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label">应缴金额</label>
        <div class="layui-input-block">
            <input type="text" name="textMoney" required lay-verify="required" placeholder="请输入应缴金额" autocomplete="off"
                   class="layui-input" value="${textMoney}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">实缴金额</label>
        <div class="layui-input-block">
            <input type="text" name="valueMoney" required lay-verify="required" placeholder="请输入实缴金额" autocomplete="off"
                   class="layui-input" value="${valueMoney}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">减免</label>
        <div class="layui-input-block">
            <input type="text" name="tax" required lay-verify="required" placeholder="请输入减免金额" autocomplete="off"
                   class="layui-input" value="${tax}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">缴费项</label>
        <div class="layui-input-block">
            <input type="text" name="comment" required lay-verify="required" placeholder="请输入缴费项" autocomplete="off"
                   class="layui-input" value="${comment}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">付款人账号</label>
        <div class="layui-input-block">
            <c:if test="${method=='add'}">
                <select name="payerId" lay-verify="required" lay-search>
                    <c:forEach items="${users}" var="user" begin="0" end="${usersSize}" step="1">
                        <option value="${user.id}">${user.name}</option>
                    </c:forEach>
                </select>
            </c:if>
            <c:if test="${method=='update'}">
                <select name="payerId" lay-verify="required" disabled="disabled">
                    <option value="${payerId}">${payerName}</option>
                </select>
            </c:if>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">收款人姓名</label>
        <div class="layui-input-block">
            <c:if test="${method=='add'}">
                <select name="payeeId" lay-verify="required" disabled="disabled">
                    <option value="${sessionScope.user.id}">${sessionScope.user.name}</option>
                </select>
            </c:if>
            <c:if test="${method=='update'}">
                <select name="payeeId" lay-verify="required" disabled="disabled">
                    <option value="${payeeId}">${payeeName}</option>
                </select>
            </c:if>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <c:if test="${method =='add'}">
                <button id="form" method="add" class="layui-btn" lay-submit lay-filter="payform">提交</button>
            </c:if>
            <c:if test="${method =='update'}">
                <button id="form" method="update" class="layui-btn" lay-submit lay-filter="payform">保存</button>
            </c:if>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script src="<%=basePath%>js/paySubmit.js"></script>
</html>

