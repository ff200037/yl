var hidden_id = UD.lib.createHidden("id");
var hidden_mechanismIds = UD.lib.createHidden("mechanismIdStrs");

var priceName = UD.lib.createTextField({
    fieldLabel: '定价模板名称',
    name: 'priceName',
    allowBlank: false
});

var rescueObject = UD.lib.createComboBox({
    fieldLabel: '救 援 项 目',
    valueField: 'id',
    displayField: 'name',
    name: 'rescueObject',
    store: UD.lib.createSimpleJsonStore({
        fields: ["id", "name"],
        url: contextPath
        + "/price/template/getDictionaryDataListByCode?code=rescue_object",
        autoLoad: true
    }),
    allowBlank: false
});

var basicsCharging = UD.lib.createNumberField({
    fieldLabel: '基础计费(元/次)',
    name: 'basicsCharging',
    minValue: 0,
    anchor: '100%',
    allowBlank: false
});

var mechanismNames = UD.lib.createTextArea({
    fieldLabel: '适用救援机构',
    name: 'mechanismNames',
    readOnly: true,
    height: 100
});

var getMechanismButton = new Ext.Button({
    text: '选择',
    handler: getMechanism
});
/*var clearMechanismButton = new Ext.Button({
    text: '清空',
    handler: clearMechanism
});*/

var additionalCharging = UD.lib.createComboBox({
    fieldLabel: '是否包含附加费用',
    valueField: 'id',
    displayField: 'name',
    name: 'additionalCharging',
    store: new Ext.data.SimpleStore({
        data: [['1', '是'], ['2', '否']],
        fields: ["id", "name"]
    }),
    listeners: {
        "select": function () {
            var value = additionalCharging.getValue()
            if (value == "1") {
                serviceMileage.setDisabled(false);
                exceedCharging.setDisabled(false);
                exceedChargingMileage.setDisabled(false);
                serviceMileage.allowBlank = false;
                exceedCharging.allowBlank = false;
                exceedChargingMileage.allowBlank = false;
            } else if (value == "2") {
                serviceMileage.setDisabled(true);
                exceedCharging.setDisabled(true);
                exceedChargingMileage.setDisabled(true);
                serviceMileage.allowBlank = true;
                exceedCharging.allowBlank = true;
                exceedChargingMileage.allowBlank = true;
                serviceMileage.setValue("");
                exceedCharging.setValue("");
                exceedChargingMileage.setValue("");
            }
        },
        triggerAction: 'all'
    },
    allowBlank: false
});

var label1 = new Ext.form.Label({
    x: 30,
    y: 0,
    xtype: 'label',
    text: '每次服务里程不超过：'
});
var serviceMileage = UD.lib.createNumberField({
    x: 150,
    y: 0,
    name: 'serviceMileage',
    minValue: 0,
    width: 80
});
var label2 = new Ext.form.Label({
    x: 240,
    y: 0,
    xtype: 'label',
    text: '公里'
});
var label3 = new Ext.form.Label({
    x: 280,
    y: 0,
    xtype: 'label',
    text: '超出部分按：'
});
var exceedCharging = UD.lib.createNumberField({
    x: 350,
    y: 0,
    name: 'exceedCharging',
    minValue: 0,
    width: 80
});
var label4 = new Ext.form.Label({
    x: 440,
    y: 0,
    xtype: 'label',
    text: '元 / '
});
var exceedChargingMileage = UD.lib.createNumberField({
    x: 470,
    y: 0,
    name: 'exceedChargingMileage',
    minValue: 1,
    width: 80
});
var label5 = new Ext.form.Label({
    x: 555,
    y: 0,
    xtype: 'label',
    text: '公里 加收'
});


var chargingExplain = UD.lib.createTextArea({
    fieldLabel: '其 他 说 明',
    name: 'chargingExplain',
    allowBlank: false
});

var addForm = new Ext.FormPanel({
    bodyPadding: 5,
    layout: 'column',
    defaults: {
        xtype: 'container',
        columnWidth: 1
    },
    items: [
        {
            layout: 'column',
            defaults: {
                layout: 'form',
                xtype: 'container',
                columnWidth: .5
            },
            items: [
                {items: [hidden_id, hidden_mechanismIds]}
                , {items: [priceName]}

            ]
        },
        {
            layout: 'column',
            defaults: {
                layout: 'form',
                xtype: 'container',
                columnWidth: .5
            },
            items: [
                {items: [rescueObject]}

            ]
        },
        {
            layout: 'column',
            defaults: {
                layout: 'form',
                xtype: 'container',
                columnWidth: .5,
            },
            items: [
                {items: [mechanismNames]},
                {items: [getMechanismButton]},
                //{items: [clearMechanismButton]}, 
            ]
        },
        {
            layout: 'column',
            defaults: {
                layout: 'form',
                xtype: 'container',
                columnWidth: .5
            },
            items: [
                {items: [basicsCharging]}
            ]
        },
        {
            layout: 'column',
            defaults: {
                layout: 'form',
                xtype: 'container',
                columnWidth: .5,
                width: 50,
            },
            items: [
                {items: [additionalCharging]}
            ]
        },
        {
            layout: 'absolute',
            width: 300,
            height: 40,
            x: 0,
            y: 10,
            defaultType: 'textfield',
            items: [
                label1,
                serviceMileage,
                label2,
                label3,
                exceedCharging,
                label4,
                exceedChargingMileage,
                label5,
            ]
        },
        {
            layout: 'column',
            defaults: {
                layout: 'form',
                xtype: 'container',
                columnWidth: .5
            },
            items: [
                {items: [chargingExplain]}
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

function getMechanism() {
    iframeWindow = UD.lib.showIframeWindow(contextPath + "/price/template/getMechanismPage", 800, 400, "选择救援机构")
}

/*function clearMechanism() {
    hidden_mechanismIds.setValue("");
    mechanismNames.setValue("");
}*/

Ext.onReady(function () {
    new Ext.Viewport({
        layout: "fit",
        items: [addForm]
    });

    if (jsonParams.id) {
        UD.lib.doFormLoadData(addForm, contextPath + '/price/template/queryPriceById?id=' + jsonParams.id, function (action) {
                rescueObject.setReadOnly(true);
                
                var value = additionalCharging.getValue();
                if (value == "2") {
                    serviceMileage.setDisabled(true);
                    exceedCharging.setDisabled(true);
                    exceedChargingMileage.setDisabled(true);
                }
            }
        );
    }

});