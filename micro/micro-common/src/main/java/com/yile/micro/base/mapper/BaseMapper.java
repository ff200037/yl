package com.yile.micro.base.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	//根据id获取对象
	public T queryById(Serializable id);
	//插入
	public void insert(T bean);
	//根据id删除
	public void deleteById(Serializable id);
	//更新
	public void update(T bean);
	//根据条件查询列表
	public List<T> queryList(Map<String, Object> map);
	//根据条件统计
	public Integer queryCount(Map<String, Object> map);
}
