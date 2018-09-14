var dicId=UD.lib.createHidden("id");
var fkPid=UD.lib.createHidden("fkPid");
var commonWidth=300;
var fkPidName =UD.lib.createTwinTriggerField({
	fieldLabel : "上级字典分类名称",
	onTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/dictionary/dictionaryTreePage", 300, 400, "选择字典分类")
	},
    onTrigger1Click : function(){
    	this.reset();
    	fkPid.reset();
    },	
	width : commonWidth,
	allowBlank : false
});
window.setDictionaryName=function(id,text)
{
	fkPid.setValue(id);
	fkPidName.setValue(text);
	iframeWindow.hide();
}
var dictionaryName =UD.lib.createTextField({
	fieldLabel : "字典分类名称",
	name : "dictionaryName",
	allowBlank : false
	,width:commonWidth
})
var dictionaryCode =UD.lib.createTextField({
	fieldLabel : "字典分类编码",
	name : "dictionaryCode"
	,width:commonWidth
	,allowBlank : false
})

var addForm = new Ext.FormPanel({
			frame : true,
			border : false,
			autoHeight : true,
			labelAlign : "right",
			labelWidth : 100,
			items : [dicId,fkPid, fkPidName,dictionaryName,dictionaryCode]
		});

var addWindow = new Ext.Window({
	width:commonWidth+150,
	autoHeight : true,

	closeAction : 'hide',
	plain : true,
	modal : true,
	border : false,
	constrainHeader : true,
	layout : 'fit',
	items : [addForm],
	buttonAlign : "center",
	buttons : [UD.lib.createSaveBtn(saveAction), UD.lib.createCloseBtn()],
	resizable : true
		// ,openFullScreen: true
		// openFullScreen:form和window都要配置autoHeight:true
	});

function saveAction() {
	var currentWindow = this.findParentByType("window");// 获取所属窗口
	var basicForm = currentWindow.findByType("form")[0].getForm();// 获取窗口里唯一的formpanel
	if (basicForm.isValid()) {
		UD.lib.doFormSubmit(basicForm, contextPath+ '/system/dictionary/saveDictionary', function(action) {
					Ext.MessageBox.alert('提示', action.result.msg,function() {
								currentWindow.hide();
								treePanel.getRootNode().reload();
							});
				})
	}
}

// -----------------------------------------GridPanel用到的按钮----------------------------
function addDictionary(node) {
	UD.lib.showWindow(addForm,"添加字典分类");

	fkPid.setValue(node.id);
	fkPidName.setValue(node.text);
}
function editDictionary(node) {
	UD.lib.showWindow(addForm,"修改字典分类");
	
	dicId.setValue(node.id);
	dictionaryName.setValue(node.text);
	dictionaryCode.setValue(node.attributes.dictionaryCode);
	
	fkPid.setValue(node.parentNode.id);
	fkPidName.setValue(node.parentNode.text);
}
