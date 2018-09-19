var hidden_id = UD.lib.createHidden("id");

var priceName = UD.lib.createTextField({
    fieldLabel: '定价模板名称',
    name: 'priceName',
    readOnly: true
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
    readOnly: true
});

var mechanismNames = UD.lib.createTextArea({
    fieldLabel: '适用救援机构',
    name: 'mechanismNames',
    readOnly: true,
    height: 100
});

var basicsCharging = UD.lib.createNumberField({
    fieldLabel: '基础计费(元/次)',
    name: 'basicsCharging',
    minValue: 0,
    anchor: '100%',
    readOnly: true
});

var additionalCharging = UD.lib.createComboBox({
    fieldLabel: '是否包含附加费用',
    valueField: 'id',
    displayField: 'name',
    name: 'additionalCharging',
    store: new Ext.data.SimpleStore({
        data: [['1', '是'], ['2', '否']],
        fields: ["id", "name"]
    }),
    readOnly: true
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
    width: 80,
    readOnly: true
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
    width: 80,
    readOnly: true
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
    width: 80,
    readOnly: true
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
    readOnly: true,
    height: 300
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
                {items: [hidden_id]}
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
                {items: [mechanismNames]}
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
    , buttons: [UD.lib.createIframeCloseBtn()]
});


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