var priceName = UD.lib.createTextField({
	fieldLabel : '模板名称'
});
var rescueObject = UD.lib.createComboBox({
    fieldLabel : '救援项',
    valueField : 'id',
    displayField : 'name',
    name : 'rescueObject',
    store : UD.lib.createSimpleJsonStore({
        fields : ["id", "name"],
        url : contextPath
        + "/price/template/getRescueObject",
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
            priceName:priceName.getValue(),
            rescueObject:rescueObject.getValue()
		}
		,isClearLastParams:false
	}
	);		
}
);
var searchForm = new Ext.FormPanel({
	layout: 'column',
 	/*fieldDefaults: {
 		labelAlign: 'right'
        ,labelWidth: 100
    },*/	
    defaults: {
        layout: 'form'
        ,xtype: 'container'
        ,columnWidth:.3
    },
	items: [
		{items: [priceName]},{items: [rescueObject]}
	]
    ,region:"north"
    ,buttonAlign:"center"
    ,buttons : [ searchBtn, resetBtn]
	
		
});
