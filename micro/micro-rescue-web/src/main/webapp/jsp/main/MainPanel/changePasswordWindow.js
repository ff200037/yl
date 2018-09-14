var commonWidth=200;
var oldPassword =UD.lib.createTextField({
	fieldLabel : "旧密码",
	name : "oldPassword",
	allowBlank : false
	,width:commonWidth
	,inputType:"password"
})
var newPassword =UD.lib.createTextField({
	fieldLabel : "新密码",
	name : "newPassword",
	allowBlank : false
	,width:commonWidth
})



//在每个控件上加：,anchor : '95%'
var addForm=new Ext.FormPanel(
{
	frame:true
	,border:false
	,autoHeight:true
	,labelAlign:"right"
	,labelWidth:60
	,items:[
		oldPassword,newPassword
	]
});

var addWindow = new Ext.Window({
	//如果没使用commonWidth,就删掉这两个配置
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
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/account/modifyPassword',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
                currentWindow.hide();
                location.reload();
            });
        })
    }
}

var modifyPasswordBtn=new Ext.Button(
{
	text:"修改密码",
	iconCls : "edit",
	handler:function()
	{
		UD.lib.showWindow(addForm,"修改密码");
	}
});
