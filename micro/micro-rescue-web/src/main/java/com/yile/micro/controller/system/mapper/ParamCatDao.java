package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.ParamCat;


public interface ParamCatDao {
	List<ParamCat> getAllParamCat();

	void saveParamCat(Map< String, String > map);


	void updateParamCat(Map< String, String > map);

	void delById(String id);
}