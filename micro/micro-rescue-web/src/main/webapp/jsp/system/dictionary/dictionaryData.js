function isEnableRenderer(val)
{
	if (val=="T") {
		return "是";
	} else {
		return "否";
	}
}
var sm=new Ext.grid.CheckboxSelectionModel({singleSelect: false});
var cm = new Ext.grid.ColumnModel(
{
	columns:[new Ext.grid.RowNumberer(),sm
	,{header:'字典分类名称',dataIndex:'dictionaryName'}
	,{header:'字典分类编码',dataIndex:'dictionaryCode',width:160}
	,{header:'字典数据名称',dataIndex:'dictdataName'}
	,{header:'字典数据值',dataIndex:'dictdataValue'}
	,{header:'是否启用',dataIndex:'isEnable',renderer:isEnableRenderer}
	,{header:'添加时间',dataIndex:'createDate',width:110}
	,{header:'备注',dataIndex:'remark'}
	
	]
	,defaultSortable: true
}
);
var store = new Ext.data.JsonStore({
	root : 'info',
	totalProperty : 'count',
	url :contextPath+ "/system/dictionaryData/getListData"
	,fields : new Ext.data.Record.create([
		{name:'id'}
		,{name:'dictionaryName'}
		,{name:'dictionaryCode'}
		,{name:'dictdataName'}
		,{name:'dictdataValue'}
		,{name:'isEnable'}
		,{name:'remark'}
		,{name:'createDate'}
				
	])
});
var addbtn = UD.lib.createAddBtn(
	function() {
	    var selectedNode = treePanel.getSelectionModel().getSelectedNode();
	    if (selectedNode) {
	        var params = {
	            fkDictionary: selectedNode.attributes.id,
	            dicCatName: selectedNode.attributes.text
	        };
	        iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/dictionaryData/addDictionaryDataPage?" + Ext.urlEncode(params), 350, 0, "添加字典数据")
	    } else {
	        Ext.Msg.alert("提示", "请选择一个字典分类");
	    }
    }
);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	    iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/dictionaryData/addDictionaryDataPage?id=" + record.get("id"), 350, 0, "修改字典数据")
    }
);

var delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/dictionaryData/delDictionaryData");
var grid = new Ext.grid.GridPanel({
	title:"字典数据列表",
	border : false
	,loadMask: {msg: '正在读取数据,请稍等...'}
	,autoScroll:true
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
	,region: "center"
	,tbar:[addbtn,"-",modifyBtn,"-",delbtn]
	
});



Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[treePanel,grid]
	});
});

