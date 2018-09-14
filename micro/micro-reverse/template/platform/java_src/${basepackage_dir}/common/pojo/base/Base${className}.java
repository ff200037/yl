<#include "/platform/include/macro.include"/>
<#include "/platform/include/java_copyright.include">
<#assign className = table.className> 
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.common.pojo.base;

import ${basepackage}.common.pojo.BaseEntity;

<#include "/platform/include/model.include">

@SuppressWarnings("serial")
public class Base${className} extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	
	//date formats
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	public static final String FORMAT_${column.constantName} = DATE_TIME_FORMAT;
	</#if>
	</#list>
	
	//columns START
	<#list table.columns as column>
	<#if column.javaType=='java.lang.Object'>
	//${column.columnAlias}
	private java.lang.String ${column.columnNameLower};
	<#else>
	//${column.columnAlias}
	private ${column.javaType} ${column.columnNameLower};
	</#if>
	</#list>
	//columns END


<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>


<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
		<#--
	public String get${column.columnName}String() {
		return date2String(get${column.columnName}(), FORMAT_${column.constantName});
	}
	public void set${column.columnName}String(String value) {
		set${column.columnName}(string2Date(value, FORMAT_${column.constantName},${column.javaType}.class));
	}
	-->
		</#if>
		<#if column.javaType=='java.lang.Object'>
	public void set${column.columnName}(java.lang.String ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	public java.lang.String get${column.columnName}() {
		return this.${column.columnNameLower};
	}
		<#else>
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
		</#if>
		
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
}