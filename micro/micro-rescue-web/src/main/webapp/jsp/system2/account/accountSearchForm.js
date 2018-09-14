var accountName = UD.lib.createTextField({
	fieldLabel : '账号名称'
});
var accountStatus = UD.lib.createComboBox({
	fieldLabel : '账号状态',
	valueField : 'id',
	displayField : 'name',
	store : new Ext.data.SimpleStore({
		data : [ [ '1', '正常' ], [ '2', '禁用' ] ],
		fields : [ "id", "name" ]
	})
});
var searchbtn=UD.lib.createSearchBtn(function()
{
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			accountName:accountName.getValue()
			,accountStatus:accountStatus.getValue()
		}
		,isClearLastParams:false
	}
	);		
}
);
var searchForm = new Ext.FormPanel({
	layout: 'column',
 	fieldDefaults: {
 		labelAlign: 'right'
//        ,labelWidth: 100
    },	
    defaults: {
        layout: 'form'
        ,xtype: 'container'
        ,columnWidth:.3
    },
	items: [
		{items: [accountName]}
		, {items: [accountStatus]}
		, {items: [searchbtn]}
	]

	,region:"north"
});
