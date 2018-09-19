var mechanismName = UD.lib.createTextField({
	fieldLabel : '救援机构名称'
});
var city = UD.lib.createComboBox({
    fieldLabel : '城市',
    valueField : 'id',
    displayField : 'name',
    name : 'city',
    store : UD.lib.createSimpleJsonStore({
        fields : ["id", "name"],
        url : contextPath
        + "/price/template/getDictionaryDataListByCode?code=order_area",
        autoLoad : true
    })
});

var resetBtn=UD.lib.createResetBtn();
var searchBtn=UD.lib.createSearchBtn(function()
{
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			mechanismName:mechanismName.getValue(),
            city:city.getValue()
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
		{items: [mechanismName]},{items: [city]}, {items: [searchBtn]}
	]
    ,region:"north"
    //,buttonAlign:"center"
    //,buttons : [ searchBtn, resetBtn]
	
		
});
