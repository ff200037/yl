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
var sm=new Ext.grid.CheckboxSelectionModel({singleSelect: false});
var cm = new Ext.grid.ColumnModel(
{
	columns:[new Ext.grid.RowNumberer()
	,{header: "上级权限分类名称",dataIndex: 'parentPermissionName' }
	,{header: "权限名称",dataIndex: 'permissionName' }
	,{header: "节点类型",dataIndex: 'isFolder',renderer:isFolderRenderer }
	,{header: "权限类型",dataIndex: 'permissionType',renderer:permissionTypeRenderer }
	,{header: "权限路径",dataIndex: 'permissionPath',width:220 }
	,{header: "是否系统权限",dataIndex: 'isBuiltIn',renderer:isBuiltInRenderer }
	,{header: "备注",dataIndex: 'remark' }
	,{header: "应用名称",dataIndex: 'appName' }
	,{header: "应用路径",dataIndex: 'appWebpath',width:240 }
	
	]
	,defaultSortable: true
}
);
var store = new Ext.data.JsonStore({
	root : 'info',
	totalProperty : 'count',
	url :contextPath+ "/system/permission/getListData"
	,fields : new Ext.data.Record.create([
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
				
	])
});
var grid = new Ext.grid.GridPanel({
	border : false
	,loadMask: {msg: '正在读取数据,请稍等...'}
//	,viewConfig : {forceFit : true}
	,autoScroll:true
	,cm:cm
	,sm:sm
	,store: store
	,bbar:getPageTool(store)
	,loadData:function(){
		var thePageSize=this.getBottomToolbar().pageSize;
		this.getStore().load(
		{
			params : {start : 0,limit : thePageSize}
		});	
	}
	,region: "center"
	
	
});
grid.on("rowdblclick",function(thegrid,rowIndex,e)
{
	setData(this.getSelectionModel().getSelected());
}
);



Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[treePanel,grid]
	});
});
