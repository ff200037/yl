var cpName = UD.lib.createTextField({
	fieldLabel : '优惠券名称'
});
var cpType = UD.lib.createComboBox({
	fieldLabel : '优惠券类别',
	valueField : 'id',
	displayField : 'name',
	name : 'name',
	store : UD.lib.createSimpleJsonStore({
				fields : ["id", "name"],
				url : contextPath
						+ "/coupon/coupon/getDictionary?name=coupon_type",
				autoLoad : true
			})
});

var cpContent = UD.lib.createTextField({
	fieldLabel : '优惠券内容'
});
var cpArea = UD.lib.createTextField({
	fieldLabel : '优惠区域'
});
var cpObject = UD.lib.createTextField({
	fieldLabel : '优惠对象'
});
var startOrEnd = UD.lib.createTextField({
	fieldLabel : '启用禁用'
});
var searchbtn=UD.lib.createSearchBtn(function()
{
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			cpnName:cpName.getValue(),
			cpnType:cpType.getValue(),
			cpContent:cpContent.getValue(),
			cpArea:cpArea.getValue(),
			cpObject:cpObject.getValue(),
			startOrEnd:startOrEnd.getValue()
		}
		,isClearLastParams:false
	}
	);	
}
);
var searchForm = new Ext.FormPanel({
	labelAlign:"right"
	,labelWidth:90
	,region:"center",
	items:[
	{
		layout: 'column',
	    defaults: {
	        layout: 'form'
	        ,xtype: 'container'
	        ,columnWidth:.25
	    },
		items: [
			{items: [cpName]}
			, {items: [cpType]}
			, {items: [cpContent]}
			, {items: [cpArea]}
		]
	},{
		layout: 'column',
	    defaults: {
	        layout: 'form'
	        ,xtype: 'container'
	        ,columnWidth:.25
	    },
		items: [
			{items: [cpObject]}
			, {items: [startOrEnd]}
			, {items: [searchbtn]}
			
		]
	}]
	,region:"north"
});
