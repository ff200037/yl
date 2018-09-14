var hidden_id = UD.lib.createHidden("id");
var priceName = UD.lib.createTextField({
    fieldLabel: '定价模板名称',
    name: 'priceName',
    allowBlank: false
});
var rescueObject = UD.lib.createComboBox({
    fieldLabel: '救援项',
    valueField: 'id',
    displayField: 'name',
    name: 'rescueObject',
    store: UD.lib.createSimpleJsonStore({
        fields: ["id", "name"],
        url: contextPath
        + "/price/template/getRescueObject",
        autoLoad: true
    }),
    allowBlank: false
});
var basicsCharging = UD.lib.createTextField({
    fieldLabel: '基础计费(元/次)',
    name: 'basicsCharging',
    allowBlank: false
});
var additionalCharging = UD.lib.createComboBox({
    fieldLabel: '是否需要附加费用',
    valueField: 'id',
    displayField: 'name',
    name: 'additionalCharging',
    store: new Ext.data.SimpleStore({
        data: [['1', '是'], ['2', '否']],
        fields: ["id", "name"]
    }),
    listeners:{
        "select" : function(){
       		var value = additionalCharging.getValue()
       		if(value == "1"){
       			serviceMileage.setDisabled(false);
       			exceedCharging.setDisabled(false);
       			exceedChargingMileage.setDisabled(false);
       		}else if(value == "2"){
       			serviceMileage.setDisabled(true);
       			exceedCharging.setDisabled(true);
       			exceedChargingMileage.setDisabled(true);
                serviceMileage.setValue("");
                exceedCharging.setValue("");
                exceedChargingMileage.setValue("");
       		}
      	},
      	triggerAction : 'all'
	},	
    allowBlank: false
});

var serviceMileage = UD.lib.createTextField({
	x: 150,
    y: 0,
    name: 'serviceMileage',
    width: 80
});


var exceedCharging = UD.lib.createTextField({
	x: 370,
    y: 0,
    name: 'exceedCharging',
    width: 80
});

var exceedChargingMileage = UD.lib.createTextField({
	x: 490,
    y: 0,
    name: 'exceedChargingMileage',
    width: 80
});

var chargingExplain = UD.lib.createTextArea({
    fieldLabel: '其他说明',
    name: 'chargingExplain',
    allowBlank: false
});

var addForm = new Ext.FormPanel({
    bodyPadding: 5,
    layout: 'column',
    defaults: {
        layout: 'form',
        xtype: 'container',
        columnWidth: 1
    },
    items: [
        {
            items: [
                hidden_id, priceName, rescueObject, basicsCharging,
                additionalCharging
            ]
        },
        {
        	layout: 'absolute',
        	width: 300,
            height: 40,
            x:0,
            y:10,
            defaultType: 'textfield',
            items: [
	            {
	                x: 30,
	                y: 0,
	                xtype:'label',
	                text: '每次服务里程不超过：'
	            },
	            serviceMileage
	            ,
	            {
	                x: 240,
	                y: 0,
	                xtype:'label',
	                text: '公里'
	            },
	            {
	                x: 300,
	                y: 0,
	                xtype:'label',
	                text: '超出部分按：'
	            },
	            exceedCharging
	            ,
	            {
	                x: 460,
	                y: 0,
	                xtype:'label',
	                text: '元 / '
	            },
	            exceedChargingMileage
	            ,
	            {
	                x: 575,
	                y: 0,
	                xtype:'label',
	                text: '公里 加收'
	            },
            
            ]
        },
        {
            items: [
                chargingExplain
            ]
        }
    ]

    , buttonAlign: "center"
    , buttons: [UD.lib.createSaveBtn(saveAction), UD.lib.createIframeCloseBtn()]
});


function saveAction() {
    var theForm = this.findParentByType("form");
    var basicForm = theForm.getForm();
    if (basicForm.isValid()) {
        UD.lib.doFormSubmit(basicForm, contextPath + '/price/template/savePrice',
            function (action) {
                Ext.MessageBox.alert('提示', action.result.msg,
                    function () {
                        parent.iframeWindow.hide();
                        parent.grid.getStore().reload();
                    });
            })
    }
}


Ext.onReady(function () {
    new Ext.Viewport({
        layout: "fit",
        items: [addForm]
    });

    if (jsonParams.id) {
        UD.lib.doFormLoadData(addForm, contextPath + '/price/template/queryPriceById?id=' + jsonParams.id, function (action) {

            }
        );
    }

});