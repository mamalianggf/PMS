layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    $("#user_form").css({
        position: "absolute",
        left: ($(window).width() - $("#user_form").outerWidth()) / 2,
        top: ($(window).height() - $("#user_form").outerHeight()) / 2
    });

    /*form.on('select(type)', function (data) {
        if (data.value == 2) {
            $("#addressLabel").html("<span style=\"color: red\">*</span>地址");
            $('input[name="address"]').attr("lay-verify","required");

        }
        if (data.value != 2) {
            $("#addressLabel").html("地址");
            $('input[name="address"]').removeAttr("lay-verify")

        }
    });*/


    //监听提交
    let peopleform =form.on('submit(peopleform)', function (data) {
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
                if ("add" == $("#form").attr("method")) {
                    $("#reset").click();
                }
            }
        });
        return false;
    });
});
