var addParamCat=new Ext.menu.Item({
	text : '新增下级参数分类',
	iconCls : "add"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var n = item.parentMenu.contextNode;
			addParamCatWin(n);
		}
	}
})
var editParamCat=new Ext.menu.Item({
	text : '修改参数分类',
	iconCls : "edit"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var n = item.parentMenu.contextNode;
			editParamCatWin(n);
		}
	}
})
var delParamCat = new Ext.menu.Item({
	text: '删除'
	,iconCls : "delete"  
	,listeners : {
		click:function( item, e, eOpts )
		{
        	var node = item.parentMenu.contextNode;
        	var params={
        			id: node.id
        	}
			Ext.Msg.confirm('提示', '确定要删除吗?', function(btn) {
				if (btn != 'yes') {
					return;
				}
				UD.lib.doAjax(contextPath+"/system/paramCat/delParamCat",params,function(response, options)
						{
	    					var resJson = Ext.decode(response.responseText);
	    					Ext.Msg.alert("提示", resJson.msg,function() {
	    						if (resJson.success==true) {
	    							treePanel.getRootNode().reload();
								}
	    					});
						}
				);
				
			}); 
		}
	}	
});
var menus = new Ext.menu.Menu({
	items : [ 
		addParamCat,editParamCat,delParamCat
	]
});
//var treePanel = new Ext.tree.TreePanel({
//	border : false,
//	autoScroll : true,
//	loader : new Ext.tree.TreeLoader({
//		dataUrl : contextPath + '/system/paramCat/getTreeData'
//	}),
//	root : new Ext.tree.AsyncTreeNode(),
//	rootVisible : false,
//
//	region : "west",
//	split : true,
//	collapsible : true,
//	width : 190,
//	maxWidth : 300,
//
//	plugins : 'filterBar',
//	contextMenu : menus,
//	listeners : {
//		contextmenu : function(node, e) {
//			node.select();
//			
//			if (node.id=="root") {
//				editParamCat.hide();
//				delParamCat.hide();
//			} else {
//				editParamCat.show();
//				delParamCat.show();
//			}
//			var c = node.getOwnerTree().contextMenu;
//			c.contextNode = node; // contextNode是自定义的一个属性
//			c.showAt(e.getXY());
//
//		}
//	}
//});





//var oaStore = Ext.create('Ext.data.TreeStore', {
//	rootData: {
//        text: 'Ext JS',
//        expanded: true,
//        children: [
//            {
//                text: 'app',
//                children: [
//                    { leaf:true, text: 'Application.js' }
//                ]
//            },
//            {
//                text: 'button',
//                expanded: true,
//                children: [
//                    { leaf:true, text: 'Button.js' },
//                    { leaf:true, text: 'Cycle.js' },
//                    { leaf:true, text: 'Split.js' }
//                ]
//            },
//            {
//                text: 'container',
//                children: [
//                    { leaf:true, text: 'ButtonGroup.js' },
//                    { leaf:true, text: 'Container.js' },
//                    { leaf:true, text: 'Viewport.js' }
//                ]
//            },
//            {
//                text: 'core',
//                children: [
//                    {
//                        text: 'dom',
//                        children: [
//                            { leaf:true, text: 'Element.form.js' },
//                            { leaf:true, text: 'Element.static-more.js' }
//                        ]
//                    }
//                ]
//            },
//            {
//                text: 'dd',
//                children: [
//                    { leaf:true, text: 'DD.js' },
//                    { leaf:true, text: 'DDProxy.js' },
//                    { leaf:true, text: 'DDTarget.js' },
//                    { leaf:true, text: 'DragDrop.js' },
//                    { leaf:true, text: 'DragDropManager.js' },
//                    { leaf:true, text: 'DragSource.js' },
//                    { leaf:true, text: 'DragTracker.js' },
//                    { leaf:true, text: 'DragZone.js' },
//                    { leaf:true, text: 'DragTarget.js' },
//                    { leaf:true, text: 'DragZone.js' },
//                    { leaf:true, text: 'Registry.js' },
//                    { leaf:true, text: 'ScrollManager.js' },
//                    { leaf:true, text: 'StatusProxy.js' }
//                ]
//            },
//            {
//                text: 'core',
//                children: [
//                    { leaf:true, text: 'Element.alignment.js' },
//                    { leaf:true, text: 'Element.anim.js' },
//                    { leaf:true, text: 'Element.dd.js' },
//                    { leaf:true, text: 'Element.fx.js' },
//                    { leaf:true, text: 'Element.js' },
//                    { leaf:true, text: 'Element.position.js' },
//                    { leaf:true, text: 'Element.scroll.js' },
//                    { leaf:true, text: 'Element.style.js' },
//                    { leaf:true, text: 'Element.traversal.js' },
//                    { leaf:true, text: 'Helper.js' },
//                    { leaf:true, text: 'Query.js' }
//                ]
//            },
//            { leaf:true, text: 'Action.js' },
//            { leaf:true, text: 'Component.js' },
//            { leaf:true, text: 'Editor.js' },
//            { leaf:true, text: 'Img.js' },
//            { leaf:true, text: 'Layer.js' },
//            { leaf:true, text: 'LoadMask.js' },
//            { leaf:true, text: 'ProgressBar.js' },
//            { leaf:true, text: 'Shadow.js' },
//            { leaf:true, text: 'ShadowPool.js' },
//            { leaf:true, text: 'ZIndexManager.js' }
//        ]
//    }
//});
var treeStore = Ext.create('Ext.data.TreeStore', {
    // 根节点的参数是parentId
    nodeParam: 'parentId',
    // 根节点的参数值是0
    defaultRootId: 0,
//    fields: [{
//        name: 'className',
//        type: 'string'
//    }, {
//        name: 'text',
//        type: 'string'
//    }, {
//        name: 'iconCls',
//        type: 'string'
//    }],
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/paramCat/getTreeData'
    }
});

var treePanel= new Ext.tree.TreePanel(
{
	title:"参数分类",
	region : "west",
	split : true,
	collapsible : true,
	width : 270,
	maxWidth : 300
	
	,store: treeStore
	,rootVisible : false
	
	,contextMenu : menus
	,listeners : {
		//这个node的类型是Ext.data.Model
		itemcontextmenu: function (curr, node, item, index, e, eOpts )
		{
			e.preventDefault();//取消浏览器对此事件的默认操作，否则会弹出浏览器的系统菜单
			
			if (node.get("id")=="root") {
				editParamCat.hide();
				delParamCat.hide();
			} else {
				editParamCat.show();
				delParamCat.show();
			}
			var c = node.getOwnerTree().contextMenu;
			c.contextNode = node; // contextNode是自定义的一个属性
			c.showAt(e.getXY());			
		}
	}	
}
);

treePanel.on("itemclick", function( curr, node, item, index, e, eOpts )  {
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			fkParamCat : node.get("id")
		}
		,isClearLastParams:false
	}
	);
	
	
});
 

