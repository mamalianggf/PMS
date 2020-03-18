layui.config({
    version: '1583393622887' //为了更新 js 缓存，可忽略
});

layui.use(['laypage', 'layer', 'table', 'jquery', 'form'], function () {
    let laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , form = layui.form //表单
        , $ = layui.$;

    //执行一个 table 实例
    let payTable = table.render({
        elem: '#pay'
        , method: "POST"
        , url: '/PMS/pay/select' //数据接口
        , parseData: function (res) { //res 即为原始返回的数据
            if ("200" == res.status) {
                res.status = 0;
            }
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        , title: '缴费表'
        , page: true //开启分页
        , toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'textMoney', title: '应缴金额'}
            , {field: 'valueMoney', title: '实缴金额'}
            , {field: 'tax', title: '减免'}
            , {field: 'comment', title: '缴费项'}
            , {field: 'createTime', title: '创建时间'}
            , {field: 'payerName', title: '付款人账号'}
            , {field: 'payeeName', title: '收款人账号'}
            , {field: 'payerId', title: '付款人ID', hide: true}
            , {field: 'payeeId', title: '收款人ID', hide: true}
            , {fixed: 'right', align: 'center', toolbar: '#barDemo'}
        ]]
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    type: 2,
                    area: ['500px', '500px'],
                    content: ['/PMS/pay/submit?method=add', 'no'],
                    end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                        payTable.reload({});
                    }
                });
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    let arr = [];
                    for (let i = 0; i < data.length; i++) {
                        arr.push(data[i].id);
                    }
                    $.ajax({
                        url: "/PMS/pay/delete",
                        type: "POST",
                        data: {
                            payIds: arr
                        },
                        success: function (data) {
                            layer.msg(data.message);
                            payTable.reload({});
                        }
                    });
                }
                break;
        }
        ;
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                let arr = [obj.data.id];
                $.ajax({
                    url: "/PMS/pay/delete",
                    type: "POST",
                    data: {
                        payIds: arr
                    },
                    success: function (data) {
                        layer.msg(data.message);
                        payTable.reload({});
                    }
                });
            });
        } else if (layEvent === 'edit') {
            let id = obj.data.id;
            let textMoney = obj.data.textMoney;
            let valueMoney = obj.data.valueMoney;
            let tax = obj.data.tax;
            let comment = obj.data.comment;
            let payerId = obj.data.payerId;
            let payeeId = obj.data.payeeId;
            let payerName = obj.data.payerName;
            let payeeName = obj.data.payeeName;
            layer.open({
                type: 2,
                area: ['500px', '500px'],
                content: ['/PMS/pay/submit?method=update&id=' + id + '&textMoney=' + textMoney + '&valueMoney=' + valueMoney+ '&tax=' + tax+ '&comment=' + comment+ '&payerId=' + payerId+ '&payerName=' + payerName+ '&payeeId=' + payeeId+ '&payeeName=' + payeeName, 'no'],
                end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                    payTable.reload({});
                }
            });
        }
    });

    //分页
    laypage.render({
        elem: 'pageDemo' //分页容器的id
        , count: 100 //总页数
        , skin: '#1E9FFF' //自定义选中色值
        //,skip: true //开启跳页
        , jump: function (obj, first) {
            if (!first) {
                layer.msg('第' + obj.curr + '页', {offset: 'b'});
            }
        }
    });

    form.on('submit(paySearch)', function (data) {
        let formData = data.field;
        let payerRname = formData.payerRname,
            payeeRname = formData.payeeRname;
        payTable.reload({
            where: {
                payerRname: payerRname,
                payeeRname: payeeRname
            },
            method: 'post',
            url: '/PMS/pay/select',
        });
        return false;
    });


});
