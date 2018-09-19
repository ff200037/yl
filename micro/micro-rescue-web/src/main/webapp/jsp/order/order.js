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
// 订单状态文字
function orderSt(val)
{
   switch (val) {
	  case '1': return "<font>已派单</font>"; break;
	  case '2': return "<font>已接单</font>"; break;
	  case '3': return "<font>已取消</font>"; break;
	  case '4': return "<font>执行中</font>"; break;
	  case '5': return "<font>待支付</font>"; break;
	  case '6': return "<font>已完成</font>"; break;
	  default: return "<font></font>"; break;
		}
}
// 下单方式
var orderTy = function(val)
{
	if(1 == val){
		return "<font>电话下单2</font>";
	}
	if(2 == val){
		return "<font>公众号下单</font>";
	}

}

var serviceTy = function(val)
{
	switch (val) {
	  case '1': return "<font>救援</font>"; break;
	  case '2': return "<font>分时</font>"; break;
	  case '3': return "<font>网约</font>"; break;
	  case '4': return "<font>商城</font>"; break;
	  default: return "<font></font>"; break;
		}
}

var store = UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/order/getListData",
	fields : [
		 {name:'id'}
		,{name:'orderNo'}
		,{name:'orderStatus'}
		,{name:'fkCustomerId'}
		,{name:'orderType'}
		,{name:'createOrderTime'}
		,{name:'endTime'}
		,{name:'serviceType'}
		,{name:'creater'}
	]
});

var addbtn = UD.lib.createAddBtn(
	function() {
		iframeWindow = UD.lib.showIframeWindow(contextPath + "/order/addOrderPage", 370, 380, "添加订单")
    }
);

var modifyBtn = UD.lib.createModifyBtn(function(record) {
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/order/addOrderPage?id=" + record.get("id"),370, 380, "修改订单")
});

var delbtn= UD.lib.createSingleDelBtn(contextPath+"/order/del");
var grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title:"订单列表",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
   		,{text:'id',dataIndex:'id'}
		,{text:'订单编号',dataIndex:'orderNo'}
		//,{text:'创建人',dataIndex:'rescueOrder', renderer : function(v) { return v.id; }}
		,{text:'订单状态',dataIndex:'orderStatus', renderer : orderSt}
		,{text:'车主ID',dataIndex:'fkCustomerId'}
		,{text:'下单方式',dataIndex:'orderType', renderer : orderTy}
		,{text:'下单时间',dataIndex:'createOrderTime', renderer : function(v) { return v.substring(0, 19).replace("T"," "); }}
//				,{text:'登录时间',dataIndex:'loginTime',renderer:accountTypeRenderer}
//				,{text:'上次登录时间',dataIndex:'lastLoginTime'}
		,{text:'订单结束时间',dataIndex:'endTime'}
		,{text:'订单大业务类型',dataIndex:'serviceType', renderer : serviceTy}
		
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
