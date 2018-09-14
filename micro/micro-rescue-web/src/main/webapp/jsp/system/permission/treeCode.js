var addPermissionFolder = new Ext.menu.Item({
    id: 'addPermissionFolder',
    text: '新增下级权限分类'
    ,iconCls: "add"
});
var editPermissionFolder = new Ext.menu.Item({
	id: 'editPermissionFolder',
	text: '修改权限分类'
		,iconCls : "edit"    	
});
var addPermission = new Ext.menu.Item({
    id: 'addPermission',
    text: '新增权限'
    ,iconCls: "add"
});
var editPermission = new Ext.menu.Item({
	id: 'editPermission',
	text: '修改权限'
	,iconCls : "edit"    	
});
var delPermission = new Ext.menu.Item({
	id: 'delPermission',
	text: '删除'
	,iconCls : "delete"    	
});
var menus = new Ext.menu.Menu({
    items: [addPermissionFolder,editPermissionFolder, addPermission,editPermission,delPermission],
    listeners: {
        itemclick: function(item) {
            switch (item.id) {
            case 'addPermissionFolder':
                var node = item.parentMenu.contextNode;
                var params = {
                		fkPid: node.id,
                		fkPidName: node.text
                };
                iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionFolderPage?" + Ext.urlEncode(params), 430,450, "添加权限分类") 
                break;
            case 'editPermissionFolder':
            	var node = item.parentMenu.contextNode;
            	var params = {
            			id: node.id
            	};
            	iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionFolderPage?" + Ext.urlEncode(params), 430,450, "修改权限分类")                
            	break;
                
            case 'addPermission':
                var node = item.parentMenu.contextNode;
    	        var params = {
    	        		fkPid: node.id,
    	        		fkPidName: node.text
    		        };
    	        iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionPage?" + Ext.urlEncode(params), 460,450, "添加权限") 
                break;
            case 'editPermission':
            	var node = item.parentMenu.contextNode;
            	var params = {
            			id: node.id
            	};
            	iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/permission/addPermissionPage?" + Ext.urlEncode(params), 460,450, "修改权限")                
            	break;    	        
            case 'delPermission':
            	var node = item.parentMenu.contextNode;
            	var params={
            			id: node.id
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
var treePanel = new Ext.tree.TreePanel({
    border: false,
    autoScroll: true,
    loader: new Ext.tree.TreeLoader({
        dataUrl: contextPath + '/system/permission/getTreeData'
    }),
    root: new Ext.tree.AsyncTreeNode(),
    rootVisible: false,

    region: "west",
    split: true,
    collapsible: true,
    width: 280,
    maxWidth: 300,
    contextMenu: menus,
    plugins: 'filterBar',
    listeners: {
        contextmenu: function(node, e) {
        	//删除权限
        	if (node.id=="root") {
        		delPermission.hide();
        	}else {
        		delPermission.show();
			}
        	
            if (node.attributes.is_folder == "T") {
                addPermissionFolder.show();
                addPermission.show();

                editPermission.hide();//隐藏编辑菜单按钮
                if (node.id=="root") {
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
            node.select();
            // getOwnerTree () 返回当前节点所在的树。
            var c = node.getOwnerTree().contextMenu;
            c.contextNode = node; // contextNode是自定义的一个属性
            c.showAt(e.getXY());

        }
    }
});
treePanel.on("click", function(node, event) {
		grid.getStore().baseParams={
			id:node.id
		};
		grid.loadData();
});

