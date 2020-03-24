layui.use(['form', 'jquery', 'laydate'], function () {
    var form = layui.form
        , laydate = layui.laydate
        , $ = layui.$;

    $("#outIn_form").css({
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



    //监听提交
    form.on('submit(outInform)', function (data) {
        let url;
        if ("add" == $("#form").attr("method")) {
            url = "/PMS/outIn/submit";
        } else {
            url = "/PMS/outIn/update";
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
