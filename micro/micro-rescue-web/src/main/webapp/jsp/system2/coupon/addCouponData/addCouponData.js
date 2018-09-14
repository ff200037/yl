
var cpnName= UD.lib.createTextField({
	fieldLabel : '优惠券名称',
	name:"cpnName"
	,allowBlank : false
});

var cpnType = UD.lib.createComboBox({
	fieldLabel : '优惠券类别',
	valueField : 'id',
	displayField : 'name',
	name : 'cpnType',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=coupon_type",
				autoLoad : true
			}),
	listeners:{
        "select" : function(){
       		var value = cpnType.getValue()//getRawValue
       		
       		console.info("value: "+value);
       		
       		if(value == "1"){
       			xj.hide();
       			zk.hide();
       			Ext.getCmp('xj').setValue('');
       			Ext.getCmp('zk').setValue('');
       		}else if(value == "2"){
       			Ext.getCmp('zk').setValue('');
       			xj.show();
       			zk.hide();
       		}else if(value == "3"){
       			Ext.getCmp('xj').setValue('');
       			xj.hide();
       			zk.show();
       		}
      	},
      	triggerAction : 'all'
	}		
	,allowBlank : false
});

var custRange = UD.lib.createComboBox({
	fieldLabel : '活动对象',
	valueField : 'id',
	displayField : 'name',
	name : 'custRange',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=cust_range",
				autoLoad : true
			})
	,allowBlank : false
});

var areaRange = UD.lib.createComboBox({
	fieldLabel : '活动区域',
	valueField : 'id',
	displayField : 'name',
	name : 'areaRange',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=area_range",
				autoLoad : true
			})
	,allowBlank : false
});

var mjMoney = UD.lib.createNumberField({
	fieldLabel : '满减金额',
	name : 'mjMoney',
	hidden : true
});
var eftLen = UD.lib.createNumberField({
	fieldLabel : '有效时长(天)',
	name : 'eftLen',
	id : 'eftLen',
	hidden : true
});

var cpnNum= UD.lib.createNumberField({
	fieldLabel : '发行数量',
	name:"cpnNum"
	,allowBlank : false
});

var limGetNum= UD.lib.createNumberField({
	fieldLabel : '每人限领',
	name:"limGetNum"
	,allowBlank : false
});

var useCondType = UD.lib.createComboBox({
	fieldLabel : '使用条件',
	valueField : 'id',
	displayField : 'name',
	name : 'useCondType',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=use_cond_type",
				autoLoad : true
	}),
	listeners:{
        "select" : function(){
       		var value = useCondType.getValue()//getRawValue
       		
       		console.info("value: "+value);
       		
       		
       		if(value == "0"){
       		
       			mjMoney.show();
       		}else if(value == "1"){
       			
       			mjMoney.hide();
       		}
      	},
      	triggerAction : 'all'
	}
	,allowBlank : false
});



var dateType = UD.lib.createComboBox({
	fieldLabel : '有效时间',
	valueField : 'id',
	displayField : 'name',
	name : 'dateType',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=date_type",
				autoLoad : true
	}),
	listeners:{
        "select" : function(){
       		var value = dateType.getValue()//getRawValue
       		
       		console.info("value: "+value);
       		
       		if(value == "1"){
       			eftDate.hide();
       			invDate.hide();
       			eftLen.hide();
       			Ext.getCmp('eftDate').setValue('');
       			Ext.getCmp('invDate').setValue('');
       			Ext.getCmp('eftLen').setValue('');
       		}else if(value == "2"){
       			eftDate.show();
       			invDate.show();
       			eftLen.hide();
       			Ext.getCmp('eftLen').setValue('');
       		}else if(value == "3"){
       			eftDate.hide();
       			invDate.hide();
       			eftLen.show();
       			Ext.getCmp('eftDate').setValue('');
       			Ext.getCmp('invDate').setValue('');
       		}
      	},
      	triggerAction : 'all'
	}
	,allowBlank : false
});

var eftDate = UD.lib.createDateField({
			fieldLabel : '开始时间',
			name : "eftDate",
			hiddenName : 'eftDate',
			format : "Y-m-d",
			id : 'eftDate',
			hidden : true
});

var invDate = UD.lib.createDateField({
			fieldLabel : '至',
			name : "invDate",
			hiddenName : 'invDate',
			format : "Y-m-d",
			id : 'invDate',
			hidden : true
});

var cpnMarks = UD.lib.createTextArea({
	fieldLabel : '使用说明',
	name : 'cpnMarks'
	,allowBlank : false
});

var xj = UD.lib.createNumberField({
	fieldLabel : '现金',
	id : 'xj',
	name : 'cpnAmt',
	hidden : true
	
});
var zk = UD.lib.createNumberField({
	fieldLabel : '折扣',
	id : 'zk',
	name : 'cpnAmt',
	hidden : true
	
});

var pBe = UD.lib.createComboBox({
	fieldLabel : '救援业务类型',
	valueField : 'id',
	displayField : 'name',
	name : 'pBe',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=p_be",
				autoLoad : true
			})
	,allowBlank : false
});

var addForm = new Ext.FormPanel({
	bodyPadding:5,
	
	items: [
		{
			layout: 'column',
		    defaults: {
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.3
		    },
			items: [
				{items: [cpnName]}
				, {items: [custRange]}
				, {items: [areaRange]}
				
			]
		},
		{
			layout: 'column',
		    defaults: {
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.3
		    },
			items: [
				
				{items: [cpnNum]}
				, {items: [limGetNum]}
				
			]
		},
		
		{
			layout: 'column',
		    defaults: {
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.3
		    },
			items: [
				{items: [cpnType]}
				, {items: [xj]}
				, {items: [zk]}
				
			]
		},
		{
			layout: 'column',
		    defaults: {
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.3
		    },
			items: [
				{items: [useCondType]}
				, {items: [mjMoney]}
				
				
			]
		},
		{
			layout: 'column',
		    defaults: {
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.3
		    },
			items: [
				{items: [dateType]}
				, {items: [eftDate]}
				, {items: [invDate]}
				, {items: [eftLen]}
			]
		},
		{
			layout: 'column',
		    defaults: {
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.3
		    },
			items: [
				{items: [pBe]}
				, {items: [cpnMarks]}
				
				
			]
		}
    ]

	,buttonAlign : "center"
	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});

function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
	if (basicForm.isValid()) {
   		UD.lib.doFormSubmit(basicForm, contextPath+ '/coupon/coupon/saveCoupon',
      	function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
            	parent.iframeWindow.hide();
            	parent.grid.getStore().reload();
            });
        })
    }	
}


Ext.onReady(function() {
    new Ext.Viewport({
        layout: "fit",
        items: [addForm]
    });
});