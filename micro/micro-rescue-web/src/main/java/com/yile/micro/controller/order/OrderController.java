package com.yile.micro.controller.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.service.OrderService;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
      
	 @Autowired
	 OrderService orderService;

	@RequestMapping("/list")
	public String list() {
		return "order/listOrder";
	}
	 
	 
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int pageNo = start/limit + 1;
		int pageSize = limit;
		Map<String, Object> param = new HashMap<String, Object>();
		//查询条件参数
		//if(StringUtils.isNotEmpty(kaidate)){
		//	param.put("kaidate", kaidate.replaceAll("-", ""));
		//}
		param.put("orderBy", "desc");
		PageInfo<Order> page = this.orderService.findPage(param, pageNo, pageSize);
		JSONObject json = ResultUtil.getPageResult(page.getTotal(), page.getList());
		return new ResponseEntity(json, HttpStatus.OK);
		//return items;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
