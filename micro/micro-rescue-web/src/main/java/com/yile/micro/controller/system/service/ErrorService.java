package com.yile.micro.controller.system.service;

import com.yile.micro.controller.system.bean.Permission;


public interface ErrorService {

	Permission findPermissionByPath(String servletPath);

}
