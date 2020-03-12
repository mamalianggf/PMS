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
                console.log(data);
            }
        });
        return false;
    });
});
