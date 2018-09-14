var id = UD.lib.createHidden("id");
var fkPid = UD.lib.createHidden("fkPid");
var commonWidth = 300;
var fkPidName = UD.lib.createTwinTriggerField({
	fieldLabel : "上级权限分类名称",
	onTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/permission/permissionFolderPage", 250, 350, "选择上级权限分类")
	},
    onTrigger1Click : function(){
    	this.reset();
    	fkPid.reset();
    },	
	width : commonWidth,
	allowBlank : false
	,name:"fkPidName"
});
window.setPermissionFolder = function(id, text) {
	fkPid.setValue(id);
	fkPidName.setValue(text);
	iframeWindow.hide();
}
var permissionName = UD.lib.createTextField({
	fieldLabel : "权限名称",
	name : "permissionName",
	allowBlank : false,
	width : commonWidth
})
var permissionPath = UD.lib.createTextField({
	fieldLabel : "权限路径",
	name : "permissionPath",
	allowBlank : false,
	width : commonWidth
});
var application = UD.lib.createComboBox({
	fieldLabel : '应用名称',
	valueField : 'id',
	displayField : 'app_name',
	hiddenName : 'fkApplication',
	store : new Ext.data.JsonStore({
		fields : [ "id", "app_name" ],
		url : contextPath + "/system/application/getAppListData",
		autoLoad : true
	}),
	allowBlank : false,
	width : commonWidth
});
var permissionType = UD.lib.createComboBox({
	fieldLabel : '权限类型',
	valueField : 'id',
	displayField : 'name',
	hiddenName : 'permissionType',
	value:"notPage",
	store : new Ext.data.SimpleStore({
		data : [ [ 'page', '菜单权限' ], [ 'notPage', '非菜单权限' ] ],
		fields : [ "id", "name" ]
	}),
	allowBlank : false,
	width : commonWidth
});

var remark = UD.lib.createTextField({
	fieldLabel : "备注",
	name : "remark",
	width : commonWidth
})
var addForm = new Ext.FormPanel({
	frame : true,
	border : false,
	autoHeight : true,
	labelAlign : "right",
	labelWidth : 110,
	items : [id, fkPid, fkPidName, permissionName,permissionPath, permissionType, application,
			 remark ],
	buttonAlign : "center",
	buttons : [ UD.lib.createSaveBtn(saveAction) ]
});
function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/permission/savePermission',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,function() {
            	parent.iframeWindow.hide();
            	parent.treePanel.getRootNode().reload();
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
		UD.lib.doFormLoadData(addForm, contextPath + '/system/permission/getById?id='+ jsonParams.id, function(action) {

		});
	} else {
		fkPid.setValue(jsonParams.fkPid);
		fkPidName.setValue(jsonParams.fkPidName);
	}

});
