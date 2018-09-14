<#include "/platform/include/java_copyright.include">
<#assign className = table.className>
<#assign sqlName = table.sqlName>
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.controller;

<#include "/platform/include/controller.include">

import ${basepackage}.service.${className}Service;

@Controller
@RequestMapping("/${syspath}/${classNameLower}")
public class ${className}Controller {

	@Autowired
	private ${className}Service ${classNameLower}Service;
	
	@RequestMapping("/initPage")
	public String initPage(Model model) {
		return "${syspath}/${classNameLower}/initPage";
	}
	
	@RequestMapping("/add${className}")
	public String add${className}(Model model) {
		return "${syspath}/${classNameLower}/add/add${className}";
	}
	
	@RequestMapping("/${classNameLower}Detail")
	public String ${classNameLower}Detail(Model model) {
		return "${syspath}/${classNameLower}/detail/${classNameLower}Detail";
	}
	
	@RequestMapping(value = "/save${className}")
	public ResponseEntity save${className}(HttpServletRequest request)  {
		Map< String, Object > map = RequestUtil.paramsToMap(request);
		JSONObject json = ${classNameLower}Service.save${className}(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, Object > map = RequestUtil.paramsToMap(request);
		JSONObject jsonObject = ${classNameLower}Service.getListData(map);
		return new ResponseEntity(jsonObject, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete${className}")
	public ResponseEntity delete${className}(HttpServletRequest request) {
		Map< String, Object > map = RequestUtil.paramsToMap(request);
		JSONObject json = ${classNameLower}Service.del${className}(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, Object > map = RequestUtil.paramsToMap(request);
		JSONObject json = ${classNameLower}Service.get${className}ById(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
}
