package com.yile.micro.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yile.micro.user.service.RescueRescuesService;

@Controller
public class RescueRescuesController {
	@Resource(name = "rescueRescuesService")
	private RescueRescuesService rescueRescuesService;

	@RequestMapping(value = "postList", method = RequestMethod.POST)
	@ResponseBody
	public Object Hello(Long id) {

		System.out.println("请求成功");
		return null;

	}

}
