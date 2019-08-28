;
layui.define(['jquery', 'table', 'form', 'layer'],
function(exports) {
    var $ = layui.$,
    form = layui.form,
    layer = layui.layer,
    table = layui.table;
    table.render({
        elem: '#role-table',
        url: '/sys/role/inquiry/page',
        method: 'post',
        page: true,
        //开启分页
        toolbar: '#role-toolbar',
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
            field: 'roleName',
            title: '角色名称',
            minWidth: 200
        },
        {
            field: 'available',
            title: '状态',
            minWidth: 110,
            unresize: true,
            templet: '#statusTpl'
        },
        {
            field: 'gmtCreate',
            title: '创建时间',
            width: 170
        },
        {
            field: 'remark',
            title: '描述',
            width: 500
        },
        {
            fixed: 'right',
            title: "操作",
            width: 110,
            align: 'center',
            toolbar: '#role-bar'
        }]]
    });
    table.on('toolbar(role-table)',
    function(obj) {
        switch (obj.event) {
        case 'add':
            layer.open({
                type:
                2,
                title: '添加角色信息',
                content: '/role/add/html',
                maxmin: true,
                area: ['450px', '300px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'role-add-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: '/sys/role/addition',
                            type: 'post',
                            dataType: 'text',
                            data: field,
                            success: function(data) {
                                var result = JSON.parse(data);
                                if (result.transactionStatus.success) {
                                    layer.msg("添加成功");
                                    table.reload('role-table'); //数据刷新
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
    table.on('tool(role-table)',
    function(obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么',
            function(index) {
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改用户信息',
                content: '/role/edit/html',
                maxmin: true,
                area: ['450px', '300px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'role-edit-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: '/sys/role/modify',
                            type: 'post',
                            dataType: 'text',
                            data: field,
                            success: function(data) {
                                var result = JSON.parse(data);
                                if (result.transactionStatus.success) {
                                    layer.msg("修改成功");
                                    table.reload('role-table'); //数据刷新
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
                        url: '/sys/role/modify/inquiry',
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
    form.on('submit(role-search)',
    function(data) {
        var field = data.field;
        //执行重载
        table.reload('role-table', {
            where: field
        });
        return false;
    });
    form.on('checkbox(roleLock)',
    function(obj) {
        var id = this.value;
        var status;
        if (obj.elem.checked) {
            status = 0;
        } else {
            status = 1;
        }
        $.ajax({
            url: '/sys/role/modify/status',
            type: 'post',
            dataType: 'text',
            data: {
                "id": id,
                "available": status
            },
            success: function(data) {
                var result = JSON.parse(data);
                if (result.transactionStatus.success) {
                    layer.msg("修改成功");
                    table.reload('role-table'); //数据刷新
                } else {
                    layer.msg(result.transactionStatus.replyText);
                }
            }
        });
    });
    exports('roleList',
    function() {});
});