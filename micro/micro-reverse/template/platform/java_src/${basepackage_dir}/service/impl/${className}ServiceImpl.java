<#include "/platform/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.impl;

<#include "/platform/include/service.impl.include">

import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import core.util.ResultUtil;
import ${basepackage}.common.dao.${className}Dao;
import ${basepackage}.service.${className}Service;

@Transactional
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {

	protected Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Dao ${classNameLower}Dao;
	
	public JSONObject getListData(Map< String, Object > map) {
		List<Map < String, Object >> list = ${classNameLower}Dao.getListData(map);
		Long count = ${classNameLower}Dao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	
	public JSONObject save${className}(Map< String, Object > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			//新建账号的默认密码是123456
			map.put("accountPassword", "123456");
			map.put("accountType", 2);//普通账号
			${classNameLower}Dao.save${className}(map);
			return ResultUtil.success("保存成功!");
		} else {
			${classNameLower}Dao.update${className}(map);
			return ResultUtil.success("更新成功!");
		}
		
	}
	
	public JSONObject get${className}ById(Map< String, Object > map) {
		Map<String, Object> resultMap = ${classNameLower}Dao.getById(map.get("id").toString());
		return ResultUtil.formDataJson(resultMap);
	}
	
	public JSONObject del${className}(Map< String, Object > map) {
		try {
			String id = map.get("id").toString();
			${classNameLower}Dao.deleteById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			return ResultUtil.failed("无法删除");
		}
	}
	
	/**
	 * 删除
	 */
	public void delete${className}ById(String id) {
		this.${classNameLower}Dao.deleteById(id);
	}
	
	/**
	 * 多删除
	 * @param ids
	 */
	public void batchDelete${className}(List<Long> ids) {
		this.${classNameLower}Dao.batchDeleteByIds(ids);
	}
	
	/**
	 * 获取对象
	 */
	public Map<String, Object> get${className}ById(String id) {
		Map<String, Object> map = this.${classNameLower}Dao.getById(id);
		return map;
	}
	
	/**
	 * 全部
	 */
	public List<Map<String, Object>> getAll${className}List(){
		return this.${classNameLower}Dao.findAll();
	}

	/**
	 * 分页
	 * @param map
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> findByPage(Map<String, Object> map) {
		return this.${classNameLower}Dao.findByPage(map);
	}
	
}
