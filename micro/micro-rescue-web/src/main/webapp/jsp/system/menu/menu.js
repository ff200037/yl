function isFolderRenderer(val)
{
    if (val=="T") {
        return "分类";
    } else {
        return "菜单";
    }
}	
function isBuiltInRenderer(val)
{
	if (val=="T") {
		return "是";
	} else {
		return "否";
	}
}
var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/system/menu/getListData",
	fields : [
		{name :'id'} 
		,{name :'parentMenuName'}
		,{name :'menuName'}
		,{name :'menuIndex'}
		,{name :'isFolder'}
		,{name :'permissionName'}
		,{name :'appName'}
		,{name :'appWebpath'}
		,{name :'permissionPath'}
		,{name :'isBuiltIn'}		
		,{name :'fkPermission'}	
	]
});
var grid=Ext.create('Ext.grid.Panel', {
    store: store,
    columnLines: true,
    forceFit: false,
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
	,{text: "上级菜单分类名称",dataIndex: 'parentMenuName' }
	,{text: "菜单名称",dataIndex: 'menuName' }
//	,{text: "菜单排序号",dataIndex: 'menuIndex' }
	,{text: "节点类型",dataIndex: 'isFolder',renderer:isFolderRenderer }
	,{text: "是否系统菜单",dataIndex: 'isBuiltIn',renderer:isBuiltInRenderer }
	,{text: "权限名称",dataIndex: 'permissionName' }
	,{text: "权限路径",dataIndex: 'permissionPath',width:220 }
	,{text: "应用名称",dataIndex: 'appName' }
	,{text: "应用路径",dataIndex: 'appWebpath',width:240 }	
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
});

grid.on("rowdblclick",function(thegrid,rowIndex,e)
{
	var record=this.getSelectionModel().getSelection()[0];
	if (record.get("isFolder")=="T") {
        var params = {
        		id: record.get("id")
        };
        iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/menu/addMenuFolderPage?" + Ext.urlEncode(params), 430,450, "修改菜单分类")		
	} else {
        var params = {
        		id: record.get("id")
        };
        iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/menu/addMenuPage?" + Ext.urlEncode(params), 430,450, "修改菜单")		
	}
}
);



Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[treePanel,grid]
	});
});
