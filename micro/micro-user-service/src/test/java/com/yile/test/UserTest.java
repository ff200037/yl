package com.yile.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yile.micro.UserApplication;
import com.yile.micro.common.aspect.PageQueryAspect;
import com.yile.micro.user.bean.User;
import com.yile.micro.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=UserApplication.class)
public class UserTest {
	@Resource(name="userService")
	UserService userService;
	//@Autowired
	//UserMapper userService;
	@Test
	public void userTest(){
		User u = new User();
		u.setName("uuu");
		userService.insert(u);
		userService.deleteById(1);
		System.out.println(userService.queryById(14));
		u.setId(36);
		userService.update(u);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PageQueryAspect.CURRENT_PAGE,1);
		map.put(PageQueryAspect.PAGE_SIZE,10);
		System.out.println(userService.queryPage(map));
	}
}
