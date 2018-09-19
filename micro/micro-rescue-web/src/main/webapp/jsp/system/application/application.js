var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/system/application/getListData",
	fields : [
		{name:'id'}
		,{name:'app_name'}
		,{name:'app_code'}
		,{name:'app_webpath'}
		,{name:'use_state'}
		,{name:'remark'}	
	]
});
var delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/application/delApplication");
var grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title:"应用列表",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text:'应用名称',dataIndex:'app_name'}
		,{text:'应用编码',dataIndex:'app_code'}
		,{text:'应用路径',dataIndex:'app_webpath'}
//		,{text:'启用状态',dataIndex:'use_state'}
		,{text:'应用描述',dataIndex:'remark'}
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,tbar:[addbtn,modifyBtn,delbtn]
});
grid.on("rowclick",function(thegrid,rowIndex,e)
{		var record = this.getSelectionModel().getSelection()[0];
		if (record.get("app_code")=="systemApplication") {
			modifyBtn.setDisabled(true);
			delbtn.setDisabled(true);
		} else {
			modifyBtn.setDisabled(false);
			delbtn.setDisabled(false);

		}
}
);


Ext.onReady(function(){
	new Ext.Viewport({
		layout:"fit",
		items:[grid]
	});
	store.load();
});
