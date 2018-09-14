package com.yile.micro.controller.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yile.micro.controller.system.bean.Permission;
import com.yile.micro.controller.system.mapper.PermissionDao;
import com.yile.micro.controller.system.service.ErrorService;

@Service
public class ErrorServiceImpl implements ErrorService{
	@Autowired
	private PermissionDao permissionDao;
	public Permission findPermissionByPath(String servletPath) {
		return permissionDao.findPermissionByPath(servletPath);
	}
}
