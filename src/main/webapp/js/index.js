//JavaScript代码区域
layui.use(['element', 'jquery'], function () {
    let element = layui.element,
        $ = layui.$;

    let content = $(".layui-body");

    $("#opinion_submit").click(function () {
        $.ajax({
            url: "/PMS/opinion/submit",
            type: "GET",
            success: function (data) {
                content.empty().html(data);
            }
        })
    });

});