layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    $("#opinion_form").css({
        position: "absolute",
        left: ($(window).width() - $("#opinion_form").outerWidth()) / 2,
        top: ($(window).height() - $("#opinion_form").outerHeight()) / 2
    });

    form.verify({
        intro: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length > 15) {
                return "请控制字数在15以内"
            }
        },
        details: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length > 50) {
                return "请控制字数在50以内"
            }
        }

    });


    //监听提交
    form.on('submit(opinionform)', function (data) {
        let url;
        if ("add" == $("#form").attr("method")) {
            url = "/PMS/opinion/submit";
        } else {
            url = "/PMS/opinion/update";
        }
        $.ajax({
            url: url,
            type: "POST",
            data: data.field,
            success: function (data) {
                layer.msg(data.message, {offset: '100px'});
                if ("add" == $("#form").attr("method")) {
                    $("#reset").click();
                }
            }
        });
        return false;
    });
});
