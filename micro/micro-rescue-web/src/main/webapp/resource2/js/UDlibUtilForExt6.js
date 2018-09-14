//显示窗口
UD.lib.showWindow=function(theForm, windowTitle) {
    var theWindow = theForm.findParentByType("window");
	if (windowTitle && windowTitle!="") {
		theWindow.setTitle(windowTitle);
	}
    theWindow.show();
    theForm.getForm().reset();//显示窗口后清空表单数据
}
UD.lib.showIframeWindow=function(defaultSrc,width,height,title) {
	var config={
		border:false,
		title : title,
		closeAction : 'hide',
		modal : true,
		constrainHeader : true,
		layout : 'fit',
		resizable : true
	};
	
	if (width==0) {
		config.autoWidth=true;
	} else {
		config.width=width;
	}
	if (height==0) {
		config.autoHeight=true;
	} else {
		config.height=height;
	}
	var addWindow = new Ext.Window(config);
	var htmlpanel=new JpkFrame.common.HtmlPanel({
		xtype : 'htmlpanel',
		autoScroll: true,
		layout : 'fit',
		border : false,
		// loadMask:false,关闭进度条
		// loadMask:{} 启用默认的进度条,{}为设置
		// loadMask:{msg:'正在打开页面，请稍等....',msgCls:''}, //启用自定义配置
		loadMask : {},
		defaultSrc : defaultSrc,
		closable : true,
		group : this
		
//		title : "xxxxx",
//		,bodyStyle: {
//			background:"white",
//            padding: '30px'
//        }
//		,bodyStyle: "background:white;padding: 30px"
	});
	var panel=UD.lib.createPanel(defaultSrc);
	addWindow.add(panel);
	addWindow.show();
	return addWindow;
}
UD.lib.createPanel=function(url)
{
	var panel=Ext.create('Ext.panel.Panel', {
		html : "<iframe  width='100%' height='100%' scrolling='yes' frameborder='0' src='"+url+"'></iframe>"
	});	
	return panel;
}
//——————————————————————————————————————————————————————————————————————————————————————————
UD.lib.getPagebar=function(gridStore)
{
	var data = Ext.create('Ext.data.Store', {
	    fields: ['value', 'text'],
	    data: [
	        [10, '10条/页'],
	        [20, '20条/页'],
	        [50, '50条/页'],
	        [100, '100条/页'],
	        [250, '250条/页'],
	        [500, '500条/页'],
	        [1000, '1000条/页'],
	        [2000, '2000条/页'],
	        [3000, '3000条/页']
	    ]
	});
	var currPageSize=gridStore.getPageSize();
	var combobox=Ext.create('Ext.form.ComboBox', {
	    store: data,
	    queryMode: 'local',
	    displayField: 'text',
	    valueField: 'value',
	    editable:false,
	    width:120
	    ,value:currPageSize
		,listeners:{
			select:function(combo, record, eOpts)
			{
				var newPageSize=this.getValue();
				gridStore.setPageSize(newPageSize);
				gridStore.reload();//带上之前的参数和新的分页参数加载数据，看api
				//下面两种加载数据的效果是一样的，只不过会丢失之前的参数
//				gridStore.load();				
//				gridStore.load({
//							params : {
//								start : 0,
//								limit :newPageSize
//							}
//						});
			}
		}	    
	});	
	var pagebar = Ext.create("Ext.toolbar.Paging", {
	    store: gridStore,
	    displayInfo: true,
	    displayMsg: "显示{0}-{1}条，共计{2}条",
	    emptyMsg: "没有数据",
	 	items:[combobox]
	});	
	return pagebar;
}

UD.lib.loadStoreData=function(json)
{
	
	var store=json.store;
	var paramsJson=json.params;//可选
	if (!paramsJson) {
		store.reload();
		return;
	}
	//是否清除之前的参数
	var isClearLastParams=json.isClearLastParams || false;
	if (isClearLastParams==true) {
		store.load(
		{
			params : paramsJson
		});		
	}
	var lastOptions = store.lastOptions || {},
	    lastParams = lastOptions.params ? Ext.clone(lastOptions.params) :{}; // make a copy of the last params so we don't affect future reload() calls
	var newParams=Ext.apply(lastParams,paramsJson);	
	store.load(
	{
		params : newParams
	});		
}
//用法1,相当于grid.getStore().reload()
//UD.lib.loadStoreData(
//{
//	store:grid.getStore()
//}
//);
//用法2
//	UD.lib.loadStoreData(
//	{
//		store:grid.getStore()
//		,params:{
//			keyWord:keyWord.getValue()
//		}
//		,isClearLastParams:false
//	}
//	);


