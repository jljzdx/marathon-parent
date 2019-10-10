;
layui.extend({
    formSelects: 'res/js/component/formSelects-v4',
}).define(['formSelects', 'form'],
function(exports) {
    var form = layui.form,
    formSelects = layui.formSelects;
    formSelects.data('roleSelect', 'server', {
        url: '/public/role/inquiry/select'
    });
    //formSelects.render();
    exports('userEdit',
    function() {});
});