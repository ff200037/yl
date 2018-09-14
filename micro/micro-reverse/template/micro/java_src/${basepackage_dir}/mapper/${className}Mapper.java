<#include "/micro/include/java_copyright.include">
<#assign className = table.className>   

<#assign classNameLower = className?uncap_first>
<#-- 解析如包含下线，就去掉大写字母 -->
package ${basepackage}.mapper;

<#include "/micro/include/mapper.include">

import com.yile.micro.base.mapper.BaseMapper;
import ${basepackage}.bean.${className};

/**
 * Mapper.xml 文件配置相应的方法
 * @author 
 * 
 */
public interface ${className}Mapper extends BaseMapper<${className}>{
	
}
