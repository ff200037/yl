var keyWord = UD.lib.createTextField({
	fieldLabel : '请输入参数名称或编码'
});

var searchbtn=UD.lib.createSearchBtn(function()
{
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			keyWord:keyWord.getValue()
		}
		,isClearLastParams:false
	}
	);	
}
);
var searchForm = new Ext.FormPanel({
	layout: 'column',
    defaults: {
        layout: 'form'
        ,xtype: 'container'
        ,columnWidth:.3
    },
	items: [
		{items: [keyWord]}
		, {items: [searchbtn]}
	]

	,region:"north"
});
