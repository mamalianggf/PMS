layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(opinionform)', function(data){
        $.ajax({
            url: "/opinion/submit",
            type: "POST",
            data: obj.field,
            success: function (data) {
                layer.msg(data.message,{offset: '100px'});
            }
        });
        return false;
    });
});