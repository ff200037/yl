package com.yile.micro.user.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.user.bean.User;
import com.yile.micro.user.mapper.UserMapper;
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	@PostConstruct
	public void setMapper() {
		super.setMapper(userMapper);
	}
}
