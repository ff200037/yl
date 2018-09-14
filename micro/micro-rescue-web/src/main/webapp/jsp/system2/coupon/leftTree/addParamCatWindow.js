var paramCatId=UD.lib.createHidden("id");
var fkPid=UD.lib.createHidden("fkPid");
var fkPidName =UD.lib.createTwinTriggerField({
	fieldLabel : "上级参数分类名称",
	onTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/paramCat/paramCatTreePage", 300, 400, "选择参数分类")
	},
    onTrigger1Click : function(){
    	this.reset();
    	fkPid.reset();
    },	
	allowBlank : false
});
window.setCatName=function(id,text)
{
	fkPid.setValue(id);
	fkPidName.setValue(text);
	iframeWindow.hide();
}
var catName =UD.lib.createTextField({
	fieldLabel : "参数分类名称",
	name : "catName",
	allowBlank : false
})

var addForm = new Ext.FormPanel({
	bodyPadding:5,
	layout: 'column',
    defaults: {
        layout: 'form'
        ,xtype: 'container'
        ,columnWidth:1
    },
	items: [
		{items: [paramCatId,fkPid, fkPidName,catName]}
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
});
function saveAction() {
	var currentWindow = this.findParentByType("window");
	var basicForm = currentWindow.down("form").getForm();
	if (basicForm.isValid()) {
		UD.lib.doFormSubmit(basicForm, contextPath+ '/system/paramCat/saveParamCat', function(action) {
					Ext.MessageBox.alert('提示', action.result.msg,function() {
								currentWindow.hide();
								treePanel.getRootNode().reload();
							});
				})
	}
}

// -----------------------------------------GridPanel用到的按钮----------------------------
function addParamCatWin(node) {
	UD.lib.showWindow(addForm,"添加参数分类");

	fkPid.setValue(node.get("id"));
	fkPidName.setValue(node.get("text"));
}
function editParamCatWin(node) {
	UD.lib.showWindow(addForm,"修改参数分类");
	
	paramCatId.setValue(node.get("id"));
	catName.setValue(node.get("text"));
	
	
	fkPid.setValue(node.parentNode.get("id"));
	fkPidName.setValue(node.parentNode.get("text"));
}
