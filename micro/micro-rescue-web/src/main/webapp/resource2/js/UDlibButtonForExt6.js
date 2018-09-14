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
			var selection=theSelectionModel.getSelection();
			fn(selection[0]);
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
			var selection=theSelectionModel.getSelection();
			fn(selection[0]);
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
                var selection=theSelectionModel.getSelection();
                var theRecord=selection[0];
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
                Ext.each(theSelectionModel.getSelection(), function(theRecord) {
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


