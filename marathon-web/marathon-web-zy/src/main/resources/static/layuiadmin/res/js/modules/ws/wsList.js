;
layui.define(['table', 'form', 'layer'],
function(exports) {
    var $ = layui.$,
    form = layui.form,
    layer = layui.layer,
    table = layui.table;
    var socket;
    table.render({
        elem: '#ws-table',
        url: '/ws/inquiry/page',
        method: 'post',
        page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'refresh', 'skip'],
            groups: 5 //只显示 1 个连续页码
        },
        limit: 30,
        limits: [30, 60, 90, 120, 150, 180],
        autoSort: false,
        toolbar: '#ws-toolbar',
        defaultToolbar: ['filter'],
        title: 'websocket数据表',
        height: 'full-130',
        loading: true,
        cols: [[{
            type: 'numbers',
            fixed: 'center',
            title: "序号列",
            width: 100
        },
        {
            field: 'clientId',
            title: '客户端ID',
            minWidth: 200
        },
        {
            field: 'address',
            title: '地址',
            minWidth: 200
        },
        {
            field: 'createTime',
            title: '创建时间',
            width: 170
        },
        {
            fixed: 'right',
            title: "操作",
            width: 170,
            toolbar: '#ws-bar'
        }]]
    });
    //工具栏事件
    table.on('toolbar(ws-table)',
    function(obj) {
        switch (obj.event) {
        case 'add':
            layer.open({
                type:
                2,
                title: '添加一个连接',
                content: '/ws/add/html',
                maxmin: true,
                area: ['400px', '200px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                    submitID = 'ws-add-submit',
                    submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')',
                    function(data) {
                        var field = data.field; //获取提交的字段
                        socket = new WebSocket('ws://localhost:8340/websocket/'+field.clientId);
                        //连接成功时触发
                        socket.onopen = function() {
                            console.log("连接成功");
                            layer.msg("连接成功");
                            //socket.send('XXX连接成功');
                        };
                        //监听收到的消息
                        socket.onmessage = function(res) {
                            //res为接受到的值，如 {"emit": "messageName", "data": {}}
                            //emit即为发出的事件名，用于区分不同的消息
                            layer.msg("收到来自服务端的消息："+res.data);
                        };
                        //关闭连接
                        socket.onclose = function(res) {
                            console.log(res);
                            layer.msg("连接已关闭");
                        };
                        table.reload('ws-table'); //数据刷新
                        layer.close(index); //关闭弹层
                    });
                    submit.trigger('click');
                }
            });
            break;
        case 'send':
            layer.confirm('确定要群发数据吗？',
            function(index) {
                layer.close(index);
                $.ajax({
                    url: '/ws/send/all',
                    type: 'post',
                    dataType: 'text',
                    data: {
                        "message": "群发测试"
                    },
                    success: function(data) {
                        var result = JSON.parse(data);
                        if (result.transactionStatus.success) {
                            //layer.msg("群发成功");
                        } else {
                            layer.msg(result.transactionStatus.replyText);
                        }
                    }
                });
            });
            break;
        };
    });
    //监听行工具事件
    table.on('tool(ws-table)',
    function(obj) {
        var data = obj.data;
        if (obj.event === 'closed') {
            layer.confirm('确定关闭此连接吗？',
            function(index) {
                layer.close(index);
                socket.close();
            });
        }
    });
    //监听搜索
    form.on('submit(ws-search)',
    function(data) {
        var field = data.field;
        //执行重载
        table.reload('ws-table', {
            where: field
        });
        return false;
    });
    exports('wsList',
    function() {});
});