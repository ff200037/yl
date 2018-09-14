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
var store = UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/order/getListData",
	fields : [
		{name:'id'}
		,{name:'orderNo'}
		,{name:'orderNo'}
		,{name:'orderStatus'}
		,{name:'fkCustomerId'}
		,{name:'orderType'}
		,{name:'createOrderTime'}
		,{name:'endTime'}
		,{name:'serviceType'}
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
   		,{text:'id',dataIndex:'id'}
		,{text:'订单编号',dataIndex:'orderNo'}
		,{text:'订单状态',dataIndex:'orderStatus'}
		,{text:'车主ID',dataIndex:'fkCustomerId'}
		,{text:'下单方式',dataIndex:'orderType'}
		,{text:'下单时间',dataIndex:'createOrderTime'}
//				,{text:'登录时间',dataIndex:'loginTime',renderer:accountTypeRenderer}
//				,{text:'上次登录时间',dataIndex:'lastLoginTime'}
		,{text:'订单结束时间',dataIndex:'endTime'}
		,{text:'订单大业务类型',dataIndex:'serviceType'}
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
    ,tbar:[addbtn,modifyBtn,delbtn]
});


var topPanel= new Ext.Panel(
{
	border:false,
	region:"center",
	
	layout:"border",
	items:[searchForm, grid]// searchForm,
	
}
);

Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[topPanel]
	});
	store.load();
});
