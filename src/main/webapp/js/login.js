layui.use(['form', 'jquery', 'layer'], function () {
    let form = layui.form,
        layer = layui.layer,
        $ = layui.$;
    //提交
    form.on('submit(loginForm)', function (obj) {

        let load=layer.load()

        $.ajax({
            url: "authentication",
            type: "POST",
            data: obj.field,
            success: function (data) {
                layer.close(load);
                if ("200" == data.status) {
                    window.location.href = "/PMS/index"
                } else {
                    layer.msg(data.message, {offset: '100px'});
                }
            }
        });
        return false;
    });
});

