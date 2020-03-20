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
    let userTable = table.render({
        elem: '#user'
        , method: "POST"
        , url: '/PMS/user/select' //数据接口
        , where: {roleId: 2}
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
        , title: '用户表'
        , page: true //开启分页
        , limit: 5
        , limits: [5, 10, 15, 20, 25, 30, 35, 40, 45]
        , toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'name', title: '账号'}
            , {field: 'pwd', title: '密码', hide: true}
            , {field: 'phone', title: '电话'}
            , {field: 'address', title: '住址'}
            , {field: 'createTime', title: '入住时间'}
            , {field: 'rname', title: '姓名'}
            /*, {field: 'roleId', title: '角色Id', hide: true}
            , {field: 'roleName', title: '角色'}*/
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
                    content: ['/PMS/user/submit?method=add', 'no'],
                    end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                        userTable.reload({});
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
                        url: "/PMS/user/delete",
                        type: "POST",
                        data: {
                            userIds: arr
                        },
                        success: function (data) {
                            layer.msg(data.message);
                            userTable.reload({});
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
                    url: "/PMS/user/delete",
                    type: "POST",
                    data: {
                        userIds: arr
                    },
                    success: function (data) {
                        layer.msg(data.message);
                        userTable.reload({});
                    }
                });
            });
        } else if (layEvent === 'edit') {
            let id = obj.data.id;
            let name = obj.data.name;
            let pwd = obj.data.pwd;
            let phone = obj.data.phone;
            let address = obj.data.address;
            let rname = obj.data.rname;
            let roleId = obj.data.roleId;
            layer.open({
                type: 2,
                area: ['500px', '500px'],
                content: ['/PMS/user/submit?method=update&id=' + id + '&name=' + name + '&pwd=' + pwd + '&phone=' + phone + '&address=' + address + '&rname=' + rname + '&roleId=' + roleId, 'no'],
                end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
                    userTable.reload({});
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

    form.on('submit(userSearch)', function (data) {
        let formData = data.field;
        let name = formData.name,
            rname = formData.rname,
            roleId = formData.roleId
        userTable.reload({
            where: {
                name: name,
                rname: rname,
                roleId: roleId
            },
            method: 'post',
            url: '/PMS/user/select',
        });
        return false;
    });


});
