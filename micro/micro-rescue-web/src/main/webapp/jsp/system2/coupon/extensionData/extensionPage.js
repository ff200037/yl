var hidden_id = UD.lib.createHidden("id");

var cpnName= UD.lib.createTextField({
	fieldLabel : '优惠券名称',
	name:'cpnName',
	disabled:true
	
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
	disabled:true
});

var cpnNum= UD.lib.createNumberField({
	fieldLabel : '发行数量',
	name:"cpnNum",
	disabled:true
	
});

var alrdyGetNum= UD.lib.createNumberField({
	fieldLabel : '已推广数量',
	name:"alrdyGetNum",
	disabled:true
	
});

var pbe = UD.lib.createComboBox({
	fieldLabel : '救援业务类型',
	valueField : 'id',
	displayField : 'name',
	name : 'pbe',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=p_be",
				autoLoad : true
			}),
	disabled:true
	
});

var custRange = UD.lib.createComboBox({
	fieldLabel : '优惠活动对象',
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

	var chk2 = new Ext.form.Checkbox({
		name:"chk2",
		inputvalue:"2",
		boxLabel:"微信公众号",
		checked:true
	});
	var chk1 = new Ext.form.Checkbox({
		name:"chk1",
		inputvalue:"1",
		boxLabel:"APP",
		checked:true
	});
	var spreadChannel = new Ext.form.CheckboxGroup({
		name:"spreadChannel",
		fieldLabel:"推广渠道",
		items:[chk2,chk1]
		,allowBlank : false
	});

var spreadMode = UD.lib.createComboBox({
	fieldLabel : '推广方式',
	valueField : 'id',
	displayField : 'name',
	name : 'spreadMode',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=spread_mode",
				autoLoad : true
			}),
	listeners:{
        "select" : function(){
       		var value = spreadMode.getValue()//getRawValue
       		
       		console.info("value: "+value);
       		
       		
       		if(value == "1"){
       			spreadNum.setValue('');
       			spreadNum.setReadOnly(true);
       		}else if(value == "2"){
       			
       			spreadNum.setReadOnly(false);
       		}
      	},
      	triggerAction : 'all'
	}
	,allowBlank : false
});

var spreadNum= UD.lib.createNumberField({
	fieldLabel : '推广数量',
	name:"spreadNum"
	
});

var addForm = new Ext.FormPanel({
	frame : true,
	border : false,
	
	items: [
		hidden_id,
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			items:[{items:cpnName},{items:cpnType},{items:cpnNum}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			items:[{items:alrdyGetNum},{items:pbe},{items:custRange}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			items:[{items:spreadMode},{items:spreadNum},{items:spreadChannel}]
		}
    ]

	,buttonAlign : "center"
	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});

function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath+ '/coupon/coupon/extensionCoupon',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
            	parent.iframeWindow.hide();
            	//parent.grid.getStore().reload();
            });
        })
    }	
}


Ext.onReady(function() {
    new Ext.Viewport({
        layout: "fit",
        items: [addForm]
    });
    if (jsonParams.id) {
    	UD.lib.doFormLoadData(addForm, contextPath + '/coupon/coupon/getById?id='+ jsonParams.id, function(action)
	    	{
	    		
	    	}		
    	);
	} else {
	
		
	}
    
});