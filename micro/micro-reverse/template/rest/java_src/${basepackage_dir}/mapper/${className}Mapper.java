<#include "/rest/include/java_copyright.include">
<#assign className = table.className>   

<#assign classNameLower = className?uncap_first>
<#-- 解析如包含下线，就去掉大写字母 -->
package ${basepackage}.mapper;

<#include "/rest/include/mapper.include">

import ${basepackage}.bean.${className};

/**
 * Mapper.xml 文件配置相应的方法
 * @author 
 * 
 */
public interface ${className}Mapper{
	
	// 最大id值
	int getMaxId();

	// 添加
	void add(${className} ${classNameLower});
	
	// 更新
	void update(${className} ${classNameLower});
	
	// 删除
	void deleteById(Long id);
	
	// 批量删除
	void batchDeleteByIds(List<Long> ids);
	
	// 总数
	int count(${className} ${classNameLower});
	
	// 根据id获取
	${className} getById(Long id);
	
	// 获取全部内容
	List<${className}> findAll();
	
	// 列表分页
	List<${className}> findByPage(Map<String, Object> map) ;
		
}
