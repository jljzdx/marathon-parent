;
layui.extend({
    formSelects: 'res/js/component/formSelects-v4',
}).define(['formSelects', 'form'],
function(exports) {
    var $ = layui.$,
    formSelects = layui.formSelects;
    $("input:first").focus();
    formSelects.data('roleSelect', 'server', {
        url: '/public/role/inquiry/select'
    });
    exports('userAdd',
    function() {});
});