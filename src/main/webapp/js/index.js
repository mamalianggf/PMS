//JavaScript代码区域
layui.use(['element', 'jquery'], function () {
    let element = layui.element,
        $ = layui.$;

    /*let content = $(".layui-body");*/

    //意见反馈
    /*$("#opinion_submit").click(function () {
        $.ajax({
            url: "/PMS/opinion/submit",
            type: "GET",
            success: function (data) {
                content.empty().html(data);
            }
        })
    });*/

    //意见查阅
    $("#opinion_check").click(function () {
        $.ajax({
            url: "/PMS/opinion/check",
            type: "GET",
            success: function (data) {
                content.empty().html(data);
            }
        })
    });

});