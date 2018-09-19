//非分页用的json数据源
UD.lib.createSimpleJsonStore=function (config)
{
	var isautoLoad=config.autoLoad || false;
	var jsonStore = Ext.create('Ext.data.Store', {
	    proxy: {
	            type: 'ajax',
	            url: config.url,
	            reader: {
	                    type: 'json'
	            }
	    }
	    ,fields: config.fields
	    ,autoLoad : isautoLoad
	});	
	return jsonStore;
}
//分页用的数据源
UD.lib.createPagingJsonStore=function (config)
{
	var isautoLoad=config.autoLoad || false;
	var jsonStore = Ext.create('Ext.data.Store', {
	    proxy: {
	            type: 'ajax',
	            url: config.url,
	            actionMethods : {
	            	read : config.actionMethods || 'POST' //这里不设置的话，默认是发送get请求，get请求的话传中文，后台会有乱码，ext3.4的分页数据源也是发送post请求
	            },
	            reader: {
	                    type: 'json',
	                    rootProperty: config.rootProperty || 'info',
	                    totalProperty: config.totalProperty || 'count'
	            }
	    }
	    ,pageSize:config.pageSize || 10//默认每页显示10条数据
	    ,fields: config.fields
	    ,autoLoad : isautoLoad
	});	
	return jsonStore;
}