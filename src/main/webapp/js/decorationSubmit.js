layui.use(['form', 'jquery', 'laydate'], function () {
    var form = layui.form
        , laydate = layui.laydate
        , $ = layui.$;

    $("#decoration_form").css({
        position: "absolute",
        left: ($(window).width() - $("#decoration_form").outerWidth()) / 2,
        top: ($(window).height() - $("#decoration_form").outerHeight()) / 2
    });

    laydate.render({
        elem: '#startTime'
        , trigger: 'click'
        , min: 1
        , zIndex: 99999999
    });

    form.on('select(userId)', function (data) {
        $.ajax({
            url: "/PMS/user/select",
            type: "POST",
            data: {
                id: data.value
            },
            success: function (data) {
                $('input[name="address"]').val(data.data[0].address);
            }
        });
    });


    //监听提交
    form.on('submit(decorationform)', function (data) {
        let url;
        if ("add" == $("#form").attr("method")) {
            url = "/PMS/decoration/submit";
        } else {
            url = "/PMS/decoration/update";
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
