package com.yile.micro.controller.order;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import com.yile.micro.common.utils.FastJson;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.common.utils.MediaTypes;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.bean.RescueOrder;
import com.yile.micro.order.service.OrderService;
import com.yile.micro.util.RequestUtil;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/order/rescue")
public class OrderRescueController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping("/create")
	public String create() {
		return "order/rescue/createRescue";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model) {
		return "order/rescue/editRescue";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model) {
		return "order/rescue/detailRescue";
	}
	
	/**
	 * 百度地图
	 * @param model
	 * @return
	 */
	@RequestMapping("/detailBMap")
	public String detailBMap(Model model) {
		return "order/rescue/detailBMap";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/save")
	public ResponseEntity save(HttpServletRequest request)  {
		MapRet ret = MapRet.create();
		Map< String, Object > map = RequestUtil.paramsToMap2(request);
		// 转换位实体
		Order order = new Order();
		try {
			// 车主
			RescueOrder rescueOrder = FastJson.getJson().parse(JSON.toJSONString(map), RescueOrder.class);
			Long fkCustomerId = (Long)map.get("fkCustomerId");
			order.setOrderNo("456");// 订单编号,自动生成
			order.setFkCustomerId(fkCustomerId);
			order.setOrderStatus("1"); //'订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成',
			order.setOrderType(1); // '下单方式:1.电话下单2.公众号下单',
			order.setServiceType("1"); //'订单大业务类型1.救援 2.分时 3.网约 4.商城',
			order.setRescueOrder(rescueOrder);
			ret = this.orderService.saveOrder(order);
			ret.setOk().set("msg", "保存成功").set("success", true);
		} catch (Exception e) {
			ret.setFail().set("msg", "保存失败").set("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(ret.toJson(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/delete")
	public ResponseEntity delete(HttpServletRequest request) {
		MapRet ret = MapRet.create();
		Map< String, Object > map = RequestUtil.paramsToMap2(request);
		String idStr = (String)map.get("id");
		if(StringUtils.isNotEmpty(idStr)){
			try {
				ret = orderService.deleteOrderById(Long.parseLong(idStr));
				ret.setOk().set("msg", "删除成功").set("success", true);
			} catch (Exception e) {
				ret.setFail().set("msg", "删除失败").set("success", false);
				e.printStackTrace();
			}
		}
		return new ResponseEntity(ret.toJson(), HttpStatus.OK);
	}

	@RequestMapping("/list")
	public String list() {
		return "order/rescue/listRescue";
	}

	@RequestMapping(value="/getPageData", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public @ResponseBody PageInfo<Order> getPageData(HttpServletRequest request) {
		String startStr = request.getParameter("page");
		String limitStr = request.getParameter("limit");
		int start = 0;
		int limit = 10;
		if (StringUtils.isNotEmpty(startStr) && StringUtils.isNotEmpty(limitStr)) {
			start = Integer.parseInt(startStr);
			limit = Integer.parseInt(limitStr);
		}
		int pageNo = start / limit + 1;
		int pageSize = limit;
		Map<String, Object> param = new HashMap<String, Object>();
		//查询条件参数
		//if(StringUtils.isNotEmpty(kaidate)){
		//	param.put("kaidate", kaidate.replaceAll("-", ""));
		//}
		param.put("orderBy", "desc");
		PageInfo<Order> page = this.orderService.findPage(param, pageNo, pageSize);
		return page;
	}

}
