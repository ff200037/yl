function isBuiltInRenderer(val)
{
	if (val=="T") {
		return "是";
	} else {
		return "否";
	}
}	
function isFolderRenderer(val)
{
    if (val=="T") {
        return "分类";
    } else {
        return "权限";
    }
}	
function permissionTypeRenderer(val)
{
	if (val=="page") {
		return "菜单权限";
	} else if (val=="notPage"){
		return "非菜单权限";
	}else{
		return ""
	}
}	
var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/system/permission/getListData",
	fields : [
		{name :'id'} 
		,{name :'parentPermissionName'}
		,{name :'permissionName'}
		,{name :'isFolder'}
		,{name :'permissionType'}
		,{name :'remark'}
		,{name :'appName'}
		,{name :'appWebpath'}
		,{name :'permissionPath'}
		,{name :'isBuiltIn'}	
	]
});



var grid=Ext.create('Ext.grid.Panel', {
    store: store,
    columnLines: true,
    forceFit: false,
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text: "上级权限分类名称",dataIndex: 'parentPermissionName' }
		,{text: "权限名称",dataIndex: 'permissionName' }
		,{text: "节点类型",dataIndex: 'isFolder',renderer:isFolderRenderer }
		,{text: "权限类型",dataIndex: 'permissionType',renderer:permissionTypeRenderer }
		,{text: "权限路径",dataIndex: 'permissionPath',width:220 }
		,{text: "是否系统权限",dataIndex: 'isBuiltIn',renderer:isBuiltInRenderer }
		,{text: "备注",dataIndex: 'remark' }
		,{text: "应用名称",dataIndex: 'appName' }
		,{text: "应用路径",dataIndex: 'appWebpath',width:240 }
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
});


Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[treePanel,grid]
	});
});
