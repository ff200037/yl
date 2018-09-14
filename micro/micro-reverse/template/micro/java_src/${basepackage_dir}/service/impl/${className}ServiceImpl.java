<#include "/micro/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.impl;

<#include "/micro/include/service.impl.include">

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import com.yile.micro.base.service.BaseService;
import ${basepackage}.bean.${className};
import ${basepackage}.mapper.${className}Mapper;
import ${basepackage}.service.${className}Service;

@Transactional
@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseService<${className}> implements ${className}Service {

	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(${classNameLower}Mapper);
	}
	
}
