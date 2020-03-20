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
    let decorationTable = table.render({
        elem: '#decoration'
        , method: "POST"
        , url: '/PMS/decoration/select' //数据接口
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
        , title: '装修表'
        , page: true //开启分页
        , limit: 5
        , limits: [5, 10, 15, 20, 25, 30, 35, 40, 45]
        , toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'address', title: '地址'}
            , {field: 'startTime', title: '开始时间'}
            , {field: 'userName', title: '用户账户'}
            , {field: 'userRname', title: '用户姓名'}
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
                    content: ['/PMS/decoration/submit?method=add'],
                    end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                        decorationTable.reload({});
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
                        url: "/PMS/decoration/delete",
                        type: "POST",
                        data: {
                            decorationIds: arr
                        },
                        success: function (data) {
                            layer.msg(data.message);
                            decorationTable.reload({});
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
        if (layEvent === 'detail') {
            layer.msg('查看操作');
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                let arr = [obj.data.id];
                $.ajax({
                    url: "/PMS/decoration/delete",
                    type: "POST",
                    data: {
                        decorationIds: arr
                    },
                    success: function (data) {
                        layer.msg(data.message);
                        decorationTable.reload({});
                    }
                });
            });
        } else if (layEvent === 'edit') {
            let id = obj.data.id;
            layer.open({
                type: 2,
                area: ['500px', '500px'],
                content: ['/PMS/decoration/submit?method=update&id=' + id],
                end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                    decorationTable.reload({});
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

    form.on('submit(decorationSearch)', function (data) {
        let formData = data.field;
        let address = formData.address,
            userName = formData.userName;
        decorationTable.reload({
            where: {
                address: address,
                userName: userName
            }
        });
        return false;
    });


});
