<#include "/micro/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.test;

<#include "/micro/include/service.include">

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yile.micro.${className}Application;
import com.yile.micro.common.aspect.PageQueryAspect;
import ${basepackage}.bean.${className};
import ${basepackage}.service.${className}Service;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=${className}Application.class)
public class ${className}Test {
	@Resource(name="${classNameLower}Service")
	${className}Service ${classNameLower}Service;
	@Test
	public void ${classNameLower}Test(){
		//插入和更新自己写
		//User u = new User();
		//u.setName("uuu");
		//u.setId(36);
		//${classNameLower}Service.update(u);
		//${classNameLower}Service.insert(u);
		${classNameLower}Service.deleteById(1);
		System.out.println(${classNameLower}Service.queryById(14));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PageQueryAspect.CURRENT_PAGE,1);
		map.put(PageQueryAspect.PAGE_SIZE,10);
		System.out.println(${classNameLower}Service.queryPage(map));
	}
}