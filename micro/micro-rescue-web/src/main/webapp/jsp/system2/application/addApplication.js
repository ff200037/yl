var hidden_id = UD.lib.createHidden("id");
var applicationName = UD.lib.createTextField({
	fieldLabel : '应用名称',
	name : 'appName',
	allowBlank : false
});
var applicationCode = UD.lib.createTextField({
	fieldLabel : '应用编码',
	name : 'appCode',
	allowBlank : false
});
var applicationWebpath = UD.lib.createTextField({
	fieldLabel : '应用路径',
	name : 'appWebpath',
	allowBlank : false
});
var remark = UD.lib.createTextField({
	fieldLabel : '应用描述',
	name : 'remark'
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
		{items: [hidden_id,applicationName,applicationCode,applicationWebpath,remark]}
    ]
});

new Ext.Window({
	border:false,
	closeAction : 'hide',
	resizable : true,
	modal : true,
	constrainHeader : true,
	layout : 'fit',
	items : [addForm]
	
	,buttonAlign:"center"
	,buttons : [UD.lib.createSaveBtn(saveAction)]	
	,width:530
});
function saveAction() {
    var currentWindow = this.findParentByType("window"); // 获取所属窗口
    var basicForm = currentWindow.down("form").getForm(); // 获取窗口里唯一的formpanel
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/application/saveApplication',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
                currentWindow.hide();
                grid.store.reload();
            });
        })
    }
}
//-----------------------------------------GridPanel用到的按钮----------------------------
var addbtn = UD.lib.createAddBtn(
		function() {
			UD.lib.showWindow(addForm,"添加应用");
	    }
	);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	setData(record);
}
);
function setData(rec)
{
	UD.lib.showWindow(addForm,"修改应用");
//---------------------------------------------------------------------
	hidden_id.setValue(rec.get("id"));
	applicationName.setValue(rec.get("app_name"));
	applicationCode.setValue(rec.get("app_code"));
	applicationWebpath.setValue(rec.get("app_webpath"));
	remark.setValue(rec.get("remark"));
	
}
