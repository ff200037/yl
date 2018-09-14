package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.container.page.Menu;
import com.yile.micro.controller.system.bean.MenuVo;


public interface MenuDao {


	void saveMenu(Map< String, String > map);

	List<Menu> getAllData();

	List<Map<String, Object>> getListData(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	void updateMenuFolder(Map< String, String > map);

	void updateMenu(Map< String, String > map);

	List<MenuVo> getDataForTree();

	List<String> findAccountMenuIdList(String id);

	List<String> findAllMenuIdList();

	Map<String, Object> getById(String id);

	void delById(String id);
}