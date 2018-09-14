<#include "/common/include/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

// 详细展示
function showCarDetail(rowIndex)
{
	var record=store.getAt(rowIndex);
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/${syspath }/${classNameLower }/${classNameLower}Detail?id=" + record.get("id"),1140, 450, "详细信息")
}
function licenseNoRenderer(value,metadata,record,rowIndex,colIndex,theStore)
{
	var link = "<a href ='javascript:showCarDetail({0})' >{1}</a>";
	return String.format(link, rowIndex,record.get("licenseNo"));
}
// 是否多选择
var sm=new Ext.grid.CheckboxSelectionModel({singleSelect: false});
var cm = new Ext.grid.ColumnModel(
{
	columns:[new Ext.grid.RowNumberer(),sm
	<#list table.columns as column>
	,{header:'${column.columnAlias}',dataIndex:'${column.columnNameLower}'}
	</#list>
	]
	,defaultSortable: true
}
);
var store = new Ext.data.JsonStore({
	root : 'info',
	totalProperty : 'count',
	url :contextPath+ "/${syspath }/${classNameLower }/getListData"
	,fields : new Ext.data.Record.create([
	<#list table.columns as column>
	{name:'${column.columnNameLower}'}<#if column_has_next>,</#if>
	</#list>
	])
});
var addbtn = UD.lib.createAddBtn(
		function() {
			iframeWindow = UD.lib.showIframeWindow(contextPath + "/${syspath }/${classNameLower }/add${className}", 1140, 450, "添加${tableComment}")
	    }
	);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/${syspath }/${classNameLower }/add${className}?id=" + record.get("id"),1140, 450, "修改${tableComment}")
});
var delbtn=UD.lib.createSingleDelBtn(contextPath+"/${syspath }/${classNameLower }/delete${className}");

var bindBtn = UD.lib.createGridCustomBtn("设备绑定","add",function(record) {
	iframeWindow = UD.lib.showIframeWindow(contextPath+ "/${syspath }/${classNameLower }/initPage?id="+ record.get("id"), 970, 410, "设备绑定")
});
var unbindBtn = UD.lib.createGridCustomBtn("设备解绑","edit",function(record) {
		Ext.Msg.confirm('提示', '确定要解绑吗?', function(btn) {
			if (btn != 'yes') {
				return;
			}
			var carId=record.get("id");
			var params={
					carId:carId
		    	}                 
			UD.lib.doAjax(contextPath+ "/${syspath }/${classNameLower }/do${className}",params,function(response, options)
					{
						var resJson=Ext.decode(response.responseText);
						Ext.Msg.alert("提示", resJson.msg,function()
						{
							if (resJson.success==true) {
								grid.loadData();
							}
						}
						);
					}
				); 
		});
});

var grid = new Ext.grid.GridPanel({
	border : false
	,loadMask: {msg: '正在读取数据,请稍等...'}
	,autoScroll:true
	,viewConfig : {forceFit : true}
	,cm:cm
	,sm:sm
	,store: store
	,bbar:getPageTool(store)
	,loadData:function(){
		var thePageSize=this.getBottomToolbar().pageSize;
		this.getStore().load(
		{
			params : {start : 0,limit : thePageSize}
		});	
	}
	,tbar:[addbtn,"-",modifyBtn,"-",delbtn,"-",bindBtn,"-",unbindBtn]
	,region:"center"
	
});
Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[searchForm,grid]
	});
	grid.loadData();
});
