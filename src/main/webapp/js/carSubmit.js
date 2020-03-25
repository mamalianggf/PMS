layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    $("#car_form").css({
        position: "absolute",
        left: ($(window).width() - $("#car_form").outerWidth()) / 2,
        top: ($(window).height() - $("#car_form").outerHeight()) / 2
    });

    form.verify({
        license: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (!new RegExp("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$").test(value)) {
                return '请正确输入车牌号';
            }
        }
    })


    //监听提交
    form.on('submit(carform)', function (data) {
        let url;
        if ("add" == $("#form").attr("method")) {
            url = "/PMS/car/submit";
        } else {
            url = "/PMS/car/update";
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
