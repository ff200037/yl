var editDic=new Ext.menu.Item({
	id : 'editDic',
	text : '修改字典分类',
	iconCls : "edit"
})
var delDic = new Ext.menu.Item({
	id: 'delDic',
	text: '删除'
	,iconCls : "delete"    	
});
var menus = new Ext.menu.Menu({
	items : [ 
		new Ext.menu.Item({
			id : 'addDic',
			text : '新增下级字典分类',
			iconCls : "add"
		})
		,editDic,delDic

	],
	listeners : {
		itemclick : function(item) {
			switch (item.id) {
				case 'addDic':
					var n = item.parentMenu.contextNode;
					addDictionary(n)
					break;
				case 'editDic':
					var n = item.parentMenu.contextNode;
					editDictionary(n)
					break;
	            case 'delDic':
	            	var node = item.parentMenu.contextNode;
	            	var params={
	            			id: node.id
	            	}
	    			Ext.Msg.confirm('提示', '确定要删除吗?', function(btn) {
	    				if (btn != 'yes') {
	    					return;
	    				}
	    				UD.lib.doAjax(contextPath+"/system/dictionary/delDictionary",params,function(response, options)
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
	            	break; 					
			}
		}
	}
});
//原来的treePanel
//var treePanel = new Ext.tree.TreePanel({
//	border : false,
//	autoScroll : true,
//	loader : new Ext.tree.TreeLoader({
//		dataUrl : contextPath + '/system/dictionary/getTreeData'
//	}),
//	root : new Ext.tree.AsyncTreeNode(),
//	rootVisible : false,
//
//	region : "west",
//	split : true,
//	collapsible : true,
//	width : 190,
//	maxWidth : 300,
//	contextMenu : menus,
//	plugins : 'filterBar',
//	listeners : {
//		contextmenu : function(node, e) {
//			node.select();
//			
//			if (node.id=="root") {
//				editDic.hide();
//				delDic.hide();
//			} else {
//				editDic.show();
//				delDic.show();
//			}
//			var c = node.getOwnerTree().contextMenu;
//			c.contextNode = node; // contextNode是自定义的一个属性
//			c.showAt(e.getXY());
//
//		}
//	}
//});


var treePanel = new Ext.ux.tree.TreeGrid({
	region : "west",
	split : true,
	collapsible : true,
	width : 400,
	maxWidth : 400,

    columns:[{
        header: '分类名称',
        dataIndex: 'text',
        width: 230
    },{
        header: '分类编码',
        width: 150,
        dataIndex: 'dictionaryCode'
    }],
    dataUrl: contextPath + '/system/dictionary/getTreeData',
//	plugins : 'filterBar',
	
	contextMenu : menus,
	listeners : {
	contextmenu : function(node, e) {
		node.select();
		
		if (node.id=="root") {
			editDic.hide();
			delDic.hide();
		} else {
			editDic.show();
			delDic.show();
		}
		var c = node.getOwnerTree().contextMenu;
		c.contextNode = node; // contextNode是自定义的一个属性
		c.showAt(e.getXY());

	}
}		
	
});



treePanel.on("click", function(node, event) {
	grid.getStore().baseParams = {
		dictionaryCode : node.attributes.dictionaryCode
	};
	grid.loadData();
});