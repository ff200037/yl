var hidden_id = UD.lib.createHidden("id");

var paramCatName= UD.lib.createTextField({
	fieldLabel : '优惠券名称',
	name:'catName'
	
});

var paramDataName = UD.lib.createTextField({
	fieldLabel : '优惠券类型',
	name : 'paramDataName'
});
var paramDataCode = UD.lib.createTextField({
	fieldLabel : '面额/折扣',
	name : 'paramDataCode'
});
var paramDataValue = UD.lib.createTextField({
	fieldLabel : '发行数量',
	name : 'paramDataValue'
});

var paramDataRemark = UD.lib.createTextField({
	fieldLabel : '使用数量',
	name : 'paramDataRemark'
});

var addForm = new Ext.FormPanel({
	frame : true,
	border : false,
	title : "优惠券信息",
	items: [
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			items:[{items:paramCatName},{items:paramDataName},{items:paramDataCode}]
		},
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			items:[{items:paramDataValue},{items:paramDataRemark}]
		}
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
	
		paramCatName.setValue(jsonParams.paramCatName);
	}
    
});