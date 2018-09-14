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
var login = function(){  
    var panel = new Ext.Panel({//声明定义一个面板，用来布局登录页面  
    	title: '用户登陆',
        renderTo:'loginpanel',//渲染（加载）到指定的loginpanel元素中  
        layout:'fit',
        width:300,  
        defaults:{border:false},//容器中的每个子组件默认是右边框的  
        items:[addForm]  
    });  
    Ext.get('loginpanel').setStyle('position', 'absolute').center(Ext.getBody());  
};  
Ext.onReady(login);//在Ext Js文件或Dom文档载入之后才运行login函数 
