function isEnableRenderer1(val)
{
	if (val=="1") {
		return "免费券";
	} else if(val=="2"){
		return "现金券";
	} else{
		return "折扣券";
	}
}

function isEnableRenderer2(val)
{
	if (val=="0") {
		return "海南省";
	} else if(val=="1"){
		return "海口市";
	} else if(val=="2"){
		return "三亚市";
	}
}

function isEnableRenderer3(val)
{
	if (val=="0") {
		return "所有用户";
	} else if(val=="1"){
		return "普通用户";
	} else{
		return "VIP用户";
	}
}

var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/coupon/coupon/getCouponData",
	fields : [
		{name:'id'}
		,{name:'cpnName'}
		,{name:'cpnType'}
		,{name:'cpnMarks'}
		,{name:'cpnNum'}
		,{name:'areaRange'}
		,{name:'custRange'}
		,{name:'createTime'}
		,{name:'cpnStatus'}
		,{name:'alrdyGetNum'}
		
	]
});
var addbtn = UD.lib.createAddBtn(function() {
	  	iframeWindow = UD.lib.showIframeWindow(contextPath + "/coupon/coupon/addCouponPage", 1000, 500, "添加优惠券")
	}
);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	    iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/paramData/addParamDataPage?id=" + record.get("id"), 500, 370, "修改参数")
    }
);
var extensionBtn = UD.lib.createGridCustomBtn('推广','edit',function(record) {
		iframeWindow = UD.lib.showIframeWindow(contextPath + "/coupon/coupon/extensionPage?id=" + record.get("id"), 1000, 500, "推广")
	}
);
var delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/paramData/delParamData");
var grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title:"参数列表",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text:'优惠券名称',dataIndex:'cpnName'}
		,{text:'优惠类型',dataIndex:'cpnType',renderer:isEnableRenderer1,width:80}
		,{text:'优惠券说明',dataIndex:'cpnMarks'}
		,{text:'发行数量',dataIndex:'cpnNum'}
		,{text:'优惠区域',dataIndex:'areaRange',renderer:isEnableRenderer2,width:80}
		,{text:'优惠对象',dataIndex:'custRange',renderer:isEnableRenderer3,width:80}
		,{text:'创建时间',dataIndex:'createTime'}
		,{text:'启用状态',dataIndex:'cpnStatus'}
		,{text:'发放/领取总量',dataIndex:'alrdyGetNum'}
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
    ,tbar:[addbtn,modifyBtn,delbtn,extensionBtn]
});	
var rightCenterPanel= new Ext.Panel(
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
		items:[rightCenterPanel]
	});
});

