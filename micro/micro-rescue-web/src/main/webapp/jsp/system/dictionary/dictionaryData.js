function isEnableRenderer(val)
{
	if (val=="T") {
		return "是";
	} else {
		return "否";
	}
}
var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/system/dictionaryData/getListData",
	fields : [
		{name:'id'}
		,{name:'dictionaryName'}
		,{name:'dictionaryCode'}
		,{name:'dictdataName'}
		,{name:'dictdataValue'}
		,{name:'isEnable'}
		,{name:'remark'}
		,{name:'createDate'}	
	]
});
var addbtn = UD.lib.createAddBtn(
	function() {
	    var selectedNode = treePanel.getSelectionModel().getSelection();
	    if (selectedNode.length==1) {
	        var params = {
	            fkDictionary: selectedNode[0].get("id"),
	            dicCatName:selectedNode[0].get("text")
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

var grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: false,
    title:"字典数据列表",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text:'字典分类名称',dataIndex:'dictionaryName'}
		,{text:'字典分类编码',dataIndex:'dictionaryCode',width:160}
		,{text:'字典数据名称',dataIndex:'dictdataName'}
		,{text:'字典数据值',dataIndex:'dictdataValue'}
		,{text:'是否启用',dataIndex:'isEnable',renderer:isEnableRenderer}
		,{text:'添加时间',dataIndex:'createDate',width:110}
		,{text:'备注',dataIndex:'remark'}
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,tbar:[addbtn,"-",modifyBtn,"-",delbtn]
    ,region: "center"
});


Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[treePanel,grid]
	});
});

