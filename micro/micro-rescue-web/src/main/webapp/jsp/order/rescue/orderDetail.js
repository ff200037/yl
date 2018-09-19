var commonWidth = 200;
//属性
var orderNo = UD.lib.createTextField({
	fieldLabel:'订单编号'
	,name:'orderNo'
	,allowBlank : false
	,width:commonWidth
});
var orderStatus = UD.lib.createTextField({
	fieldLabel:'订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成'
	,name:'orderStatus'
	,allowBlank : false
	,width:commonWidth
});
var userName = UD.lib.createTextField({
	fieldLabel:'车主姓名'
	,name:'userName'
	,allowBlank : false
	,width:commonWidth
});
	#{fkUserId,jdbcType=BIGINT} ,
var userNumber = UD.lib.createTextField({
	fieldLabel:'车主电话'
	,name:'userNumber'
	,allowBlank : false
	,width:commonWidth
});
var belongedRegion = UD.lib.createTextField({
	fieldLabel:'所属区域'
	,name:'belongedRegion'
	,allowBlank : false
	,width:commonWidth
});
var orderType = UD.lib.createComboBox({
	fieldLabel : '下单方式:1.电话下单2.公众号下单',
	valueField : 'id',
	displayField : 'name',
	hiddenName : 'orderType',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	}),
	allowBlank : false,
	width : commonWidth
});
	#{fkCarId,jdbcType=BIGINT} ,
var licenseNo = UD.lib.createTextField({
	fieldLabel:'故障车牌号'
	,name:'licenseNo'
	,allowBlank : false
	,width:commonWidth
});
var carColor = UD.lib.createTextField({
	fieldLabel:'故障车颜色'
	,name:'carColor'
	,allowBlank : false
	,width:commonWidth
});
var rescueProject = UD.lib.createTextField({
	fieldLabel:'救援项目:1.拖车牵引,2.现场修理,3.派送燃油,4.电瓶搭电,5.更换轮胎,6.地库救援,7困境救援'
	,name:'rescueProject'
	,allowBlank : false
	,width:commonWidth
});
var rescueExplain = UD.lib.createTextField({
	fieldLabel:'救援说明'
	,name:'rescueExplain'
	,allowBlank : false
	,width:commonWidth
});
var carPosition = UD.lib.createTextField({
	fieldLabel:'故障车辆位置'
	,name:'carPosition'
	,allowBlank : false
	,width:commonWidth
});
var carLon = UD.lib.createTextField({
	fieldLabel:'故障车辆经度'
	,name:'carLon'
	,allowBlank : false
	,width:commonWidth
});
var carLat = UD.lib.createTextField({
	fieldLabel:'故障车辆纬度'
	,name:'carLat'
	,allowBlank : false
	,width:commonWidth
});
	#{fkRescuersId,jdbcType=BIGINT} ,
var rescuersName = UD.lib.createTextField({
	fieldLabel:'救援人员姓名'
	,name:'rescuersName'
	,allowBlank : false
	,width:commonWidth
});
var rescuersNumber = UD.lib.createTextField({
	fieldLabel:'救援人员电话'
	,name:'rescuersNumber'
	,allowBlank : false
	,width:commonWidth
});
var createOrderTime = UD.lib.createDateField({
			fieldLabel : '下单时间',
			name : "createOrderTime",
			hiddenName : 'createOrderTime',
			format : "Y-m-d",
			// readOnly : true,
			emptyText : '请选择日期',
			// value:new Date(),
			width : commonWidth
		})
var waitTime = UD.lib.createComboBox({
	fieldLabel : '等待时间',
	valueField : 'id',
	displayField : 'name',
	hiddenName : 'waitTime',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	}),
	allowBlank : false,
	width : commonWidth
});
var allTime = UD.lib.createComboBox({
	fieldLabel : '订单总时长',
	valueField : 'id',
	displayField : 'name',
	hiddenName : 'allTime',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	}),
	allowBlank : false,
	width : commonWidth
});
	var settlementAmount = UD.lib.createTextField({
		fieldLabel:'结算费用'
		,name:'settlementAmount'
		,allowBlank : false
		,width:commonWidth
	}); ,
	#{fkOrderEvaluate,jdbcType=BIGINT} ,
var creater = UD.lib.createTextField({
	fieldLabel:'创建人姓名'
	,name:'creater'
	,allowBlank : false
	,width:commonWidth
});
var createTime = UD.lib.createDateField({
			fieldLabel : '创建时间',
			name : "createTime",
			hiddenName : 'createTime',
			format : "Y-m-d",
			// readOnly : true,
			emptyText : '请选择日期',
			// value:new Date(),
			width : commonWidth
		})
var updater = UD.lib.createTextField({
	fieldLabel:'修改人姓名'
	,name:'updater'
	,allowBlank : false
	,width:commonWidth
});
var updateTime = UD.lib.createDateField({
			fieldLabel : '修改时间',
			name : "updateTime",
			hiddenName : 'updateTime',
			format : "Y-m-d",
			// readOnly : true,
			emptyText : '请选择日期',
			// value:new Date(),
			width : commonWidth
		})
// 属性end

var FieldSet = new Ext.form.FieldSet(
{
	title:"车辆基本信息"
	,labelAlign:"right"
	,labelWidth:90
	,items:[
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:orderNo}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:orderStatus}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:userName}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:fkUserId}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:userNumber}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:belongedRegion}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:orderType}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:fkCarId}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:licenseNo}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:carColor}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:rescueProject}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:rescueExplain}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:carPosition}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:carLon}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:carLat}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:fkRescuersId}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:rescuersName}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:rescuersNumber}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:createOrderTime}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:waitTime}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:allTime}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:settlementAmount}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:fkOrderEvaluate}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:creater}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:createTime}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:updater}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:updateTime}]
		}
	]
}
);

var addForm = new Ext.FormPanel({
	frame : true,
	border : false,
	autoHeight : true,
	autoWidth : true,
	labelAlign : "right",
	labelWidth : 110
	,items:[FieldSet]
	
//	,buttonAlign : "center"
//	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});


Ext.onReady(function() {
	new Ext.Viewport({
		layout : "fit",
		items : [ addForm ]
	});
	if (jsonParams.id) {
		UD.lib.doFormLoadData(addForm, contextPath + '/order/order/getById?id='+ jsonParams.id, function(action) {
				
			});
	} else {
	}

});