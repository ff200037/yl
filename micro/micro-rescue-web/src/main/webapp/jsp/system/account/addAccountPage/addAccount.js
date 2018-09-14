var hidden_id = UD.lib.createHidden("id");
var accountName = UD.lib.createTextField({
	fieldLabel : '账号名称',
	name : 'accountName',
	allowBlank : false
});

var accountStatus = UD.lib.createComboBox({
	fieldLabel : '账号状态',
	valueField : 'id',
	displayField : 'name',
	name : 'accountStatus',
	store : new Ext.data.SimpleStore({
		data : [ [ '1', '正常' ], [ '2', '禁用' ] ],
		fields : [ "id", "name" ]
	}),
	allowBlank : false
});
var remark = UD.lib.createTextField({
	fieldLabel : '备注',
	name : 'remark'
});
var isChangePassword = UD.lib.createComboBox({
	fieldLabel : '是否要修改密码',
	valueField : 'id',
	displayField : 'name',
	value : "2",
	store : new Ext.data.SimpleStore({
		data : [ [ '1', '是' ], [ '2', '否' ] ],
		fields : [ "id", "name" ]
	}),
	allowBlank : false
	, listeners :
	{
	   select : function (combobx,record,rowIndex)
	   {
	      if (this.getValue()=="1")
	      {
	    	  accountPassword.setDisabled(false);
	      }
	      if (this.getValue()=="2")
	      {
	    	  accountPassword.setDisabled(true);
	      }
	   }
	}	
});
var accountPassword = UD.lib.createTextField({
	fieldLabel : '新密码',
	name : 'accountPassword',
	allowBlank : false
});

var addForm = new Ext.FormPanel({
	bodyPadding:5,
	layout: 'column',
 	fieldDefaults: {
 		labelAlign: 'right'
//        ,labelWidth: 100
    },	
    defaults: {
        layout: 'form'
        ,xtype: 'container'
        ,columnWidth:1
    },
	items: [
		{items: [ hidden_id, accountName, accountStatus, remark,isChangePassword, accountPassword]}
    ]

	,buttonAlign : "center"
	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});

function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm();
	if (basicForm.isValid()) {
		UD.lib.doFormSubmit(basicForm, contextPath+ '/system/account/saveAccount',
				function(action) {
					Ext.MessageBox.alert('提示', action.result.msg, function() {
						parent.iframeWindow.hide();
						parent.grid.getStore().load();
					});
				})
	}
}

Ext.onReady(function() {
	new Ext.Viewport({
		layout : "fit",
		items : [ addForm ]
	});
	if (jsonParams.id) {
		UD.lib.doFormLoadData(addForm, contextPath + '/system/account/getById?id='+ jsonParams.id, function(action) {
			accountName.setDisabled(true);
			
			isChangePassword.setDisabled(false);
			accountPassword.setDisabled(true);
		});
	} else {
		isChangePassword.setDisabled(true);
		accountPassword.setDisabled(true);
	}

});