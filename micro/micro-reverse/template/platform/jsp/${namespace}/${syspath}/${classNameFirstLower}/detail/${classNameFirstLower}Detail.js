<#include "/common/include/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
var commonWidth = 200;
//属性
<#list table.columns as column>
<#if !column.pk>
<#if column.sqlTypeName=='NVARCHAR2'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
	,name:'${column.columnNameLower}'
	,allowBlank : false
	,width:commonWidth
});
<#elseif column.sqlTypeName=='bigint'>
	#${r"{"}${column.columnNameLower},jdbcType=BIGINT${r"}"} <#if column_has_next>,</#if>
<#elseif column.sqlTypeName=='varchar'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
	,name:'${column.columnNameLower}'
	,allowBlank : false
	,width:commonWidth
});
<#elseif column.sqlTypeName=='LONGTEXT'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
	,name:'${column.columnNameLower}'
	,allowBlank : false
	,width:commonWidth
});
<#elseif column.sqlTypeName=='longtext'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
	,name:'${column.columnNameLower}'
	,allowBlank : false
	,width:commonWidth
});
<#elseif column.sqlTypeName=='int'>
var ${column.columnNameLower} = UD.lib.createComboBox({
	fieldLabel : '${column.columnAlias}',
	valueField : 'id',
	displayField : 'name',
	hiddenName : '${column.columnNameLower}',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	}),
	allowBlank : false,
	width : commonWidth
});
<#elseif column.sqlTypeName=='NUMBER'>
var ${column.columnNameLower} = UD.lib.createComboBox({
	fieldLabel : '${column.columnAlias}',
	valueField : 'id',
	displayField : 'name',
	hiddenName : '${column.columnNameLower}',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	}),
	allowBlank : false,
	width : commonWidth
});
<#elseif column.sqlTypeName=='timestamp'>
var ${column.columnNameLower} = UD.lib.createDateTimeField({
			fieldLabel : '${column.columnAlias}',
			name : "${column.columnNameLower}",
			hiddenName : '${column.columnNameLower}',
			format : "Y-m-d H:i:s",
			readOnly : true,
			emptyText : '',
			// value:new Date(),
			width : commonWidth
		})
<#elseif column.sqlTypeName=='TIMESTAMP'>
var ${column.columnNameLower} = UD.lib.createDateTimeField({
			fieldLabel : '${column.columnAlias}',
			name : "${column.columnNameLower}",
			hiddenName : '${column.columnNameLower}',
			format : "Y-m-d H:i:s",
			readOnly : true,
			emptyText : '',
			// value:new Date(),
			width : commonWidth
		})
<#elseif column.sqlTypeName=='double'>
//double
<#elseif column.sqlTypeName=='DOUBLE'>
//double
<#elseif column.sqlTypeName=='DATE'>
var ${column.columnNameLower} = UD.lib.createDateField({
			fieldLabel : '${column.columnAlias}',
			name : "${column.columnNameLower}",
			hiddenName : '${column.columnNameLower}',
			format : "Y-m-d",
			readOnly : true,
			emptyText : '',
			// value:new Date(),
			width : commonWidth
		})
<#elseif column.sqlTypeName=='date'>
var ${column.columnNameLower} = UD.lib.createDateField({
			fieldLabel : '${column.columnAlias}',
			name : "${column.columnNameLower}",
			hiddenName : '${column.columnNameLower}',
			format : "Y-m-d",
			readOnly : true,
			emptyText : '',
			// value:new Date(),
			width : commonWidth
		})
<#elseif column.sqlTypeName=='DATETIME'>
var ${column.columnNameLower} = UD.lib.createDateField({
			fieldLabel : '${column.columnAlias}',
			name : "${column.columnNameLower}",
			hiddenName : '${column.columnNameLower}',
			format : "Y-m-d",
			// readOnly : true,
			emptyText : '请选择日期',
			// value:new Date(),
			width : commonWidth
		})
<#elseif column.sqlTypeName=='datetime'>
var ${column.columnNameLower} = UD.lib.createDateField({
			fieldLabel : '${column.columnAlias}',
			name : "${column.columnNameLower}",
			hiddenName : '${column.columnNameLower}',
			format : "Y-m-d",
			// readOnly : true,
			emptyText : '请选择日期',
			// value:new Date(),
			width : commonWidth
		})
<#else>
	var ${column.columnNameLower} = UD.lib.createTextField({
		fieldLabel:'${column.columnAlias}'
		,name:'${column.columnNameLower}'
		,allowBlank : false
		,width:commonWidth
	}); <#if column_has_next>,</#if>
</#if>
</#if>
</#list>   
// 属性end

var FieldSet = new Ext.form.FieldSet(
{
	title:"车辆基本信息"
	,labelAlign:"right"
	,labelWidth:90
	,items:[
		<#list table.columns as column>
		<#if !column.pk>
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.5
			},
			items:[{items:${column.columnNameLower}}]
		}<#if column_has_next>,</#if>
		</#if>
		</#list>
	]
}
);

var addForm = new Ext.FormPanel({
	frame : true,
	border : false,
	autoHeight : true,
	autoWidth : true,
	labelAlign : "right",
	labelWidth : 110
	,items:[FieldSet]
	
//	,buttonAlign : "center"
//	,buttons : [ UD.lib.createSaveBtn(saveAction) ]
});


Ext.onReady(function() {
	new Ext.Viewport({
		layout : "fit",
		items : [ addForm ]
	});
	if (jsonParams.id) {
		UD.lib.doFormLoadData(addForm, contextPath + '/${syspath }/${classNameLower }/getById?id='+ jsonParams.id, function(action) {
				
			});
	} else {
	}

});