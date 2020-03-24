layui.config({
    version: '1583393622887' //为了更新 js 缓存，可忽略
});

layui.use(['laypage', 'layer', 'table', 'jquery', 'form'], function () {
    let laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , form = layui.form //表单
        , $ = layui.$;

    let parm;
    if (getCookie("roleId") == '2') {//只有业主，物业管理员，admin
        parm = {
            elem: '#opinion'
            , method: "GET"
            , url: '/PMS/opinion/list?userId=' + getCookie("userId") //数据接口
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
            , limit: 5
            , limits: [5, 10, 15, 20, 25, 30, 35, 40, 45]
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', fixed: 'left'}
                , {field: 'intro', title: '简介'}
                , {field: 'details', title: '详情'}
                , {field: 'name', title: '创建人账号'}
                , {field: 'rname', title: '创建人姓名'}
                , {field: 'createTime', title: '创建人时间'}
                , {field: 'status', title: '状态', templet: '#titleTpl'}
            ]]
        }
    } else {
        parm = {
            elem: '#opinion'
            , method: "GET"
            , url: '/PMS/opinion/list' //数据接口
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
            , limit: 5
            , limits: [5, 10, 15, 20, 25, 30, 35, 40, 45]
            , toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', fixed: 'left'}
                , {field: 'intro', title: '简介'}
                , {field: 'details', title: '详情'}
                , {field: 'name', title: '创建人账号'}
                , {field: 'rname', title: '创建人姓名'}
                , {field: 'createTime', title: '创建人时间'}
                , {field: 'status', title: '状态', templet: '#titleTpl'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
            ]]
        }
    }
    //执行一个 table 实例
    let opinionTable = table.render(parm);

    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    type: 2,
                    area: ['500px', '300px'],
                    content: ['/PMS/opinion/submit?method=add', 'no'],
                    end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                        opinionTable.reload({});
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
                        url: "/PMS/opinion/delete",
                        type: "POST",
                        data: {
                            opinionIds: arr
                        },
                        success: function (data) {
                            layer.msg(data.message);
                            opinionTable.reload({});
                        }
                    });
                }
                break;
            case 'solve':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    let arr = [];
                    for (let i = 0; i < data.length; i++) {
                        arr.push(data[i].id);
                    }
                    $.ajax({
                        url: "/PMS/opinion/updateStatus",
                        type: "POST",
                        data: {
                            opinionIds: arr
                        },
                        success: function (data) {
                            layer.msg(data.message);
                            opinionTable.reload({});
                        }
                    });
                }
                break;
        }
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'solve') {
            layer.confirm('真的解决该反馈么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                let arr = [obj.data.id];
                $.ajax({
                    url: "/PMS/opinion/updateStatus",
                    type: "POST",
                    data: {
                        opinionIds: arr,
                        status: 20
                    },
                    success: function (data) {
                        layer.msg(data.message);
                        opinionTable.reload({});
                    }
                });
            });
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                let arr = [obj.data.id];
                $.ajax({
                    url: "/PMS/opinion/delete",
                    type: "POST",
                    data: {
                        opinionIds: arr
                    },
                    success: function (data) {
                        layer.msg(data.message);
                        opinionTable.reload({});
                    }
                });
            });
        } else if (layEvent === 'edit') {
            let id = obj.data.id;
            let intro = obj.data.intro;
            let details = obj.data.details;
            layer.open({
                type: 2,
                area: ['500px', '300px'],
                content: ['/PMS/opinion/submit?method=update&id=' + id + '&intro=' + intro + '&details=' + details, 'no'],
                end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                    opinionTable.reload({});
                }
            });
        }
    });

    //分页
    /*laypage.render({
        elem: 'pageDemo' //分页容器的id
        , count: 100 //总页数
        , skin: '#1E9FFF' //自定义选中色值
        //,skip: true //开启跳页
        , jump: function (obj, first) {
            if (!first) {
                layer.msg('第' + obj.curr + '页', {offset: 'b'});
            }
        }
    });*/

    form.on('submit(opinionSearch)', function (data) {
        let formData = data.field;
        let intro = formData.intro,
            id = formData.id,
            status = formData.status;
        opinionTable.reload({
            where: {
                id: id,
                intro: intro,
                status: status
            }
        });
        return false;
    });

    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

        if (arr = document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }

    if ($('input[name="id"]').val() != '') {
        let id = $('input[name="id"]').val();
        let arr = [id];
        let status;
        if (getCookie("roleId") == '2') {//业主：10->1,20->2
            $.ajax({
                url: "/PMS/opinion/list?id=" + id,
                type: "Get",
                async: false,
                success: function (data) {
                    if (data.data[0].status == 10) {
                        status = 1
                    }
                    if (data.data[0].status == 20) {
                        status = 2
                    }
                }
            });
        }
        if (getCookie("roleId") == '5') {//物业：0->10
            status = 10
        }
        $.ajax({
            url: "/PMS/opinion/updateStatus",
            type: "POST",
            data: {
                opinionIds: arr,
                status: status
            },
            async: false,
            success: function (data) {
                $("#opinionSearch").click();
            }
        });
    }
});
