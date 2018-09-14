var hidden_id = UD.lib.createHidden("id");
var commonWidth=200;
var roleName = UD.lib.createTextField({
	fieldLabel : '角色名称',
	name : 'roleName',
	allowBlank : false,
	width : commonWidth
});
var remark = UD.lib.createTextField({
	fieldLabel : '备注',
	name : 'remark',
	width : commonWidth
});


var addForm=new Ext.FormPanel(
{
	frame:true
	,border:false
	,autoHeight:true
	,labelAlign:"right"
	,labelWidth:90
	,items:[
		hidden_id
		,roleName,remark
	]
});

var addWindow = new Ext.Window({
	width:commonWidth+120,
	autoHeight:true,
	
	closeAction : 'hide',
	plain : true,
	modal : true,
	border : false,
	constrainHeader:true,
	layout : 'fit',
	items : [addForm]
	,buttonAlign:"center"
	,buttons : [UD.lib.createSaveBtn(saveAction), UD.lib.createCloseBtn()]
	,resizable:true
	//,openFullScreen: true
	//openFullScreen:form和window都要配置autoHeight:true
});	
function saveAction() {
    var currentWindow = this.findParentByType("window"); // 获取所属窗口
    var basicForm = currentWindow.findByType("form")[0].getForm(); // 获取窗口里唯一的formpanel
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/role/saveRole',
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
			UD.lib.showWindow(addForm,"添加角色");
	    }
	);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	setData(record);
}
);
function setData(rec)
{
	UD.lib.showWindow(addForm,"修改角色");
//---------------------------------------------------------------------
	
	hidden_id.setValue(rec.get("id"));
	roleName.setValue(rec.get("roleName"));
	remark.setValue(rec.get("remark"));
	
}
