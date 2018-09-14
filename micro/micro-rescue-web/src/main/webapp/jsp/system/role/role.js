var sm=new Ext.grid.CheckboxSelectionModel({singleSelect: true});
var cm = new Ext.grid.ColumnModel(
{
	columns:[new Ext.grid.RowNumberer(),sm
		,{header:'角色名称',dataIndex:'roleName'}
		,{header:'备注',dataIndex:'remark'}
	]
	,defaultSortable: true
}
);
var store = new Ext.data.JsonStore({
	root : 'info',
	totalProperty : 'count',
	url :contextPath+ "/system/role/getListData"
	,fields : new Ext.data.Record.create([
		{name:'id'}
		,{name:'roleName'}
		,{name:'remark'}
				
	])
});

var keyWord = UD.lib.createTextField({
	
});
var searchbtn=UD.lib.createSearchBtn(function()
	{
		grid.getStore().baseParams={
			keyWord:keyWord.getValue()
		};
		grid.loadData();
	}
);

var delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/role/delRole");
var grid = new Ext.grid.GridPanel({
	title:"角色列表",
	border : false
	,loadMask: {msg: '正在读取数据,请稍等...'}
	,viewConfig : {forceFit : true}
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
	,region: "west"
	,width: 470
	,tbar:[addbtn,"-",modifyBtn,"-",delbtn,"-","关键字：",keyWord,searchbtn]
	
});
var record;
grid.on("rowclick", function(thegrid, rowIndex, e) {
    record = this.getSelectionModel().getSelected();
    var params = {
        roleId: record.get("id")
    }
    rightCenterPanel.setDisabled(false); 
    
    
    //读取之前先全部反选
    UD.lib.doAjax(contextPath + "/system/role/getRolePermissionIds", params, function(response, options) {
    	permissionTreePanel.setTitle("角色权限（"+record.get("roleName")+"）");
        var resJson = Ext.decode(response.responseText);
        permissionTreePanel.loadCheckedNodes(resJson)
    }, "读取权限中，请稍等");
    
    UD.lib.doAjax(contextPath + "/system/role/getRoleMenuIds", params, function(response, options) {
    	menuTreePanel.setTitle("角色菜单（"+record.get("roleName")+"）");
        var resJson = Ext.decode(response.responseText);
        menuTreePanel.loadCheckedNodes(resJson)
    }, "读取菜单中，请稍等");
});



Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[grid,rightCenterPanel]
	});
	grid.loadData();
});
