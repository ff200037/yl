var hidden_id = UD.lib.createHidden("id");
var fkDictionary = UD.lib.createHidden("fkDictionary");
var commonWidth=200;
var  dicCatName= UD.lib.createTextField({
	fieldLabel : '字典数据分类名称',
	name:"dictionaryName",
	width : commonWidth
	,disabled:true
});
var dictdataName = UD.lib.createTextField({
	fieldLabel : '字典数据名称',
	name : 'dictdataName',
	allowBlank : false,
	width : commonWidth
});
var dictdataValue = UD.lib.createTextField({
	fieldLabel : '字典数据值',
	name : 'dictdataValue',
	allowBlank : false,
	width : commonWidth
});
var isEnable = UD.lib.createComboBox({
	fieldLabel : '是否启用',
	valueField : 'id',
	displayField : 'name',
	hiddenName : 'isEnable',
	store : new Ext.data.SimpleStore({
		data : [ [ 'T', '是' ], [ 'F', '否' ] ],
		fields : [ "id", "name" ]
	}),
	value:'T',
	allowBlank : false,
	width : commonWidth
});
var remark = UD.lib.createTextField({
	fieldLabel : '备注',
	name : 'remark',
	width : commonWidth
});

var addForm=new Ext.FormPanel(
{
	frame:true
	,border:false
	,autoWidth:true
	,labelAlign:"right"
	,labelWidth:110
	,items:[
		hidden_id
		,fkDictionary,dicCatName,dictdataName,dictdataValue,isEnable,remark
	]
	,buttonAlign:"center"
	,buttons : [UD.lib.createSaveBtn(saveAction)]
});

function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath+ '/system/dictionaryData/saveDictionaryData',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
            	parent.iframeWindow.hide();
            	parent.grid.loadData();
            });
        })
    }	
}


Ext.onReady(function() {
    new Ext.Viewport({
        layout: "fit",
        items: [addForm]
    });
    if (jsonParams.id) {
    	UD.lib.doFormLoadData(addForm, contextPath+ '/system/dictionaryData/getById?id='+jsonParams.id, function(action)
	    	{
	    		
	    	}		
    	);
	} else {
		fkDictionary.setValue(jsonParams.fkDictionary);
		dicCatName.setValue(jsonParams.dicCatName);
	}
    
});