//提交表单数据
UD.lib.doFormSubmit=function(theForm, url, callbak,params) {
    theForm.submit({
        waitTitle: '提示',
        waitMsg: '正在提交数据',
        //waitMsg : '正在保存数据，请稍候...',
        url: url,
        params:params || {},
        method: 'post',
        success: function(basicForm, action) {
            callbak(action);
        },
        failure: function(basicForm, action) {
 			var status=action.response.status;
			if (status==0){
				Ext.MessageBox.alert('提示', "连接不上服务器");
				return;
			}
			if (status==404){
				Ext.MessageBox.alert('提示', "请求无效");
				return;
			}
			if (status==500){
				Ext.MessageBox.alert('提示', "服务器内部错误");
				return;
			}
			if (status==403) {
				//没有配置权限或者该用户没有权限
				var resJson = Ext.decode(action.response.responseText);
				Ext.MessageBox.alert('提示', resJson.errorMsg);
				return;
			} 
			if (status==200) {
				//业务上的提示
				var resJson = Ext.decode(action.response.responseText);
				Ext.MessageBox.alert('提示', resJson.msg);
				return;
			} 
			Ext.MessageBox.alert('提示', "未知错误");
			return;
			
			
        }
    })

}
//加载表单数据
UD.lib.doFormLoadData=function(theForm, url, callbak) {
	theForm.form.load( {
        url : url,
        waitTitle: '提示',
        waitMsg : '正在载入数据...',
        success : function(form,action) {
        	callbak(action);
        },
        failure : function(form,action) {
        	theForm.setDisabled(true);//禁止操作表单
        	
 			var status=action.response.status;
			if (status==0){
				Ext.MessageBox.alert('提示', "连接不上服务器");
				return;
			}
			if (status==404){
				Ext.MessageBox.alert('提示', "请求无效");
				return;
			}
			if (status==500){
				Ext.MessageBox.alert('提示', "服务器内部错误");
				return;
			}
			if (status==403) {
				//没有配置权限或者该用户没有权限
				var resJson = Ext.decode(action.response.responseText);
				Ext.MessageBox.alert('提示', resJson.errorMsg);
				return;
			} 
			Ext.MessageBox.alert('提示', "未知错误");
			return;
        }
    });	
}
//发送ajax请求
UD.lib.doAjax=function(url,params, callbak,loadMsg) {
	if (loadMsg) {
		Ext.getBody().mask(loadMsg);   
	} else {
//		Ext.getBody().mask("正在执行操作，请稍等");   
		Ext.getBody().mask("操作中，请稍等");   
	}
    Ext.Ajax.request({
        method: 'POST',
        url: url,
        params: params,
        success: function(response, options) {
        	Ext.getBody().unmask();
        	callbak(response, options);
        },
        failure: function(response, options) {
        	Ext.getBody().unmask();
        	
 			var status=response.status;
			if (status==0){
				Ext.MessageBox.alert('提示', "连接不上服务器");
				return;
			}
			if (status==404){
				Ext.MessageBox.alert('提示', "请求无效");
				return;
			}
			if (status==500){
				Ext.MessageBox.alert('提示', "服务器内部错误");
				return;
			}
			if (status==403) {
				//没有配置权限或者该用户没有权限
				var resJson = Ext.decode(response.responseText);
				Ext.MessageBox.alert('提示', resJson.errorMsg);
				return;
			} 
			if (status==200) {
				//业务上的提示
				var resJson = Ext.decode(response.responseText);
				Ext.MessageBox.alert('提示', resJson.msg);
				return;
			} 
			Ext.MessageBox.alert('提示', "未知错误");
			return;         
        	
        }
    });

}