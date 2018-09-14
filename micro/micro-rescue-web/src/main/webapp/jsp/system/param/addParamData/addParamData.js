var hidden_id = UD.lib.createHidden("id");
var fkParamCat = UD.lib.createHidden("fkParamCat");
var  paramCatName= UD.lib.createTextField({
	fieldLabel : '参数分类名称',
	name:"catName"
	,disabled:true
});

var paramDataName = UD.lib.createTextField({
	fieldLabel : '参数名称',
	name : 'paramDataName',
	allowBlank : false
});
var paramDataCode = UD.lib.createTextField({
	fieldLabel : '参数编码',
	name : 'paramDataCode',
	allowBlank : false
});
var paramDataValue = UD.lib.createTextField({
	fieldLabel : '参数值',
	name : 'paramDataValue',
	allowBlank : false
});
var isEnable = UD.lib.createComboBox({
	fieldLabel : '是否启用',
	valueField : 'id',
	displayField : 'name',
	name : 'isEnable',
	store : new Ext.data.SimpleStore({
		data : [ [ 'T', '是' ], [ 'F', '否' ] ],
		fields : [ "id", "name" ]
	}),
	value:'T',
	allowBlank : false
});
var paramDataRemark = UD.lib.createTextField({
	fieldLabel : '参数说明',
	name : 'paramDataRemark'
});

var addForm = new Ext.FormPanel({
	bodyPadding:5,
	layout: 'column',
 	fieldDefaults: {
 		labelAlign: 'right'
//        ,labelWidth: 100
    },	
    defaults: {
        layout: 'form'
        ,xtype: 'container'
        ,columnWidth:1
    },
	items: [
		{items: [hidden_id,fkParamCat,paramCatName,paramDataName,paramDataCode,paramDataValue,isEnable,paramDataRemark]}
    ]

	,buttonAlign : "center"
	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});

function saveAction() {
	var theForm = this.findParentByType("form");
	var basicForm = theForm.getForm(); 
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath+ '/system/paramData/saveParamData',
        function(action) {
            Ext.MessageBox.alert('提示', action.result.msg,
            function() {
            	parent.iframeWindow.hide();
            	parent.grid.getStore().reload();
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
    	UD.lib.doFormLoadData(addForm, contextPath+ '/system/paramData/getById?id='+jsonParams.id, function(action)
	    	{
	    		
	    	}		
    	);
	} else {
		fkParamCat.setValue(jsonParams.fkParamCat);
		paramCatName.setValue(jsonParams.paramCatName);
	}
    
});