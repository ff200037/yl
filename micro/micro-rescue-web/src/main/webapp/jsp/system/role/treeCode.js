 
var permissionTreePanel = new Ext.tree.TreePanel({
   		title:"角色权限",
        region: 'west',
        width:340,
   		id:"rolePanel",
        autoScroll: true,
		//permissionTreePanel本身没有enableAllCheck配置项，这里在TreeNodeCheck.js里定义的
		enableAllCheck:true,//配置成true后就不用配置checked属性了（如系统管理员1）
        loader: new Ext.tree.TreeLoader({dataUrl:contextPath + '/system/permission/getTreeData'}),        
        rootVisible: false,
        root: new Ext.tree.AsyncTreeNode({uiProvider:Ext.tree.RootTreeNodeUI})
        ,plugins: 'filterBar'
        ,tbar: {
            items: [{
                text: '保存',
                iconCls: "save",
                handler: function(){
                	if (record) {
                		var ids=permissionTreePanel.getChecked("id").join(",");
	                	var params={
	                		roleId:record.get("id"),
	                		permissionIds:ids
	                	}
						UD.lib.doAjax(contextPath+"/system/role/saveRolePermission",params,function(response, options)
							{
					            var resJson = Ext.decode(response.responseText);
					            Ext.Msg.alert("提示", resJson.msg);
							}
						);                		
                	}else
                	{
                		Ext.Msg.alert("提示", "请选择要配置权限的角色");
                	}
                   
                }
            }
            
            ]
        }
    });
   var menuTreePanel = new Ext.tree.TreePanel({
   		title:"角色菜单",
        region: 'center',
        autoScroll: true,
		//permissionTreePanel本身没有enableAllCheck配置项，这里在TreeNodeCheck.js里定义的
		enableAllCheck:true,//配置成true后就不用配置checked属性了（如系统管理员1）
        loader: new Ext.tree.TreeLoader({dataUrl:contextPath + '/system/menu/getTreeData'}),        
        rootVisible: false,
        root: new Ext.tree.AsyncTreeNode({uiProvider:Ext.tree.RootTreeNodeUI})
        ,plugins: 'filterBar'
        ,tbar: {
            items: [{
                text: '保存',
                iconCls: "save",
                handler: function(){
                	if (record) {
                		var ids=menuTreePanel.getChecked("id").join(",");
	                	var params={
	                		roleId:record.get("id"),
	                		menuIds:ids
	                	}
						UD.lib.doAjax(contextPath+"/system/role/saveRoleMenu",params,function(response, options)
							{
					            var resJson = Ext.decode(response.responseText);
					            Ext.Msg.alert("提示", resJson.msg);
							}
						);                		
                	}else
                	{
                		Ext.Msg.alert("提示", "请选择要配置菜单的角色");
                	}
                   
                }
            }
            
            ]
        }
    });
    
    
    
var rightCenterPanel= new Ext.Panel(
{
	disabled:true,
	border:false,
	region:"center",
//	layout:"column",
//	items:[permissionTreePanel,menuTreePanel]
	layout:"border",
	items:[permissionTreePanel,menuTreePanel]
	
}
);    
    
    
    
    