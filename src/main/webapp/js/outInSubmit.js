layui.use(['form', 'jquery', 'laydate'], function () {
    var form = layui.form
        , laydate = layui.laydate
        , $ = layui.$;

    $("#outIn_form").css({
        position: "absolute",
        left: ($(window).width() - $("#outIn_form").outerWidth()) / 2,
        top: ($(window).height() - $("#outIn_form").outerHeight()) / 2
    });

    form.verify({
        license: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (!new RegExp("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$").test(value)) {
                return '请正确输入车牌号';
            }
        }
    })

    form.on('select(type)', function (data) {
        if (data.value == 1) {
            $("#license").html("<div class=\"layui-form-item\" >\n" +
                "            <label class=\"layui-form-label\"><span style=\"color: red\">*</span>车牌</label>\n" +
                "            <div class=\"layui-input-block\">\n" +
                "                <input type=\"text\" name=\"license\" required lay-verify=\"required|license\" placeholder=\"请输入车牌\"\n" +
                "                       autocomplete=\"off\"\n" +
                "                       class=\"layui-input\">\n" +
                "            </div>\n" +
                "        </div>");
        } else {
            $("#license").html("");
        }
        $("#outIn_form").css({
            position: "absolute",
            left: ($(window).width() - $("#outIn_form").outerWidth()) / 2,
            top: ($(window).height() - $("#outIn_form").outerHeight()) / 2
        });
    });

    laydate.render({
        elem: '#startTime'
        , trigger: 'click'
        , min: 0
        , type: 'datetime'
        ,format:'yyyy-MM-dd HH:mm:ss'
    });

    let startTime = $('input[name="startTime"]').val();

    laydate.render({
        elem: '#endTime'
        , trigger: 'click'
        , min: startTime
        , type: 'datetime'
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
