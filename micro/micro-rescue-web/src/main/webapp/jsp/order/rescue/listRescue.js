// ============按钮=================
// 添加按钮
var addbtn = UD.lib.createAddBtn(
	function() {
		iframeWindow = UD.lib.showIframeWindow(contextPath + "/order/rescue/create", 900, 380, "创建订单")
    }
);

// 修改按钮
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/order/rescue/edit?id=" + record.get("id"),900, 380, "修改订单")
});

// 删除按钮 
var delbtn = UD.lib.createSingleDelBtn(contextPath+"/order/rescue/delete");
// ===============列表===================

// 获取列表数据
var store = UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/order/rescue/getPageData",
	actionMethods : "get",
	rootProperty : "list",
	totalProperty : "total",
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

// 列表展示
var grid = Ext.create('Ext.grid.Panel', {
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
		,{text:'订单结束时间',dataIndex:'endTime'}
		,{text:'订单大业务类型',dataIndex:'serviceType'}
    ]
    ,bbar:UD.lib.getPagebar(store) 
    ,region: "center"
    ,tbar:[addbtn,modifyBtn,delbtn] // 增加按钮
});


var topPanel= new Ext.Panel(
{
	border:false,
	region:"center",
	layout:"border",
	items:[searchForm, grid]
}
);

Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[topPanel]
	});
	store.load(); // 加载
});
