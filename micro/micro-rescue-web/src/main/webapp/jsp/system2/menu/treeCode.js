var addMenuFolder = new Ext.menu.Item({
    text: '新增下级菜单分类'
    ,iconCls : "add"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		fkPid: node.get("id"),
            		fkPidName: node.get("text")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/menu/addMenuFolderPage?" + Ext.urlEncode(params), 430,450, "添加菜单分类")
		}
	}    
});
var editMenuFolder = new Ext.menu.Item({
	text: '修改菜单分类'
	,iconCls : "edit"    	
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		id: node.get("id")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/menu/addMenuFolderPage?" + Ext.urlEncode(params), 430,450, "修改菜单分类")
		}
	} 	
});
var addMenu = new Ext.menu.Item({
    text: '新增菜单'
    ,iconCls : "add"    
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		fkPid: node.get("id"),
            		fkPidName: node.get("text")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/menu/addMenuPage?" + Ext.urlEncode(params), 430,450, "添加菜单") 
		}
	}     
});
var editMenu = new Ext.menu.Item({
	text: '修改菜单'
	,iconCls : "edit"    
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		id: node.get("id")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/menu/addMenuPage?" + Ext.urlEncode(params), 430,450, "修改菜单")
		}
	}	
});
var delMenu = new Ext.menu.Item({
	text: '删除'
	,iconCls : "delete"    
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		id: node.get("id")
            };
     			Ext.Msg.confirm('提示', '确定要删除吗?', function(btn) {
    				if (btn != 'yes') {
    					return;
    				}
    				UD.lib.doAjax(contextPath+"/system/menu/delMenu",params,function(response, options)
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
    items: [addMenuFolder,editMenuFolder, addMenu,editMenu,delMenu]
});
var treeStore = Ext.create('Ext.data.TreeStore', {
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/menu/getTreeData'
    }
});

var treePanel= new Ext.tree.TreePanel(
{
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
			
       		//删除菜单
        	if (node.get("id")=="root") {
        		delMenu.hide();
        	}else {
        		delMenu.show();
			}        	
        	
            if (node.get("is_folder") == "T") {
                addMenuFolder.show();
                addMenu.show();

                editMenu.hide();//隐藏编辑菜单按钮
                if (node.get("id")=="root") {
                	editMenuFolder.hide();//隐藏编辑菜单分类按钮
				} else {
					 editMenuFolder.show();//显示编辑菜单分类按钮
				}
               
            } else {
                addMenuFolder.hide();
                addMenu.hide();
                
                
                editMenu.show();//显示编辑菜单按钮
                editMenuFolder.hide();//隐藏编辑菜单分类按钮
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
			id : node.get("id")
		}
		,isClearLastParams:false
	}
	);
	
	
});
