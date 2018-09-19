
var store = UD.lib.createPagingJsonStore({
    url: contextPath + "/price/template/getMechanismData",
    fields: [
        {name: 'id'}
        , {name: 'mechanismName'}
        , {name: 'cityName'}
        , {name: 'legalPerson'}
        , {name: 'mechanismAddr'}
        , {name: 'mechanismnNumber'}
        , {name: 'person'}
        , {name: 'personNumber'}
        
    ]
});

var sureBtn=UD.lib.createSureBtn(function(){
	var theGrid=this.ownerCt.ownerCt;//获取所属的grid.Panel组件
	var theSelectionModel=theGrid.getSelectionModel();
	if (theSelectionModel.getCount() == 0) {
		Ext.Msg.alert("提示", "请选择一条记录");
		return;
	}		
	
	var mechanismIds = "";
	var mechanismNames = "";
	
	var record=theSelectionModel.getSelected();
	for(var i=0; i<record.length; i++){
		mechanismIds += (record.items[i].data.id + ",");
		mechanismNames += (record.items[i].data.mechanismName + ",");
	}
	
	//console.log(mechanismIds);
	//console.log(mechanismNames);
	mechanismIds = mechanismIds.substr(0, mechanismIds.length-1);
	mechanismNames = mechanismNames.substr(0, mechanismNames.length-1);
	
	parent.hidden_mechanismIds.setValue(mechanismIds);
	parent.mechanismNames.setValue(mechanismNames);
	parent.iframeWindow.hide();

})

var grid = Ext.create('Ext.grid.Panel', {
    selModel: new Ext.selection.CheckboxModel(),
    store: store,
    columnLines: true,
    forceFit: true,
    title: "救援机构列表",
    columns: [
        {xtype: "rownumberer", align: "center", text: "序号", width: 50}
        , {text: '机构名称', dataIndex: 'mechanismName', width: 150}
        , {text: '所属城市', dataIndex: 'cityName', width: 70}
        //, {text: '法人', dataIndex: 'legalPerson', width: 50}
        , {text: '地址', dataIndex: 'mechanismAddr', width: 150}
        , {text: '服务电话', dataIndex: 'mechanismnNumber', width: 100}
        , {text: '联系人', dataIndex: 'person', width: 70}
        , {text: '联系人电话', dataIndex: 'personNumber', width: 100}
    ]
    , bbar: UD.lib.getPagebar(store)
    , region: "center"
    , tbar: [sureBtn, UD.lib.createIframeCloseBtn()]
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