<#include "/platform/include/java_copyright.include">
<#assign className = table.className>   

<#assign classNameLower = className?uncap_first>
<#-- 解析如包含下线，就去掉大写字母 -->
package ${basepackage}.common.dao;

import java.util.Map;
import java.util.List;

/**
 * ${className}Dao.xml 文件配置相应的方法
 * @author 
 * 
 */
public interface ${className}Dao{
	
	// 根据id获取
	Map<String, Object> getById(String id);
	// 保存
	void save${className}(Map< String, Object > map);
	// 更新
	void update${className}(Map< String, Object > map);
	// 删除
	void deleteById(String id);
	// 列表分页
	List<Map<String, Object>> getListData(Map< String, Object > map);
	Long getListDataCount(Map<String, Object> map);
	// 最大id值
	int getMaxId();
	// 批量删除
	void batchDeleteByIds(List<Long> ids);
	// 总数
	int count(Map<String, Object> map);
	// 获取全部内容
	List<Map<String, Object>> findAll();
	//	列表分页
	List<Map<String, Object>> findByPage(Map<String, Object> map) ;
		
}
