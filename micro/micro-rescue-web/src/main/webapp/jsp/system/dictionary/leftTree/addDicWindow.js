var dicId=UD.lib.createHidden("id");
var fkPid=UD.lib.createHidden("fkPid");
var fkPidName =UD.lib.createTwinTriggerField({
	fieldLabel : "上级字典分类名称",
	onChooseTriggerClick : function(e) {
		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/dictionary/dictionaryTreePage", 300, 400, "选择字典分类")
	},
    onClearTriggerClick : function(){
    	this.reset();
    	fkPid.reset();
    },	
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
})
var dictionaryCode =UD.lib.createTextField({
	fieldLabel : "字典分类编码",
	name : "dictionaryCode"
	,allowBlank : false
})


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
		{items: [dicId,fkPid, fkPidName,dictionaryName,dictionaryCode]}
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
	,width:380
});
function saveAction() {
	var currentWindow = this.findParentByType("window");// 获取所属窗口
	var basicForm = currentWindow.down("form").getForm(); // 获取窗口里唯一的formpanel
	if (basicForm.isValid()) {
		UD.lib.doFormSubmit(basicForm, contextPath+ '/system/dictionary/saveDictionary', function(action) {
					Ext.MessageBox.alert('提示', action.result.msg,function() {
								currentWindow.hide();
								treePanel.getStore().reload();
							});
				})
	}
}

// -----------------------------------------GridPanel用到的按钮----------------------------
function addDictionary(node) {
	UD.lib.showWindow(addForm,"添加字典分类");

	fkPid.setValue(node.get("id"));
	fkPidName.setValue(node.get("text"));
}
function editDictionary(node) {
	UD.lib.showWindow(addForm,"修改字典分类");
	
	dicId.setValue(node.get("id"));
	dictionaryName.setValue(node.get("text"));
	dictionaryCode.setValue(node.get("dictionaryCode"));
	
	fkPid.setValue(node.parentNode.get("id"));
	fkPidName.setValue(node.parentNode.get("text"));
}
