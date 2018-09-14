var role_store =UD.lib.createPagingJsonStore(
{
	url :contextPath+ "/system/accountRole/getListData"
	,fields : [
		{name:'id'}
		,{name:'accountName'}
		,{name:'roleName'}
		,{name:'remark'}
	]
});
var role_delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/accountRole/delAccountRole");
var role_grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: role_store,
    columnLines: true,
    forceFit: true,
    title:"已分配的角色",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text:'账号名称',dataIndex:'accountName'}
		,{text:'角色名称',dataIndex:'roleName'}
		,{text:'备注',dataIndex:'remark'}
    ]
    ,bbar:UD.lib.getPagebar(role_store)
	,region: "south"
	,height:200
	,tbar:[role_addbtn,role_delbtn]
});