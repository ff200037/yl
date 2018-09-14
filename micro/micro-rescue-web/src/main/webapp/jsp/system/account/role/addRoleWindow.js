var role_hidden_id = UD.lib.createHidden("id");
var role_fkAccount = UD.lib.createHidden("fkAccount");
var role_accountName = UD.lib.createTextField({
	fieldLabel : '账号名称'
	,disabled:true
});
var roles = UD.lib.createComboBox({
    fieldLabel: '角色',
    valueField: 'id',
    displayField: 'roleName',
    name: 'fkRole',
    store: UD.lib.createSimpleJsonStore({
        fields: ["id", "roleName"],
        url: contextPath + "/system/role/getComboboxData",
        autoLoad: true
    }),
    allowBlank: false
});
var role_addForm = new Ext.FormPanel({
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
		{items: [role_fkAccount,role_hidden_id,role_accountName,roles]}
    ]
});
new Ext.Window({
	border:false,
	closeAction : 'hide',
	resizable : true,
	modal : true,
	constrainHeader : true,
	layout : 'fit',
	items : [role_addForm]
	
	,buttonAlign:"center"
	,buttons : [UD.lib.createSaveBtn(role_saveAction)]	
});
function role_saveAction() {
    var currentWindow = this.findParentByType("window"); 
    var basicForm = currentWindow.down("form").getForm();
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/accountRole/saveAccountRole',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
                currentWindow.hide();
                role_grid.getStore().reload();
            });
        })
    }
}
//-----------------------------------------GridPanel用到的按钮----------------------------
var role_addbtn = UD.lib.createAddBtn(
	function() {
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length==0) {
			Ext.Msg.alert("提示", "请选择要分配角色的账号");
			return;
		}
		if (selection.length>1) {
			Ext.Msg.alert("提示", "只能选择一个账号");
			return;
		}
		var record=selection[0];
		UD.lib.showWindow(role_addForm,"分配角色");
		
		role_accountName.setValue(record.get("accountName"))
		role_fkAccount.setValue(record.get("id"))
    }
);