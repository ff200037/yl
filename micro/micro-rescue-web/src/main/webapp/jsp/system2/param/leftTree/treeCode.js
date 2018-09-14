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
	    							treePanel.getStore().reload();
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
var treeStore = Ext.create('Ext.data.TreeStore', {
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
 

