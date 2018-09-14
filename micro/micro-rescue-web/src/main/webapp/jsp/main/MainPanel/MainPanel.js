var tabPanel = new Ext.TabPanel({
	activeTab : 0,
	border : true,
	items : [],
	region : "center",
	enableTabScroll : true,
	plugins : [ new Ext.ux.TabCloseMenu() ]
});
var tree = new Ext.tree.TreePanel({
	title:"菜单列表",
	border : false,
	loader : new Ext.tree.TreeLoader({
		dataUrl : contextPath + '/system/menu/getTreeDataByAccount'
	// dataUrl:contextPath+"/jsp/main/MainPanel/treeData.txt"
	}),
	root : new Ext.tree.AsyncTreeNode(), // 没有配置expanded,默认会展开root节点
	rootVisible : false,
	autoScroll : true,
	region : "west",
	split : true,
	collapsible : true,
	width : 190,
	maxWidth : 250,
	plugins : 'filterBar'
});
tree.on("click", function(node, event) {
	// 如果单击的是叶子节点
	if (node.attributes.is_folder == "F") {
		event.stopEvent();
		var id = "tab_" + node.attributes.id;
		var title = node.attributes.text;
		var url = node.attributes.url;
		addTab(id, title, url,true);
	}
	// 原先的代码
	// if (node && node.isLeaf()) {
	// event.stopEvent();
	// var id="tab_" + node.attributes.id;
	// var title=node.attributes.text;
	// var url=node.attributes.url;
	// addTab(id,title,url);
	// }
});
function addTab(id, title, url,closable) {
	var aTab = tabPanel.getComponent(id);
	if (aTab) {
		tabPanel.setActiveTab(aTab);
	} else {
		// var iframePanel=new Ext.ux.ManagedIframePanel({
		// border : false
		// ,id: id
		// ,title:title
		// ,defaultSrc:url
		//			
		// ,closable: true
		// ,group: this
		// });
		// var newTab = tabPanel.add(iframePanel);
		
		var htmlpanel=new JpkFrame.common.HtmlPanel(
			{
				xtype : 'htmlpanel',
				id : id,
				title : title,
				layout : 'fit',
				border : false,
				// loadMask:false,关闭进度条
				// loadMask:{} 启用默认的进度条,{}为设置
				// loadMask:{msg:'正在打开页面，请稍等....',msgCls:''}, //启用自定义配置
				loadMask : {},
				defaultSrc : url,
				closable : closable,
				group : this
			}		
		);
		var newTab=tabPanel.add(htmlpanel);
		tabPanel.setActiveTab(newTab);
	}

}

addTab("homepage", "首页", contextPath + '/bis/homePage/initPage',false);


var centerPanel = new Ext.Panel({
	border : false,
	region : "center",
	layout : "border",
	items : [ tree, tabPanel ]

});

Ext.onReady(function() {
	//首页初始化
//	UD.lib.doFormLoadData(zuhutj, contextPath+ '/system/main/getTenantsCount', function(action){});
//	UD.lib.doFormLoadData(cartj, contextPath+ '/system/main/getQyCarCount', function(action){});
//	UD.lib.doFormLoadData(cartj, contextPath+ '/system/main/getFsCarCount', function(action){});
//	UD.lib.doFormLoadData(cartj, contextPath+ '/system/main/getWyCarCount', function(action){});
//	UD.lib.doFormLoadData(devicetj, contextPath+ '/system/main/getDeviceCount', function(action){});
//	UD.lib.doFormLoadData(devicetj, contextPath+ '/system/main/getSimCount', function(action){});
	new Ext.Viewport({
		layout : "border",
		items : [ topPanel, centerPanel ]
	});
	
});
