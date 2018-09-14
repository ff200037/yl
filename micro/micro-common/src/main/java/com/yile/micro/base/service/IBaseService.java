package com.yile.micro.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yile.micro.common.PageBean;

public interface IBaseService<T> {
	public T queryById(Serializable id);
	public void insert(T bean);
	public void deleteById(Serializable id);
	public void update(T bean);
	public List<T> queryList(Map<String, Object> map);
	public Integer queryCount(Map<String, Object> map);
	public PageBean<T> queryPage(Map<String, Object> map);
}
