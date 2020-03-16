layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    $("#opinion_form").css({
        position: "absolute",
        left: ($(window).width() - $("#opinion_form").outerWidth()) / 2,
        top: ($(window).height() - $("#opinion_form").outerHeight()) / 2
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
                $("#reset").click();
            }
        });
        return false;
    });
});
