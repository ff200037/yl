//提交表单数据
UD.lib.doFormSubmit=function(theForm, url, callbak) {
    theForm.submit({
        waitTitle: '提示',
        waitMsg: '正在提交数据',
        //waitMsg : '正在保存数据，请稍候...',
        url: url,
        method: 'post',
        success: function(basicForm, action) {
            callbak(action);
        },
        failure: function(basicForm, action) {
        	if (action.response.statusText=="Not Found") {
        		Ext.MessageBox.alert('提示', "请求无效");
        		return;
			}
			var resJson = Ext.decode(action.response.responseText);
			//没有配置权限或者该用户没有权限
			if (action.response.statusText=="Forbidden") {
				Ext.MessageBox.alert('提示', resJson.errorMsg);
			} 
			else if (action.response.statusText=="Internal Server Error"){
				Ext.MessageBox.alert('提示', "服务器内部错误");
			}
			//在上传文件失败的时候这个值是有的
			else if (action.failureType && action.failureType=="server"){
				if (resJson && resJson.msg) {
					Ext.MessageBox.alert('提示', resJson.msg);
				} else {
					Ext.MessageBox.alert('提示', "服务器内部错误");
				}
				
			}
			else if (action.response.statusText=="communication failure"){
				Ext.MessageBox.alert('提示', "连接不上服务器");
			}
			else if (action.response.statusText=="transaction aborted"){
				Ext.MessageBox.alert('提示', "请求超时");
			}
			//显示业务上的信息
			else if (action.response.statusText=="OK"){
				Ext.MessageBox.alert('提示', resJson.msg);
			}
			else {
				Ext.MessageBox.alert('提示', "其他错误");
			}        	
        }
    })

}
//加载表单数据
//UD.lib.doFormLoadData(addForm, contextPath+ '/system/dictionaryData/getById?id='+jsonParams.id, function(action)
//    	{
//    		
//    	}		
//	);
UD.lib.doFormLoadData=function(theForm, url, callbak) {
	theForm.form.load( {
        url : url,
        waitTitle: '提示',
        waitMsg : '正在载入数据...',
        success : function(form,action) {
        	callbak(action);
        },
        failure : function(form,action) {
			var resJson = Ext.decode(action.response.responseText);
			//没有配置权限或者该用户没有权限
			if (action.response.statusText=="Forbidden") {
				Ext.MessageBox.alert('提示', resJson.errorMsg);
			} 
			else if (action.response.statusText=="Internal Server Error"){
				Ext.MessageBox.alert('提示', "服务器内部错误");
			}
			else if (action.response.statusText=="communication failure"){
				Ext.MessageBox.alert('提示', "连接不上服务器");
			}
			else if (action.response.statusText=="transaction aborted"){
				Ext.MessageBox.alert('提示', "请求超时");
			}
			else {
				if (action.failureType=="load") {
					if (theForm.title) {
						Ext.MessageBox.alert('提示', "无法加载数据【"+theForm.title+"】");
					} else {
						Ext.MessageBox.alert('提示', "无法加载数据");
					}
					
				} else {
					Ext.MessageBox.alert('提示', "其他错误");
				}
				
				
				
			}        	
        }
    });	
}
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
			var resJson = Ext.decode(response.responseText);
			//没有配置权限或者该用户没有权限
			if (response.statusText=="Forbidden") {
				Ext.MessageBox.alert('提示', resJson.errorMsg);
			} 
			else if (response.statusText=="Internal Server Error"){
				Ext.MessageBox.alert('提示', "服务器内部错误");
			}
			else if (response.statusText=="communication failure"){
				Ext.MessageBox.alert('提示', "连接不上服务器");
			}
			else if (response.statusText=="transaction aborted"){
				Ext.MessageBox.alert('提示', "请求超时");
			}
			else {
				Ext.MessageBox.alert('提示', "其他错误");
			}          
        	
        }
    });

}