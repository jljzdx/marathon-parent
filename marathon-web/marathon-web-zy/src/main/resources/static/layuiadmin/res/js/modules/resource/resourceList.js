;
layui.extend({
    treetable: 'res/js/component/treetable',
}).define(['form', 'treetable'],
function(exports) {
    var $ = layui.$,
    form = layui.form,
    treetable = layui.treetable,
    layer = layui.layer,
    table = layui.table;
    var renderTable = function () {
        layer.load(2);
        treetable.render({
            elem: '#resource-tree-table',
            url: '/sys/resource/inquiry/loop',
            method: 'post',
            treeColIndex: 1,
            treeSpid: -1,
            treeIdName: 'id',
            treePidName: 'parentId',
            page: false,
            toolbar: '#resource-toolbar',
            defaultToolbar: ['filter'],
            title: '资源数据表',
            height: 'full-60',
            loading: true,
            cols: [[{
                type: 'numbers',
                title: "序号列",
                width: 50
            },
            {
                field: 'name',
                title: '资源名称',
                minWidth: 300
            },
            {
                field: 'permission',
                title: '权限码',
                minWidth: 100
            },
            {
                field: 'icon',
                title: '图标',
                minWidth: 120
            },
            {
                field: 'url',
                title: '访问路径',
                minWidth: 250
            },
            {
                field: 'priority',
                title: '显示顺序',
                minWidth: 50
            },
            {
                field: 'type',
                title: '资源类型',
                minWidth: 50,
                templet: '#typeTpl'
            },
            {
                field: 'available',
                title: '状态',
                minWidth: 110,
                unresize: true,
                templet: '#statusTpl'
            },
            {
                title: "操作",
                width: 170,
                align: 'center',
                toolbar: '#resource-bar'
            }]],
            done: function() {
                layer.closeAll('loading');
            }
        });
    }
    renderTable();
    table.on('toolbar(resource-tree-table)',
    function(obj) {
        switch (obj.event) {
        case 'expand':
            treetable.expandAll('#resource-tree-table');
            break;
        case 'fold':
            treetable.foldAll('#resource-tree-table');
            break;
        case 'refresh':
            renderTable();
            break;
        };
    });
    table.on('tool(resource-tree-table)',
    function(obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么',
            function(index) {
                layer.close(index);
            });
        } else if (obj.event === 'add') {
            layer.open({
                type:
                2,
                title: '添加资源信息',
                content: '/resource/add/html',
                maxmin: true,
                area: ['450px', '480px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'resource-add-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    var parentId = data.id;
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        field.parentId = parentId;
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: '/sys/resource/addition',
                            type: 'post',
                            dataType: 'text',
                            data: field,
                            success: function(data) {
                                var result = JSON.parse(data);
                                if (result.transactionStatus.success) {
                                    layer.msg("添加成功");
                                    renderTable(); //数据刷新
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
        } else if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改资源信息',
                content: '/resource/edit/html',
                maxmin: true,
                area: ['450px', '480px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'resource-edit-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    var resourceId = data.id;
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        field.id = resourceId;
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: '/sys/resource/modify',
                            type: 'post',
                            dataType: 'text',
                            data: field,
                            success: function(data) {
                                var result = JSON.parse(data);
                                if (result.transactionStatus.success) {
                                    layer.msg("修改成功");
                                    renderTable(); //数据刷新
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
                        url: '/sys/resource/modify/inquiry',
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
    form.on('checkbox(resourceLock)',
    function(obj) {
        var id = this.value;
        var status;
        if (obj.elem.checked) {
            status = 0;
        } else {
            status = 1;
        }
        $.ajax({
            url: '/sys/resource/modify/status',
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
                    renderTable(); //数据刷新
                } else {
                    layer.msg(result.transactionStatus.replyText);
                }
            }
        });
    });
    exports('resourceList',
    function() {});
});