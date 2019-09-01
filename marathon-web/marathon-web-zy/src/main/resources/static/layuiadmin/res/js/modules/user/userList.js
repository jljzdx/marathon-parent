;
layui.define(['table', 'form', 'laydate', 'layer'],
function(exports) {
    var $ = layui.$,
    form = layui.form,
    layer = layui.layer,
    table = layui.table,
    laydate = layui.laydate;
    laydate.render({
        elem: '#loginTime',
        type: 'datetime',
        range: true
    });
    table.render({
        elem: '#user-table',
        url: '/sys/user/inquiry/page',
        method: 'post',
        page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'refresh', 'skip'],
            groups: 5 //只显示 1 个连续页码
        },
        limit: 30,
        limits: [30, 60, 90, 120, 150, 180],
        autoSort: false,
        toolbar: '#user-toolbar',
        defaultToolbar: ['filter'],
        title: '用户数据表',
        height: 'full-130',
        loading: true,
        cols: [[{
            type: 'numbers',
            fixed: 'center',
            title: "序号列",
            width: 50
        },
        {
            type: 'checkbox',
            fixed: 'left'
        },
        {
            field: 'userName',
            title: '登录账号',
            minWidth: 120
        },
        {
            field: 'realName',
            title: '真实姓名',
            minWidth: 150
        },
        {
            field: 'mobile',
            title: '手机号',
            minWidth: 130
        },
        {
            field: 'email',
            title: '邮箱',
            minWidth: 200
        },
        {
            field: 'roleNames',
            title: '拥有角色',
            minWidth: 200
        },
        {
            field: 'gender',
            title: '性别',
            templet: '#sexTpl',
            unresize: true,
            minWidth: 100
        },
        {
            field: 'locked',
            title: '状态',
            templet: '#statusTpl',
            unresize: true,
            minWidth: 110,
        },
        {
            field: 'gmtCreate',
            title: '创建时间',
            //sort: true,
            width: 170
        },
        {
            field: 'lastLoginTime',
            title: '上一次登录时间',
            width: 170
        },
        {
            field: 'loginTime',
            title: '最后一次登录时间',
            width: 170
        },
        {
            field: 'loginCount',
            title: '登录次数',
            minWidth: 100
        },
        {
            fixed: 'right',
            title: "操作",
            width: 170,
            toolbar: '#user-bar'
        }]]
    });
    //排序
    table.on('sort(user-table)',
    function(obj) {
        console.log(obj.field); //当前排序的字段名
        console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
        console.log(this); //当前排序的 th 对象
        table.reload('user-table', {
            initSort: obj,
            where: {
                field: obj.field //排序字段
                ,
                type: obj.type //排序方式
            }
        });
    });
    //工具栏事件
    table.on('toolbar(user-table)',
    function(obj) {
        switch (obj.event) {
        case 'add':
            layer.open({
                type:
                2,
                title: '添加用户信息',
                content: '/user/add/html',
                maxmin: true,
                area: ['450px', '470px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'user-add-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: '/sys/user/addition',
                            type: 'post',
                            dataType: 'text',
                            data: field,
                            success: function(data) {
                                var result = JSON.parse(data);
                                if (result.transactionStatus.success) {
                                    layer.msg("添加成功");
                                    table.reload('user-table'); //数据刷新
                                    layer.close(index); //关闭弹层
                                } else {
                                    layer.msg(result.transactionStatus.replyText);
                                }
                            }
                        });
                    });
                    submit.trigger('click');
                }
            });
            break;
        };
    });
    //监听行工具事件
    table.on('tool(user-table)',
    function(obj) {
        var data = obj.data;
        if (obj.event === 'resetPassword') {
            layer.confirm('确定要重置密码么？',
            function(index) {
                layer.close(index);
                $.ajax({
                    url: '/sys/user/reset/password',
                    type: 'post',
                    dataType: 'text',
                    data: {
                        "id": data.id
                    },
                    success: function(data) {
                        var result = JSON.parse(data);
                        if (result.transactionStatus.success) {
                            layer.msg("密码重置成功");
                        } else {
                            layer.msg(result.transactionStatus.replyText);
                        }
                    }
                });
            });
        } else if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改用户信息',
                content: '/user/edit/html',
                maxmin: true,
                area: ['450px', '470px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'user-edit-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: '/sys/user/modify',
                            type: 'post',
                            dataType: 'text',
                            data: field,
                            success: function(data) {
                                var result = JSON.parse(data);
                                if (result.transactionStatus.success) {
                                    layer.msg("修改成功");
                                    table.reload('user-table'); //数据刷新
                                    layer.close(index); //关闭弹层
                                } else {
                                    layer.msg(result.transactionStatus.replyText);
                                }
                            }
                        });
                    });
                    submit.trigger('click');
                },
                success: function(layero, index) {
                    $.ajax({
                        url: '/sys/user/modify/inquiry',
                        type: 'post',
                        dataType: 'text',
                        data: {
                            "id": data.id
                        },
                        success: function(data) {
                            var result = JSON.parse(data);
                            if (result.transactionStatus.success) {
                                var iframeWindow = window['layui-layer-iframe' + index];
                                iframeWindow.initData(result);
                            } else {
                                layer.msg(result.transactionStatus.replyText);
                            }
                        }
                    });
                }
            });
        }
    });
    //监听搜索
    form.on('submit(user-search)',
    function(data) {
        var field = data.field;
        //执行重载
        table.reload('user-table', {
            where: field
        });
        return false;
    });
    //监听锁定操作
    form.on('checkbox(userLock)',
    function(obj) {
        var id = this.value;
        var status;
        if (obj.elem.checked) {
            status = 0;
        } else {
            status = 1;
        }
        $.ajax({
            url: '/sys/user/modify/status',
            type: 'post',
            dataType: 'text',
            data: {
                "id": id,
                "locked": status
            },
            success: function(data) {
                var result = JSON.parse(data);
                if (result.transactionStatus.success) {
                    layer.msg("修改成功");
                    table.reload('user-table'); //数据刷新
                } else {
                    layer.msg(result.transactionStatus.replyText);
                }
            }
        });
    });
    exports('userList',
    function() {});
});