//Ext.example.msg('修改操作', '请选择要修改的一项！');

//user-defined
Ext.ns('UD.lib')
UD.lib.requiredType="red";
UD.lib.changeRequiredColor=function (obj) {
	if (UD.lib.requiredType=='red') {
		obj.fieldLabel='<font color=red>'+obj.fieldLabel+'</font>';
	} else {
		obj.style='background:none repeat scroll 0 0 #E8FFF5;';
	}	
}
UD.lib.createTriggerField=function (config) {
	var obj={
			fieldLabel : config.fieldLabel,
			hideTrigge : false,
			editable : false,
			onTriggerClick : config.onTriggerClick
		};
	if (config.allowBlank==false) {
		obj.allowBlank=false;
		UD.lib.changeRequiredColor(obj);		
	}	
	if (config.name) {
		obj.name=config.name;
	}	
	if (config.width) {
		obj.width=config.width;
	}	
	return new Ext.form.TriggerField(obj);	
}
//例子
//var fkPidName = UD.lib.createTriggerField({
//	fieldLabel : "xxx",
//	onTriggerClick : function(e) {
//		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/permission/permissionFolderPage", 250, 350,"xxxx")
//	},
//	width : commonWidth,
//	allowBlank : false,
//	name : "fkPidName"
//});


//参考自定义插件SearchField.js
UD.lib.createTwinTriggerField=function (config) {
	var obj={
			fieldLabel : config.fieldLabel,
			editable : false,
			trigger1Class:'x-form-clear-trigger',
			hideTrigger1:false,//显示清除图标
//			trigger2Class:'x-form-search-trigger',
			hideTrigger2:true,//不显示搜索图标
			onTriggerClick : config.onTriggerClick,
			onTrigger1Click : config.onTrigger1Click
		};
	if (config.allowBlank==false) {
		obj.allowBlank=false;
		UD.lib.changeRequiredColor(obj);
	}	
	if (config.name) {
		obj.name=config.name;
	}	
	if (config.width) {
		obj.width=config.width;
	}	
	return new Ext.form.TwinTriggerField(obj);	
}
//例子
//var fkPidName = UD.lib.createTwinTriggerField({
//	fieldLabel : "上级权限分类名称",
//	onTriggerClick : function(e) {
//		iframeWindow = UD.lib.showIframeWindow(contextPath+ "/system/permission/permissionFolderPage", 250, 350, "选择上级权限分类")
//	},
//    onTrigger1Click : function(){
//    	this.reset();
//    	fkPid.reset();
//    },	
//	width : commonWidth,
//	allowBlank : false
//	,name:"fkPidName"
//});


//上传文件控件
UD.lib.createFileUploadField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.FileUploadField(config);
}
//文本框控件
UD.lib.createTextField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.TextField(config);
}
//多行文本框控件
UD.lib.createTextArea=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.TextArea(config);
}
//数值控件
UD.lib.createNumberField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.NumberField(config);
}
//日期控件
UD.lib.createDateField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.DateField(config);
}
//日期控件,支持时分秒
UD.lib.createDateTimeField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.ux.form.DateTimeField(config);
}

UD.lib.createHidden=function(name) {
	var config={};
	if (name) {
		config.name=name;
	}
	return new Ext.form.Hidden(config);
}
//例子
//var xxx=UD.lib.createHidden();//创建一个没有name的Hidden
//var xxx=UD.lib.createHidden("name");//创建一个有name的Hidden


UD.lib.createComboBox=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
		config.editable=false;
	}else{
		config.editable=true;
	}
	
	config.mode='local';
	config.forceSelection=true;
	config.resizable=true;
	config.triggerAction= 'all';
	return new Ext.form.ComboBox(config);	
}
//例子
//var addPermission_application = UD.lib.createComboBox({
//    fieldLabel: '应用名称',
//    valueField: 'id',
//    displayField: 'app_name',
//    hiddenName: 'fkApplication',
//    store: new Ext.data.JsonStore({
//        fields: ["id", "app_name"],
//        url: contextPath + "/system/application/getAppListData",
//        autoLoad: true
//    })
//    ,allowBlank: false
//});



UD.lib.createAddBtn=function(fn) {
	return new Ext.Button({
		text: '添加',
		iconCls: "add",
		handler: fn
	});
}
//创建修改按钮
UD.lib.createModifyBtn=function(fn) {
	return new Ext.Button({
		text: '修改',
		iconCls: "edit",
		handler:function()
		{
			var theGrid=this.ownerCt.ownerCt;//获取所属的gridpanel组件
			var theSelectionModel=theGrid.getSelectionModel();
			if (theSelectionModel.getCount() == 0) {
				Ext.Msg.alert("提示", "请选择要修改的记录");
				return;
			} else if (theSelectionModel.getCount() >1) {
				Ext.Msg.alert("提示", "只能选择一条记录");
				return;
			}
			fn(theSelectionModel.getSelected());
		}
	});
}
//创建自定义按钮(复制修改按钮的代码)
/**
 * @method
 * @param {Type} text 目标对象
 * @returns {Type} 运营商名称
 * @desc 根据目标对象获取运营商
 */
UD.lib.createGridCustomBtn=function(text,iconCls,fn) {
	return new Ext.Button({
		text: text,
		iconCls: iconCls,
		handler:function()
		{
			var theGrid=this.ownerCt.ownerCt;//获取所属的gridpanel组件
			var theSelectionModel=theGrid.getSelectionModel();
			if (theSelectionModel.getCount() == 0) {
				Ext.Msg.alert("提示", "请选择一条记录");
				return;
			} else if (theSelectionModel.getCount() >1) {
				Ext.Msg.alert("提示", "只能选择一条记录");
				return;
			}
			fn(theSelectionModel.getSelected());
		}
	});
}
//自定义按钮用法
//var mctxBtn = UD.lib.createGridCustomBtn("按钮名称","edit",function(record) {
//	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/bis/mucaiInfo/mctxInit?id=" + record.get("id"),1000, 500, "窗口标题")
//});


//创建确定按钮
UD.lib.createSureBtn=function(fn) {
	return new Ext.Button({
		text: '确定',
		iconCls: "sure",
		handler: fn
	});
}
//创建保存按钮
UD.lib.createSaveBtn=function(fn) {
    return new Ext.Button({
        text: '保存',
        iconCls: "save",
        handler: fn
    });
}
//创建关闭按钮
UD.lib.createCloseBtn=function() {
    return new Ext.Action({
        text: "关闭",
        iconCls: "delete",
        handler: function() {
            this.findParentByType("window").hide();
        }
    })
}
//创建关闭按钮
UD.lib.createIframeCloseBtn=function() {
	return new Ext.Action({
		text: "关闭",
		iconCls: "delete",
		handler: function() {
			parent.iframeWindow.hide();
		}
	})
}
//创建重置按钮
UD.lib.createResetBtn=function() {
	return new Ext.Action({
		text: "重置",
		iconCls: "remove",
		handler: function() {
			this.findParentByType("form").getForm().reset();
			
		}
	})
}
UD.lib.createSearchBtn=function(fn) {
	return new Ext.Button({
		text: '查询',
		iconCls: "search",
		handler: fn
	});
}
//例子：
//var delbtn=UD.lib.createSingleDelBtn(contextPath+"/bis/xxxxxxx/delete");//默认是id
//var delbtn=UD.lib.createSingleDelBtn(contextPath+"/bis/xxxxxxx/delete","code");//将code作为id
//创建单个删除按钮
UD.lib.createSingleDelBtn=function(delUrl,key) {
	return new Ext.Button({
		text: '删除',
		iconCls: "delete",
		handler:function()
		{
			var theGrid=this.ownerCt.ownerCt;//获取所属的gridpanel组件
			var theSelectionModel=theGrid.getSelectionModel();
			if (theSelectionModel.getCount() == 0) {
				Ext.Msg.alert("提示", "请选择要删除的记录!");
				return;
			}
			if (theSelectionModel.getCount() > 1) {
				Ext.Msg.alert("提示", "只能选一条记录!");
				return;
			}
			Ext.Msg.confirm('提示', '确定要删除所选记录吗?', function(btn) {
				if (btn != 'yes') {
					return;
				}
                var idVal;
                var theRecord=theSelectionModel.getSelected();
                if (key) {
                	idVal=theRecord.get(key);
				} else {
					idVal=theRecord.get("id");//必须有id
				}
        		var params={
        				id:idVal
        	    	}                 
        		UD.lib.doAjax(delUrl,params,function(response, options)
    				{
    					var resJson=Ext.decode(response.responseText);
    					Ext.Msg.alert("提示", resJson.msg,function()
    					{
    						if (resJson.success==true) {
    							theGrid.getStore().reload();//更新表格的数据
    						}
    					}
    					);
    				}
        			); 
        			
        			
			});
		}
	});
}

//例子：
//var delbtn=UD.lib.createBatchDelBtn(contextPath+"/bis/xxxxxxx/delete");//默认是id
//var delbtn=UD.lib.createBatchDelBtn(contextPath+"/bis/xxxxxxx/delete","code");//将code作为id
//创建批量删除按钮
UD.lib.createBatchDelBtn=function(delUrl,key) {

    var delbtn = new Ext.Action({
        text: "删除",
        iconCls: "delete",
        handler: function() {
            var theGrid = this.ownerCt.ownerCt; //获取所属的gridpanel组件
            var theSelectionModel = theGrid.getSelectionModel();
            if (theSelectionModel.getCount() == 0) {
                Ext.Msg.alert("提示", "请选择要删除的记录!");
                return;
            }
            Ext.Msg.confirm('提示', '确定要删除所选记录吗?', function(btn) {
                //如果点击的不是"是"，可能是点击"否"或关闭图标
                if (btn != 'yes') {
                    return;
                }
                var ids = [];
                Ext.each(theSelectionModel.getSelections(), function(theRecord) {
                    if (key) {
                    	ids.push(theRecord.get(key));
					} else {
						ids.push(theRecord.get("id"));//必须有id
					}
                });
 
        		var params={
        				ids:ids.join(",")
        	    	}                 
        		UD.lib.doAjax(delUrl,params,function(response, options)
        				{
        					var resJson=Ext.decode(response.responseText);
        					Ext.Msg.alert("提示", resJson.msg,function()
        					{
        						if (resJson.success==true) {
        							theGrid.getStore().reload();//更新表格的数据
        						}
        					}
        					);
        				}
        			);                
            });


        }
    });

    return delbtn;
}

UD.lib.showWindow=function(theForm, windowTitle) {
    var theWindow = theForm.findParentByType("window");
	if (windowTitle && windowTitle!="") {
		theWindow.setTitle(windowTitle);
	}    
    theWindow.show();
    theForm.getForm().reset();
}
UD.lib.showIframeWindow=function(defaultSrc,width,height,title,openFullScreen,listeners) {
//	var zz=Ext.getBody();
//	zz=parent.Ext.getBody();
	var config={
//		width : width,
//		height : height,
//		 autoHeight:true,
//		 autoWidth:true,
		title : title,
		closeAction : 'hide',
		plain : true,
		modal : true,
		border : false,
		constrainHeader : true,
		layout : 'fit',
		items : [],
		resizable : true,
//		,renderTo:body
		listeners:listeners
		};
	
	if (openFullScreen && openFullScreen==true) {
		config.openFullScreen=true;
	} else {
		if (width==0) {
			config.autoWidth=true;
		} else {
			config.width=width;
		}
		if (height==0) {
			config.autoHeight=true;
		} else {
			config.height=height;
		}
	}
	var addWindow = new Ext.Window(config);
	var htmlpanel=new JpkFrame.common.HtmlPanel({
		xtype : 'htmlpanel',
		autoScroll: true,
		layout : 'fit',
		border : false,
		// loadMask:false,关闭进度条
		// loadMask:{} 启用默认的进度条,{}为设置
		// loadMask:{msg:'正在打开页面，请稍等....',msgCls:''}, //启用自定义配置
		loadMask : {},
		defaultSrc : defaultSrc,
		closable : true,
		group : this
		
//		title : "xxxxx",
//		,bodyStyle: {
//			background:"white",
//            padding: '30px'
//        }
//		,bodyStyle: "background:white;padding: 30px"
	});
	addWindow.add(htmlpanel);
	
	addWindow.show();
	return addWindow;
}
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
//UD.lib.doFormLoadData(addForm, contextPath+ '/system/dictionaryData/getById?id='+jsonParams.id, function(action)
//    	{
//    		
//    	}		
//	);
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
