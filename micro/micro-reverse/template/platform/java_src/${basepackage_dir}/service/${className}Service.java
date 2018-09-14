<#include "/platform/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

<#include "/platform/include/service.include">

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ${className}Service {
	
	public JSONObject getListData(Map< String, Object > map);

	public JSONObject save${className}(Map< String, Object > map);

	public JSONObject get${className}ById(Map< String, Object > map);

	public JSONObject del${className}(Map< String, Object > map);

	/**
	 * 多删除
	 * @param ids
	 */
	public void batchDelete${className}(List<Long> ids);
	
	/**
	 * 全部
	 */
	public List<Map<String, Object>> getAll${className}List();

	/**
	 * 分页
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findByPage(Map<String, Object> map);
	
}
