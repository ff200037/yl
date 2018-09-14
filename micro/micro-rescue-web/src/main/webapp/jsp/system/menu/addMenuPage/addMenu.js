var menuId = UD.lib.createHidden("id");
var fkPid = UD.lib.createHidden("fkPid");
var fkPidName = UD.lib.createTwinTriggerField({
	fieldLabel : "上级菜单分类名称",
	onChooseTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/menu/menuFolderPage", 320, 350, "选择上级菜单分类")
	},
    onClearTriggerClick : function(){
    	this.reset();
    	fkPid.reset();
    },	
	allowBlank : false
	,name:"fkPidName"
});
var menuName = UD.lib.createTextField({
	fieldLabel : "菜单名称",
	name : "menuName",
	allowBlank : false
})
var menuPath = UD.lib.createTextField({
	fieldLabel : "菜单路径",
	allowBlank : false,
	readOnly : true
	,name:"permissionPath"
})
var iframeWindow;
var permissionId = UD.lib.createHidden("fkPermission");
var permissionName = UD.lib.createTwinTriggerField({
	fieldLabel : "权限名称",
	onChooseTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath
				+ "/system/menu/selectPermission", 320, 350, "选择权限")
	},
    onClearTriggerClick : function(){
    	this.reset();
    	permissionId.reset();
    },	
	allowBlank : false
	,name:"permissionName"
});
window.setPermission = function(id, text, permission_path) {
	permissionId.setValue(id);
	permissionName.setValue(text);
	menuName.setValue(text);
	menuPath.setValue(permission_path);
	iframeWindow.hide();
}
window.setMenuFolder = function(id, text) {
	fkPid.setValue(id);
	fkPidName.setValue(text);
	iframeWindow.hide();
}

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
		{items: [ menuId, fkPid, fkPidName, permissionName, menuName, menuPath,permissionId ]}
    ]

	,buttonAlign : "center"
	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});
function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/menu/saveMenu',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
            	parent.iframeWindow.hide();
            	parent.treePanel.getStore().reload();
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
    	UD.lib.doFormLoadData(addForm, contextPath+ '/system/menu/getById?id='+jsonParams.id, function(action)
    	    	{
    	    		
    	    	}		
        	);
	} else {
		fkPid.setValue(jsonParams.fkPid);
		fkPidName.setValue(jsonParams.fkPidName);
	}

});