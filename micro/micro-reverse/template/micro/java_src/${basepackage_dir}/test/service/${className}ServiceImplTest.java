<#include "/micro/include/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service.impl;

<#include "/micro/include/service.include">

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yile.micro.OrderApplication;
import com.yile.micro.common.aspect.PageQueryAspect;
import ${basepackage}.bean.${className};
import ${basepackage}.service.${className}Service;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ${syspath?cap_first}Application.class)
public class ${className}ServiceImplTest {	
	@Autowired
	${className}Service ${classNameLower}Service;
	
    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
    	${className} ${classNameLower} = ${classNameLower}Service.queryById(null);
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
    	${className} ${classNameLower} = new ${className}();    	
    	<#list table.columns as column>
    	${classNameLower}.set${column.columnName}(null);
    	</#list>
    	
    	${classNameLower}Service.insert(${classNameLower});
    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
    	${classNameLower}Service.deleteById(null);
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
    	${className} ${classNameLower} = ${classNameLower}Service.queryById(null);
    	<#list table.columns as column>
    	${classNameLower}.set${column.columnName}(null);
    	</#list>
    	
    	${classNameLower}Service.update(${classNameLower});
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<${className}> list${className} = ${classNameLower}Service.queryList(map);
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        Integer count = ${classNameLower}Service.queryCount(map);
    }

    /**
     * 分页查询
     *
     * @throws Exception
     */
    @Test
    public void queryPage() throws Exception {

    }
}