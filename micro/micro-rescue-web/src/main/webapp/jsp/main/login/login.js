var commonWidth=200;
var accountName = UD.lib.createTextField({
	fieldLabel : '账号',
	name : 'accountName',
	allowBlank : false,
	width : commonWidth
});
var accountPassword = UD.lib.createTextField({
	fieldLabel : '密码',
	name : 'accountPassword',
	allowBlank : false,
	width : commonWidth
	,inputType:"password"
		,enableKeyEvents:true//跳转配置
		,listeners : {
			specialkey : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					doLogin();
				}
			}
		}		
});
var loginBtn = new Ext.Button({
	text : "登录",
	iconCls : "login",
	handler : function() {
		doLogin();
	}
});
function doLogin()
{
    var basicForm = addForm.getForm(); 
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/system/main/doLogin',
        function(action) {
        	if (action.result.success==true) {
        		window.location=action.result.reqUrl;
//        		window.location=contextPath + '/system/main/mainPage';
			} else {
				 Ext.MessageBox.alert('提示', action.result.msg);
			}
        	
        	
           
        })
    }	

}
var addForm=new Ext.FormPanel(
{
	frame:true
	,border:false
	,autoHeight:true
	,autoWidth:true
	,labelAlign:"right"
	,labelWidth:60
	,items:[
	        accountName,accountPassword
	]
	,buttonAlign:"center"
	,buttons : [loginBtn]

});
var addWindow = new Ext.Window({
	width:commonWidth+120,
	autoHeight:true,
	title: '用户登陆',//如果不加标题，就不能拖到窗口
	closable:false,
	closeAction : 'hide',
	plain : true,
	modal : true,
	border : false,
	constrainHeader:true,
	layout : 'fit',
	items : [addForm]
	,buttonAlign:"center"
	,buttons : [loginBtn]
	,resizable:true
	//,openFullScreen: true
	//openFullScreen:form和window都要配置autoHeight:true
});

var rightCenterPanel= new Ext.Panel(
		{
			border:false
		}
		);
Ext.onReady(function() {
    new Ext.Viewport({
        layout: "fit",
        items: [rightCenterPanel]
    });
    addWindow.show();
    accountName.focus();
//  添加浏览器缩放自动居中效果  
    Ext.EventManager.onWindowResize(function () {  
    	addWindow.center();  
    });
});