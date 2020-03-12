layui.use(['form', 'jquery'], function () {
    let form = layui.form,
        $ = layui.$;
    //提交
    form.on('submit(loginForm)', function (obj) {
        $.ajax({
            url: "authentication",
            type: "POST",
            data: obj.field,
            success: function (data) {
                if ("200" == data.status) {
                    window.location.href = "/PMS/index"
                } else {
                    layer.msg(data.message,{offset: '100px'});
                }
            }
        });
        return false;
    });
});

