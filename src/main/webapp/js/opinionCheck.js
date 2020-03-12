layui.config({
    version: '1583393622887' //为了更新 js 缓存，可忽略
});

layui.use(['laypage', 'layer', 'table'], function () {
    let laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格

    //执行一个 table 实例
    table.render({
        elem: '#opinion'
        , height: 420
        , method: "POST"
        , url: '/PMS/opinion/select' //数据接口
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
        , title: '业主反馈表'
        , page: true //开启分页
        , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 80, fixed: 'left'}
            , {field: 'username', title: '用户名', width: 80}
            , {field: 'experience', title: '积分', width: 90, sort: true, totalRow: true}
            , {field: 'sex', title: '性别', width: 80, sort: true}
            , {field: 'score', title: '评分', width: 80, sort: true, totalRow: true}
            , {field: 'city', title: '城市', width: 150}
            , {field: 'sign', title: '签名', width: 200}
            , {field: 'classify', title: '职业', width: 100}
            , {field: 'wealth', title: '财富', width: 135, sort: true, totalRow: true}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}
        ]]
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：' + checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
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
            });
        } else if (layEvent === 'edit') {
            layer.msg('编辑操作');
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


});
