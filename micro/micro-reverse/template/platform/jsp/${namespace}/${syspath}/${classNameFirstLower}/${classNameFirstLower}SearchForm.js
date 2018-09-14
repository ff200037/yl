<#include "/common/include/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
// 属性
<#list table.columns as column>
<#if column.sqlTypeName=='NVARCHAR2'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
});
<#elseif column.sqlTypeName=='bigint'>
	#${r"{"}${column.columnNameLower},jdbcType=BIGINT${r"}"} <#if column_has_next>,</#if>
<#elseif column.sqlTypeName=='varchar'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
});
<#elseif column.sqlTypeName=='LONGTEXT'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
});
<#elseif column.sqlTypeName=='longtext'>
var ${column.columnNameLower} = UD.lib.createTextField({
	fieldLabel:'${column.columnAlias}'
});
<#elseif column.sqlTypeName=='int'>
var ${column.columnNameLower} = UD.lib.createComboBox({
	fieldLabel : '${column.columnAlias}',
	valueField : 'id',
	displayField : 'name',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	})
});
<#elseif column.sqlTypeName=='NUMBER'>
var ${column.columnNameLower} = UD.lib.createComboBox({
	fieldLabel : '${column.columnAlias}',
	valueField : 'id',
	displayField : 'name',
	store : new Ext.data.JsonStore({
		fields : [ "id", "name" ],
		url : contextPath + "/system/dictionaryData/getListDataByCode?dictionaryCode=ser_type",
		autoLoad : true
	})
});
<#elseif column.sqlTypeName=='timestamp'>
var start${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '${column.columnAlias}'
	,format : 'Y-m-d H:i:s'
	,name:'start${column.columnName}'
	,width : commonWidth
});

var end${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '至'
	,format : 'Y-m-d H:i:s'
	,name:'end${column.columnName}'
	,width : commonWidth
});
<#elseif column.sqlTypeName=='TIMESTAMP'>
var start${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '${column.columnAlias}'
	,format : 'Y-m-d H:i:s'
	,name:'start${column.columnName}'
	,width : commonWidth
});

var end${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '至'
	,format : 'Y-m-d H:i:s'
	,name:'end${column.columnName}'
	,width : commonWidth
});
<#elseif column.sqlTypeName=='double'>
//double
<#elseif column.sqlTypeName=='DOUBLE'>
//double
<#elseif column.sqlTypeName=='DATE'>
var start${column.columnName} = UD.lib.createDateField({
	fieldLabel : '${column.columnAlias}'
	,format : 'Y-m-d'
	,name:'start${column.columnName}'
	,width : commonWidth
});

var end${column.columnName} = UD.lib.createDateField({
	fieldLabel : '至'
	,format : 'Y-m-d'
	,name:'end${column.columnName}'
	,width : commonWidth
});
<#elseif column.sqlTypeName=='date'>
var start${column.columnName} = UD.lib.createDateField({
	fieldLabel : '${column.columnAlias}'
	,format : 'Y-m-d'
	,name:'start${column.columnName}'
	,width : commonWidth
});

var end${column.columnName} = UD.lib.createDateField({
	fieldLabel : '至'
	,format : 'Y-m-d'
	,name:'end${column.columnName}'
	,width : commonWidth
});
<#elseif column.sqlTypeName=='DATETIME'>
var start${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '${column.columnAlias}'
	,format : 'Y-m-d H:i:s'
	,name:'start${column.columnName}'
	,width : commonWidth
});

var end${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '至'
	,format : 'Y-m-d H:i:s'
	,name:'eTime'
	,width : commonWidth
});
<#elseif column.sqlTypeName=='datetime'>
var start${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '${column.columnAlias}'
	,format : 'Y-m-d H:i:s'
	,name:'sTime'
	,width : commonWidth
});

var end${column.columnName} = UD.lib.createDateTimeField({
	fieldLabel : '至'
	,format : 'Y-m-d H:i:s'
	,name:'eTime'
	,width : commonWidth
});
<#else>
	#${r"{"}${column.columnNameLower},jdbcType=${column.sqlTypeName}${r"}"} <#if column_has_next>,</#if>
</#if>
</#list>   
// 属性end

// 重置按钮
var resetBtn = UD.lib.createResetBtn();
// 查询按钮
var searchbtn = UD.lib.createSearchBtn(function()
	{
		grid.getStore().baseParams={
			<#list table.columns as column>
			${column.columnNameLower} : ${column.columnNameLower}.getValue() <#if column_has_next>,</#if>
			</#list>
		};
		grid.loadData();
	}
);
// 查询表单
var searchForm = new Ext.FormPanel(
{
	frame:true,
	border:false,
	labelAlign:"right",
	labelWidth:130,
	items:[
		{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			// 查询属性
			items:[<#list table.columns as column>{items:${column.columnNameLower}}<#if column_has_next>,</#if></#list>]
		}
		,{
			layout:"column",
			defaults:{
				layout : 'form'
				,columnWidth:.3
			},
			items:[{items:modelName}
//			,{items:aaaa}
			]
		}
	]
	,region:"north"
	,height:100
	,buttonAlign:"center"
	,buttons:[searchbtn,resetBtn]	
}
);

