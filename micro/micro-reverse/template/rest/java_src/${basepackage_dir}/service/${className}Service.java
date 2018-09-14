<#include "/rest/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

<#include "/rest/include/service.include">

import ${basepackage}.bean.${className};

public interface ${className}Service {

	/**
	 * 保持和更新
	 */
	public void save${className}(${className} ${classNameLower});

	/**
	 * 删除
	 */
	public void delete${className}ById(Long id);
	
	/**
	 * 多删除
	 * @param ids
	 */
	public void batchDelete${className}(List<Long> ids);
	
	/**
	 * 获取对象
	 */
	public ${className} get${className}ById(Long id);
	
	/**
	 * 全部
	 */
	public List<${className}> getAll${className}List();

	/**
	 * 分页
	 * @param map
	 * @return
	 */
	public List<${className}> findByPage(Map<String, Object> map);
	
}
