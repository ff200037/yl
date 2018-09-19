var editDic=new Ext.menu.Item({
	text : '修改字典分类',
	iconCls : "edit"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
			editDictionary(node)
		}
	} 	
})
var delDic = new Ext.menu.Item({
	text: '删除'
	,iconCls : "delete" 
	,listeners : {
		click:function( item, e, eOpts )
		{
	            	var node = item.parentMenu.contextNode;
	            	var params={
	            			id: node.get("id")
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
			    							treePanel.getStore().reload();
										}
			    					});
	    						}
	    				);
	    				
	    			}); 
		}
	} 	
});
var addDic=new Ext.menu.Item({
	text : '新增下级字典分类',
	iconCls : "add"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
			addDictionary(node)
		}
	} 	
})
var menus = new Ext.menu.Menu({
	items : [addDic,editDic,delDic]
});


var treeStore = Ext.create('Ext.data.TreeStore', {
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/dictionary/getTreeData'
    }
});

var treePanel= new Ext.tree.TreePanel(
{
    reserveScrollbar: true,
    useArrows: true,
//    multiSelect: true,多选
//    singleExpand: true,	
	
    columns:[{
    	xtype: 'treecolumn',
        text: '分类名称',
        dataIndex: 'text',
        width: 170
    },{
        text: '分类编码',
        width: 110,
        dataIndex: 'dictionaryCode'
    }],	
	
	region : "west",
	split : true,
	collapsible : true
	,width : 300
	,maxWidth : 400
	
	,store: treeStore
	,rootVisible : false

	
	
	,contextMenu : menus
	,listeners : {
		//这个node的类型是Ext.data.Model
		itemcontextmenu: function (curr, node, item, index, e, eOpts )
		{
			e.preventDefault();//取消浏览器对此事件的默认操作，否则会弹出浏览器的系统菜单
			
			if (node.get("id")=="root") {
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
}
);
treePanel.on("itemclick", function( curr, node, item, index, e, eOpts )  {
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			dictionaryCode : node.get("dictionaryCode")
		}
		,isClearLastParams:false
	}
	);
	
	
});
