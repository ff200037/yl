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
	url : contextPath+ "/system/paramData/getListData",
	fields : [
		{name:'id'}
		,{name:'catName'}
		,{name:'paramDataName'}
		,{name:'paramDataCode'}
		,{name:'paramDataValue'}
		,{name:'paramDataRemark'}
		,{name:'isEnable'}
	]
});
var addbtn = UD.lib.createAddBtn(
	function() {
	    var selectedNode = treePanel.getSelectionModel().getSelection();
	    if (selectedNode.length==1) {
	        var params = {
	            fkParamCat: selectedNode[0].get("id"),
	            paramCatName: selectedNode[0].get("text")
	        };
	        iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/paramData/addParamDataPage?" + Ext.urlEncode(params), 500, 370, "添加参数")
	    } else {
	        Ext.Msg.alert("提示", "请选择一个参数分类");
	    }
    }
);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
	    iframeWindow = UD.lib.showIframeWindow(contextPath + "/system/paramData/addParamDataPage?id=" + record.get("id"), 500, 370, "修改参数")
    }
);
var delbtn=UD.lib.createSingleDelBtn(contextPath+"/system/paramData/delParamData");
var grid=Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title:"参数列表",
   	columns: [
   		{ xtype: "rownumberer",align:"center",text:"序号",width:55}
		,{text:'参数分类',dataIndex:'catName'}
		,{text:'参数名称',dataIndex:'paramDataName'}
		,{text:'参数编码',dataIndex:'paramDataCode'}
		,{text:'参数值',dataIndex:'paramDataValue'}
		,{text:'是否启用',dataIndex:'isEnable',renderer:isEnableRenderer,width:80}
		,{text:'参数说明',dataIndex:'paramDataRemark'}
    ]
    ,bbar:UD.lib.getPagebar(store)
    ,region: "center"
    ,tbar:[addbtn,modifyBtn,delbtn]
});
var rightCenterPanel= new Ext.Panel(
{
	border:false,
	region:"center",
	
	layout:"border",
	items:[searchForm,grid]
	
}
);


Ext.onReady(function(){
	new Ext.Viewport({
		layout:"border",
		items:[treePanel,rightCenterPanel]
	});
});

