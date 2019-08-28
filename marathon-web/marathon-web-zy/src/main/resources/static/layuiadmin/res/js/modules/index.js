/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;//这个分号的作用：避免多个js文件压缩在一起时引起的错误
layui.extend({//这个extend其实是可以不要的，只是为了让我们知道setter代表config.js模块
  setter: "config",
  admin: "res/js/modules/admin",
  view: "res/js/modules/view"
}).define(["setter", "admin"],//定义index.js模块，依赖于config.js和admin.js模块，因为admin.js模块依赖于view.js模块，所以这里没有引路view
function(a) {
  var e = layui.setter,
  i = layui.element,
  n = layui.admin,
  t = n.tabsPage,
  d = layui.view,
  l = function(a, d) {
    var l, b = r("#LAY_app_tabsheader>li"),
    y = a.replace(/(^http(s*):)|(\?[\s\S]*$)/g, "");
    if (b.each(function(e) {
      var i = r(this),
      n = i.attr("lay-id");
      n === a && (l = !0, t.index = e)
    }), d = d || "新标签页", e.pageTabs) l || (r(s).append(['<div class="layadmin-tabsbody-item layui-show">', '<iframe src="' + a + '" frameborder="0" class="layadmin-iframe"></iframe>', "</div>"].join("")), t.index = b.length, i.tabAdd(o, {
      title: "<span>" + d + "</span>",
      id: a,
      attr: y
    }));
    else {
      var u = n.tabsBody(n.tabsPage.index).find(".layadmin-iframe");
      u[0].contentWindow.location.href = a
    }
    i.tabChange(o, a),
    n.tabsBodyChange(t.index, {
      url: a,
      text: d
    })
  },
  s = "#LAY_app_body",
  o = "layadmin-layout-tabs",
  r = layui.$;//由于config.js依赖util.js，util.js依赖jquery.js，所以这里不需要引入jquery
  r(window);
  n.screen() < 2 && n.sideFlexible(),
  layui.config({
    base: e.base + "modules/"
  }),
  layui.each(e.extend,
  function(a, i) {
    var n = {};
    n[i] = "{/}" + e.base + "res/js/component/" + i,
    layui.extend(n)
  }),
  d().autoRender(),
  a("index", {
    openTabsPage: l
  })
});