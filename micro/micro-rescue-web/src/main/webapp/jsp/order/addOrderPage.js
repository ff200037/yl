var hidden_id = UD.lib.createHidden("id");
var orderNo = UD.lib.createTextField({
	fieldLabel : '订单编号',
	name : 'orderNo',
	allowBlank : false
});

var orderStatus = UD.lib.createComboBox({
	fieldLabel : '订单状态',
	valueField : 'id',
	displayField : 'name',
	name : 'orderStatus',
	store : new Ext.data.SimpleStore({
		data : [ [ '1', '已派单' ], [ '2', '已接单' ], [ '3', '已取消' ],
				[ '4', '执行中' ], [ '5', '待支付' ], [ '6', '已完成' ] ],
		fields : [ "id", "name" ]
	}),
	allowBlank : false
});

var fkCustomerId = UD.lib.createTextField({
	fieldLabel : '车主ID',
	name : 'fkCustomerId'
});

var orderType = UD.lib.createComboBox({
	fieldLabel : '下单方式',
	valueField : 'id',
	displayField : 'name',
	value : "2",
	name : 'orderType',
	store : new Ext.data.SimpleStore({
		data : [ [ '1', '电话下单' ], [ '2', '公众号下单' ] ],
		fields : [ "id", "name" ]
	}),
	allowBlank : false,
	listeners : {
		select : function(combobx, record, rowIndex) {
			if (this.getValue() == "1") {
				accountPassword.setDisabled(false);
			}
			if (this.getValue() == "2") {
				accountPassword.setDisabled(true);
			}
		}
	}
});
//下单时间
var createOrderTime = UD.lib.createDateField({
	name : "createOrderTime",
	fieldLabel : "下单时间",
	width : 200,
	allowBlank : false, // 不允许为空
	emptyText : '下单时间',
	format : "Y-m-d H:i:s"//指定日期格式,Y 表示四位数的年，m 表示月，d 表示日
	
//默认为当前日期
});

 
var serviceType = UD.lib.createComboBox({
	name : 'serviceType',
	fieldLabel : '订单大业务类型',
	valueField : 'id',
	displayField : 'name',
	value : "1",
	
	store : new Ext.data.SimpleStore({
		data : [ [ '1', '救援' ], [ '2', '分时' ], 
		         [ '3', '网约' ], [ '4', '商城' ]],
		fields : [ "id", "name" ]
	}),
	allowBlank : false,
	 
});

var creater = UD.lib.createTextField({
	fieldLabel : '创建人',
	name : 'creater',
	allowBlank : false
}); 

var addForm = new Ext.FormPanel({
	bodyPadding : 5,
	layout : 'column',
	fieldDefaults : {
		labelAlign : 'right'
	//        ,labelWidth: 100
	},
	defaults : {
		layout : 'form',
		xtype : 'container',
		columnWidth : 1
	},
	items : [ {
		items : [hidden_id, orderNo, orderStatus, fkCustomerId, orderType,
		          createOrderTime, serviceType, creater ]
	} ]
	/*id;
     orderNo; orderStatus; fkCustomerId; orderType; createOrderTime;

	endTime; serviceType; creater; createTime; updater; updateTime;*/

	,
	buttonAlign : "center",
	buttons : [ UD.lib.createSaveBtn(saveAction) ]
});

function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm();
	if (basicForm.isValid()) {                        
		UD.lib.doFormSubmit(basicForm, contextPath + '/order/save',
				function(action) {
					Ext.MessageBox.alert('提示', action.result.msg, function() {
						parent.iframeWindow.hide();
						parent.grid.getStore().load();
					});
				})
	}
}


function renderDate(format) {
    return function(v) {
        var JsonDateValue;
        if (Ext.isEmpty(v))
            return '';
        else if (Ext.isEmpty(v.time))
            JsonDateValue = new Date(v);
        else
            JsonDateValue = new Date(v.time);
        return JsonDateValue.format('Y-m-d H:i:s');
    };
};
	    
Ext.onReady(function() {
	new Ext.Viewport({
		layout : "fit",
		items : [ addForm ]
	});
	
  if (jsonParams.id) {
	    UD.lib.doFormLoadData(addForm, contextPath+ '/order/getById?id='+jsonParams.id, function(action)
		    {
	           var  createOrderTime1 =	action['result']['data']['createOrderTime'];
	           /* 2018-09-12T11:40:23.000+0000*/
	           var s = createOrderTime1.substring(0, 19).replace("T"," ");
	           createOrderTime.setValue(s);
		    }	
	    );
	} else {
		hidden_id.setValue(jsonParams.id);
	}
	
  
});