<#include "/rest/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.impl;

<#include "/rest/include/service.impl.include">

import ${basepackage}.bean.${className};
import ${basepackage}.mapper.${className}Mapper;
import ${basepackage}.service.${className}Service;

@Transactional
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {

	protected Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;
	
	/**
	 * 保存和更新
	 */
	public void save${className}(${className} ${classNameLower}) {
		if(${classNameLower}.getId() != null){
			this.${classNameLower}Mapper.update(${classNameLower});
		}else{
			this.${classNameLower}Mapper.add(${classNameLower});
		}
	}

	/**
	 * 删除
	 */
	public void delete${className}ById(Long id) {
		this.${classNameLower}Mapper.deleteById(id);
	}
	
	/**
	 * 多删除
	 * @param ids
	 */
	public void batchDelete${className}(List<Long> ids) {
		this.${classNameLower}Mapper.batchDeleteByIds(ids);
	}
	
	/**
	 * 获取对象
	 */
	public ${className} get${className}ById(Long id) {
		${className} ${classNameLower} = (${className}) this.${classNameLower}Mapper.getById(id);
		return ${classNameLower};
	}
	
	/**
	 * 全部
	 */
	public List<${className}> getAll${className}List(){
		return this.${classNameLower}Mapper.findAll();
	}

	/**
	 * 分页
	 * @param map
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<${className}> findByPage(Map<String, Object> map) {
		return this.${classNameLower}Mapper.findByPage(map);
	}
	
}
