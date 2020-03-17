layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    $("#user_form").css({
        position: "absolute",
        left: ($(window).width() - $("#user_form").outerWidth()) / 2,
        top: ($(window).height() - $("#user_form").outerHeight()) / 2
    });


    //监听提交
    form.on('submit(userform)', function (data) {
        let url;
        if ("add" == $("#form").attr("method")) {
            url = "/PMS/user/submit";
        } else {
            url = "/PMS/user/update";
        }
        $.ajax({
            url: url,
            type: "POST",
            data: data.field,
            success: function (data) {
                layer.msg(data.message, {offset: '100px'});
                $("#reset").click();
            }
        });
        return false;
    });
});
