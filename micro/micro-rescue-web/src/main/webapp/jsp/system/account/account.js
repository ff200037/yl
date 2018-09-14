function accountTypeRenderer(val)
{
	if (val==1) {
		return "系统账号";
	} 
	if (val==2) {
		return "普通账号";
	} 
}	
function accountStatusRenderer(val)
{
	if (val==1) {
		return "正常";
	} 
	if (val==2) {
		return "禁用";
	} 
}
var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/system/account/getListData",
	fields : [
		{name:'id'}
		,{name:'accountName'}
		,{name:'loginTime'}
		,{name:'lastLoginTime'}
		,{name:'accountType'}
		,{name:'accountStatus'}
		,{name:'createDate'}
		,{name:'remark'}
		,{name:'roleNames'}	
	]
});
var addbtn = UD.lib.createAddBtn(
	function() {
		iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/account/addAccountPage", 370, 380, "添加账号")
    }
);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/account/addAccountPage?id=" + record.get("id"),370, 380, "修改账号")
});
var delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/account/delAccount");
var grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title:"账号列表",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text:'账号名称',dataIndex:'accountName'}
		,{text:'备注',dataIndex:'remark'}
		,{text:'账号类型',dataIndex:'accountType',renderer:accountTypeRenderer}
		,{text:'账号状态',dataIndex:'accountStatus',renderer:accountStatusRenderer}
		,{text:'所属角色',dataIndex:'roleNames'}
//		,{text:'登录时间',dataIndex:'loginTime'}
//		,{text:'上次登录时间',dataIndex:'lastLoginTime'}
		,{text:'创建时间',dataIndex:'createDate'}
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
    ,tbar:[addbtn,modifyBtn,delbtn]
});

grid.on("rowclick",function(thegrid,rowIndex,e)
{		var record = this.getSelectionModel().getSelection()[0];
		//禁用添加角色按钮
		if (record.get("accountType")==1) {
			role_addbtn.setDisabled(true);
			delbtn.setDisabled(true);
		} else {
			role_addbtn.setDisabled(false);
			delbtn.setDisabled(false);

		}
		UD.lib.loadStoreData(
		{
			store:role_grid.getStore()
			,params:{
				accountId:record.get("id")
			}
			,isClearLastParams:false
		}
		);			
}
);

var topPanel= new Ext.Panel(
{
	border:false,
	region:"center",
	
	layout:"border",
	items:[searchForm,grid]
	
}
);

Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[topPanel,role_grid]
	});
	store.load();
});
