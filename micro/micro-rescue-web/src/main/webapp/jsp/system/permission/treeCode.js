var addPermissionFolder = new Ext.menu.Item({
    text: '新增下级权限分类'
    ,iconCls: "add"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		fkPid: node.get("id"),
            		fkPidName: node.get("text")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionFolderPage?" + Ext.urlEncode(params), 430,450, "添加权限分类")             

		}
	}     
});
var editPermissionFolder = new Ext.menu.Item({
	text: '修改权限分类'
		,iconCls : "edit"    
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		id: node.get("id")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionFolderPage?" + Ext.urlEncode(params), 430,450, "修改权限分类")           
		}
	} 		
});
var addPermission = new Ext.menu.Item({
    text: '新增权限'
    ,iconCls: "add"
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		fkPid: node.get("id"),
            		fkPidName: node.get("text")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionPage?" + Ext.urlEncode(params), 460,450, "添加权限") 
		}
	}     
});
var editPermission = new Ext.menu.Item({
	text: '修改权限'
	,iconCls : "edit"    
	,listeners : {
		click:function( item, e, eOpts )
		{
			var node = item.parentMenu.contextNode;
            var params = {
            		id: node.get("id")
            };
            iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionPage?" + Ext.urlEncode(params), 460,450, "修改权限") 
		}
	} 	
});
var delPermission = new Ext.menu.Item({
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
    				UD.lib.doAjax(contextPath+"/system/permission/delPermission",params,function(response, options)
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
    items: [addPermissionFolder,editPermissionFolder, addPermission,editPermission,delPermission]
});

var treeStore = Ext.create('Ext.data.TreeStore', {
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/permission/getTreeData?expanded=0'
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
        		delPermission.hide();
        	}else {
        		delPermission.show();
			}        	
        	
            if (node.get("is_folder") == "T") {
                addPermissionFolder.show();
                addPermission.show();

                editPermission.hide();//隐藏编辑菜单按钮
                if (node.get("id")=="root") {
                	editPermissionFolder.hide();//隐藏编辑菜单分类按钮
				} else {
					 editPermissionFolder.show();//显示编辑菜单分类按钮
				}  				
               
            } else {
                addPermissionFolder.hide();
                addPermission.hide();
                
                editPermission.show();//显示编辑菜单按钮
                editPermissionFolder.hide();//隐藏编辑菜单分类按钮 
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


