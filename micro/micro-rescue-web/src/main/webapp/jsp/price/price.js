function additionalChargingRenderer(val)
{
	if (val==1) {
		return "是";
	} 
	if (val==2) {
		return "否";
	} 
}	

var store = UD.lib.createPagingJsonStore(
    {
        url: contextPath + "/price/template/getPriceData",
        fields: [
            {name: 'id'}
            , {name: 'priceName'}
            , {name: 'rescueObject'}
            , {name: 'basicsCharging'}
            , {name: 'additionalCharging'}
            , {name: 'serviceMileage'}
            , {name: 'exceedCharging'}
            , {name: 'exceedChargingMileage'}
            , {name: 'chargingExplain'}
            , {name: 'createTime'}
            , {name: 'rescueObjectName'}
            
        ]
    });

var addBtn = UD.lib.createAddBtn(
    function() {
        iframeWindow = UD.lib.showIframeWindow(contextPath + "/price/template/addPricePage", 800, 400, "添加定价模板")
    }
);
var modifyBtn = UD.lib.createModifyBtn(function(record) {
    iframeWindow = UD.lib.showIframeWindow(contextPath+ "/price/template/addPricePage?id=" + record.get("id"),800, 400, "修改定价模板")
});
var delBtn = UD.lib.createSingleDelBtn(contextPath + "/price/template/deletePriceById");

var grid = Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title: "定价模板列表",
    columns: [
        {xtype: "rownumberer", align: "center", text: "序号", width: 55}
        , {text: '定价模板名称', dataIndex: 'priceName'}
        , {text: '服务项', dataIndex: 'rescueObjectName'}
        , {text: '基础计费', dataIndex: 'basicsCharging',width:50}
        //, {text: '是否有附加项', dataIndex: 'additionalCharging',renderer:additionalChargingRenderer,width:80}
        , {text: '附加项说明', dataIndex: 'chargingExplain',width:200}
        ,{text:'适用救援的机构',dataIndex:''}
        ,{text:'模板创建时间',dataIndex:'createTime'}
        ,{text:'服务定价详情',dataIndex:''}
    ]
    , bbar: UD.lib.getPagebar(store)
    , region: "center"
    , tbar: [addBtn, modifyBtn, delBtn]
});

grid.on("rowclick", function (thegrid, rowIndex, e) {
        var record = this.getSelectionModel().getSelection()[0];
    }
);

var topPanel= new Ext.Panel(
    {
        border:false,
        region:"center",
        layout:"border",
        items:[searchForm,grid]

    }
);

Ext.onReady(function () {
    new Ext.Viewport({
        layout:"border",
        items:[topPanel]
    });
    store.load();
});