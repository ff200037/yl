function cpnDetails(id)
{	
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/coupon/coupon/couponDetail?id="+id,1050, 460, "查看优惠券")
}

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

function isEnableRenderer4(val)
{
	if (val=="0") {
		return "停用";
	} else if(val=="1"){
		return "启用";
	} else{
		return "草稿";
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
	  	iframeWindow = UD.lib.showIframeWindow(contextPath + "/coupon/coupon/addCouponPage", 1000, 400, "添加优惠券")
	}
);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	    iframeWindow = UD.lib.showIframeWindow(contextPath + "/coupon/coupon/updateCouponPage?id=" + record.get("id"), 1000, 400, "修改优惠券")
    }
);
var extensionBtn = UD.lib.createGridCustomBtn('推广','edit',function(record) {
		iframeWindow = UD.lib.showIframeWindow(contextPath + "/coupon/coupon/extensionPage?id=" + record.get("id"), 1000, 200, "推广")
	}
);
var delbtn=UD.lib.createSingleDelBtn(contextPath+"/coupon/coupon/delCouponData");

//启用
var couponUse = UD.lib.createGridCustomBtn('启用','edit',function (record){
	Ext.Msg.confirm('提示', '确定要启用吗?', function(btn) {
		if (btn != 'yes') {
			return;
		}
		var params={
			id:record.get('id')
	    }      
		
		UD.lib.doAjax(contextPath+ "/coupon/coupon/couponUse",params,function(response, options)
				{
					var resJson=Ext.decode(response.responseText);
					Ext.Msg.alert("提示", resJson.msg,function()
					{
						if (resJson.success==true) {
							store.load();
						}
					}
					);
				}
			); 
	});
   });

//停用
var blockUp = UD.lib.createGridCustomBtn('停用','edit',function (record){
	Ext.Msg.confirm('提示', '确定要停用吗?', function(btn) {
		if (btn != 'yes') {
			return;
		}
		var params={
				id:record.get('id')
	    	}      
		
		UD.lib.doAjax(contextPath+ "/coupon/coupon/blockUp",params,function(response, options)
				{
					var resJson=Ext.decode(response.responseText);
					Ext.Msg.alert("提示", resJson.msg,function()
					{
						if (resJson.success==true) {
							store.load();
						}
					}
					);
				}
			); 
	});
   });



/*var supplybtn = UD.lib.createGridCustomBtn("通电","add",function(record) {
	Ext.Msg.confirm('提示', '确定要通电吗?', function(btn) {
		if (btn != 'yes') {
			return;
		}
		var licenseNo=record.get("licenseNo");
		var params={
				licenseNo:licenseNo
				,order:'11'
	    	}                 
		UD.lib.doAjax(contextPath+ "/bis/carRemote/sendOrder",params,function(response, options)
				{
					var resJson=Ext.decode(response.responseText);
					Ext.Msg.alert("提示", resJson.retMsg,function()
					{
						if (resJson.success==true) {
							grid.loadData();
						}
					}
					);
				}
			); 
	});
});*/







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
		,{text:'启用状态',dataIndex:'cpnStatus',renderer:isEnableRenderer4,width:80}
		,{text:'发放/领取总量',dataIndex:'alrdyGetNum'}
		,{header:'详情',dataIndex:'id',renderer:function (data, metadata, record, rowIndex, columnIndex, store) {
			  var couponId = store.getAt(rowIndex).get('id');
			  return "<a href='javascript:void(0)'  onclick='cpnDetails(" + couponId +")'>详情</a>"
	          
			  //return '<a href=" javascript:cpnDetails(0) ">详情</a>';
	          
	       /*  var link = "<a href ='javascript:carDevices(\"{0}\")' >{1}</a>";
			return String.format(link, record.data.licenseNo,"查看");*/
		
		}
		 }
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
    ,tbar:[addbtn,modifyBtn,delbtn,extensionBtn,couponUse,blockUp]
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
	store.load();
});

