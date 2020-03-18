layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    $("#pay_form").css({
        position: "absolute",
        left: ($(window).width() - $("#pay_form").outerWidth()) / 2,
        top: ($(window).height() - $("#pay_form").outerHeight()) / 2
    });


    //监听提交
    form.on('submit(payform)', function (data) {
        let url;
        if ("add" == $("#form").attr("method")) {
            url = "/PMS/pay/submit";
        } else {
            url = "/PMS/pay/update";
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
