package com.yile.micro.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.common.PageBean;

public abstract class BaseService<T> implements IBaseService<T>{
	protected BaseMapper<T> mapper;
	protected void setMapper(BaseMapper<T> mapper) {
		this.mapper = mapper;
	}
	public T queryById(Serializable id){
		return mapper.queryById(id);
	}
	@Transactional
	public void insert(T bean){
		mapper.insert(bean);
	}
	@Transactional
	public void deleteById(Serializable id){
		mapper.deleteById(id);
	}
	public void update(T bean){
		mapper.update(bean);
	}
	public List<T> queryList(Map<String, Object> map){
		return mapper.queryList(map);
	}
	public Integer queryCount(Map<String, Object> map){
		return mapper.queryCount(map);
	}
	public PageBean<T> queryPage(Map<String, Object> map){
		List<T> list = queryList(map);
		Integer count = queryCount(map);
		return new PageBean<T>(null,null,count,list);
	}
}
