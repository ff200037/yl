var id = UD.lib.createHidden("id");
var fkPid = UD.lib.createHidden("fkPid");
var commonWidth = 300;
var fkPidName = UD.lib.createTwinTriggerField({
	fieldLabel : "上级权限分类名称",
	onTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/permission/permissionFolderPage", 250, 350,"选择上级权限分类")
	},
    onTrigger1Click : function(){
    	this.reset();
    	fkPid.reset();
    },	
	width : commonWidth,
	allowBlank : false,
	name : "fkPidName"
});
window.setPermissionFolder = function(id, text) {
	fkPid.setValue(id);
	fkPidName.setValue(text);
	iframeWindow.hide();
}
var permissionName = UD.lib.createTextField({
	fieldLabel : "权限分类名称",
	name : "permissionName",
	allowBlank : false,
	width : commonWidth
})

var addForm = new Ext.FormPanel({
	frame : true,
	border : false,
	autoHeight : true,
	labelAlign : "right",
	labelWidth : 100,
	items : [ id, fkPid, fkPidName, permissionName ],
	buttonAlign : "center",
	buttons : [ UD.lib.createSaveBtn(saveAction) ]
});
function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
	if (basicForm.isValid()) {
		UD.lib.doFormSubmit(basicForm, contextPath+ '/system/permission/savePermissionFolder',
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

