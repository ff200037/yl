package com.yile.micro.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yile.micro.user.bean.User;
import com.yile.micro.user.service.UserService;

@Controller
public class UserController {
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "hello/{name}", method = RequestMethod.POST)
	@ResponseBody
	public Object Hello(@PathVariable String name) {
		System.out.println(111 + name);
		User u = new User(); u.setName(name); userService.insert(u);
		return "OK";
	}
}
