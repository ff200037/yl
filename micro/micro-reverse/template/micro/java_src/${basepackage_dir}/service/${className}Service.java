<#include "/micro/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

<#include "/micro/include/service.include">

import com.yile.micro.base.service.IBaseService;
import ${basepackage}.bean.${className};

public interface ${className}Service extends IBaseService<${className}> {
	
}

