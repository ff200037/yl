Ext.namespace("Ext.ux");
Ext.ux.PageSizeCombo = function(config) {
    config = config || {};
    var defaultValue=20;//默认显示20页
    if (config.value) {
    	defaultValue=config.value;
    } 
	var combobox=new Ext.form.ComboBox(
	{
		typeAhead : true,
		triggerAction : 'all',
		mode : 'local',
		store : new Ext.data.ArrayStore({
					fields : ['value', 'text'],
					data : [	
								[10, '10条/页'], [20, '20条/页'],
								[50, '50条/页'], [100, '100条/页'],
								[250, '250条/页'], [500, '500条/页'], [1000, '1000条/页']
								, [2000, '2000条/页'], [3000, '3000条/页']
						   ]
				}),
		valueField : 'value',
		displayField : 'text', 
		editable : false,
		width : 85,
		value:defaultValue,
		style:"margin-left:10px;"
		,listeners:{
			select:function()
			{
				var pageTool=this.ownerCt;
				pageTool.pageSize=this.getValue();//选择的时候更改分页大小
				pageTool.store.load({
							params : {
								start : 0,
								limit : pageTool.pageSize
							}
						});
			}
		}
	}
	);
    Ext.ux.PageSizeCombo.superclass.constructor.call(this, combobox, config);
}
Ext.extend(Ext.ux.PageSizeCombo, Ext.form.ComboBox);
Ext.ux.PageTool = function(config) {
    Ext.apply(config, {
		displayInfo : true,
		displayMsg :'显示 {0}-{1} 条 / 共 {2} 条',
		emptyMsg : "没有数据可显示"
		
//		,plugins : [new Ext.ux.ProgressBarPager()]
		//new Ext.ux.grid.PageSizePlugin(),//不使用这个分页插件
	})
   Ext.ux.PageTool.superclass.constructor.call(this, config);
}
Ext.extend(Ext.ux.PageTool, Ext.PagingToolbar);


Ext.reg("pageTool",Ext.ux.PageTool);
//创建分页工具栏的函数,defaultPageSize默认显示多少页
function getPageTool(store,defaultPageSize)
{
	if (defaultPageSize) {
		var pagesizeCombo=new Ext.ux.PageSizeCombo({value:defaultPageSize});
		var pageTool=new Ext.ux.PageTool({
			store : store,
			pageSize:defaultPageSize,
			items : pagesizeCombo
		});	
		return pageTool;		
	} else {
		var pagesizeCombo=new Ext.ux.PageSizeCombo({value:20});
		var pageTool=new Ext.ux.PageTool({
			store : store,
			pageSize:20,//默认20页
			items : pagesizeCombo
		});	
		return pageTool;		
	}


}

