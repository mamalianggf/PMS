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
</head>
<body class="layui-layout-body">

<form id="pwdform" class="layui-form" style="width: 40%;min-width:400px;">


    <input type="text" name="id" value="${sessionScope.user.id}" style="display: none">

    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red">*</span>旧密码</label>
        <div class="layui-input-block">
            <input type="password" name="opwd" required lay-verify="required" placeholder="请输入旧密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label layui-required"><span style="color: red">*</span>新密码</label>
        <div class="layui-input-block">
            <input type="password" name="pwd" required lay-verify="required" placeholder="请输入新密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label layui-required"><span style="color: red">*</span>重复密码</label>
        <div class="layui-input-block">
            <input type="password" name="cpwd" required lay-verify="required|confirm" placeholder="请再次输入新密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="form" class="layui-btn" lay-submit lay-filter="form">重置密码</button>
        </div>
    </div>
</form>

</body>
<script src="<%=basePath%>layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery'], function () {
        var form = layui.form
            , $ = layui.$;

        $("#pwdform").css({
            position: "absolute",
            left: ($(window).width() - $("#pwdform").outerWidth()) / 2,
            top: ($(window).height() - $("#pwdform").outerHeight()) / 2
        });

        form.verify({
            confirm: function (value, item) { //value：表单的值、item：表单的DOM对象
                let pwd = $('input[name="pwd"]').val();
                if (value != pwd) {
                    return "请确保两次输入一致"
                }
            }

        });


        //监听提交
        form.on('submit(form)', function (data) {
            $.ajax({
                url: "/PMS/user/update",
                type: "POST",
                data: {
                    id: data.field.id,
                    oldPwd: data.field.opwd,
                    pwd: data.field.pwd
                },
                success: function (data) {
                    layer.msg(data.message, {offset: '100px'});
                    if ("200" == data.status) {
                        window.parent.location.href = "/PMS/login";
                    }
                }
            });
            return false;
        });
    });


</script>
</html>

