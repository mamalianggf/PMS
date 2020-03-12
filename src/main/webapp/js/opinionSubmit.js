layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    //监听提交
    form.on('submit(opinionform)', function (data) {
        $.ajax({
            url: "/PMS/opinion/submit",
            type: "POST",
            data: data.field,
            success: function (data) {
                layer.msg(data.message, {offset: '100px'});
            }
        });
        return false;
    });
});
