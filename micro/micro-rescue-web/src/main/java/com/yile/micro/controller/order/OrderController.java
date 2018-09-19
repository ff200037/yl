package com.yile.micro.controller.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.github.pagehelper.PageInfo;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.controller.system.service.DictionaryDataService;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.bean.RescueOrder;
import com.yile.micro.order.service.OrderService;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
      
	 @Autowired
	 OrderService orderService;

	 @Autowired
	 private DictionaryDataService dictionaryDataService;
	 
	@RequestMapping("/list")
	public String list() {
		return "order/listOrder";
	}
	
	/**
	 * 查询一条
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = new JSONObject();
		long id =Long.valueOf(map.get("id").toString());
		       try {
				Order orderById = orderService.getOrderById(id);
				json = ResultUtil.formDataJson(orderById);
			 } catch (Exception e) {
			    e.printStackTrace();
			}
		 return new ResponseEntity(json, HttpStatus.OK);
	}
	 /**
	  * 订单列表
	  * @param request
	  * @return
	  */
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String orderNo =  request.getParameter("orderNo");  //搜索值
		String orderStatus =  request.getParameter("orderStatus"); //搜索值
		int pageNo = start/limit + 1;
		int pageSize = limit;
		Map<String, Object> param = new HashMap<String, Object>();
		//查询条件参数
		if(StringUtils.isNotEmpty(orderNo)){
		 	param.put("orderNo", orderNo.replaceAll("-", ""));
		 }
		if(StringUtils.isNotEmpty(orderStatus)){
		 	param.put("orderStatus", orderStatus.replaceAll("-", ""));
		 }
		param.put("orderBy", "desc");
		 
		PageInfo<Order> page = this.orderService.findPage(param, pageNo, pageSize);
		JSONObject json = ResultUtil.getPageResult(page.getTotal(), page.getList());
		return new ResponseEntity(json, HttpStatus.OK);
	}
	 
	/**
	 * 添加订单页面
	 * @return
	 */
	@RequestMapping("/addOrderPage")
	public String addOrderPage(Model model) {
		System.out.println(model);
	   return "order/addOrderPage";
	}
	 
	 /**
	  * 添加订单
	  * @param request
	  * @return
	  */
	@RequestMapping("/save")
	public ResponseEntity save(HttpServletRequest request) {
		Map<String, Object> map = RequestUtil.paramsToMap2(request);
		JSONObject json = new JSONObject();
		String id = map.get("id").toString();
		Order order = prepareData(map,id);
		MapRet saveUpdata = null;
		try {
			if (StringUtils.isNotEmpty(id)) {
				 order.setId(Long.valueOf(id));
				 saveUpdata = orderService.updateOrder(order);
			}else {
				 saveUpdata = orderService.saveOrder(order);
			 }
	         
		    if ("10000".equals(saveUpdata.get("returnCode"))) {
			     json= ResultUtil.success("成功!");
		    }else {
			     json= ResultUtil.failed("失败!");
		  }
        } catch (Exception e) {
        	     json= ResultUtil.failed("失败!");
			     e.printStackTrace();
		}
		return new ResponseEntity(json, HttpStatus.OK);
	}
	 
	 
	
	/**
	 * 准备添加的数据
	 * @return
	 */
	public static Order prepareData(Map<String, Object> map,String id) {
		RescueOrder rescueOrder = new RescueOrder();
		Order order = new Order();
		Integer orderType = Integer.parseInt(map.get("orderType").toString());
		String orderNo = map.get("orderNo").toString();
		String orderstatus = map.get("orderStatus").toString();
		String createOrderTime = map.get("createOrderTime").toString();
		// Integer endTime = Integer.parseInt(map.get("endTime").toString());
		Long fkCustomerId = Long.valueOf(map.get("fkCustomerId").toString());
		String serviceType = map.get("serviceType").toString();
		String creater = map.get("creater").toString();
		// String createTime = map.get("createTime").toString();
		// String updater = map.get("updater").toString();
		// String updateTime = map.get("updateTime").toString();
		order.setOrderType(orderType); // 下单方式
		order.setOrderNo(orderNo); // 订单编号
		order.setOrderStatus(orderstatus); // 订单状态
		order.setCreateOrderTime(getData(createOrderTime)); // 下单时间
		order.setEndTime(null); // 订单结束时间
		order.setFkCustomerId(fkCustomerId); // 车主ID
		order.setServiceType(serviceType); // 订单大业务类型
		order.setCreater(creater); // 创建人姓名
		order.setCreateTime(new Date()); // 创建时间
		order.setUpdater(null); // 修改人姓名
		order.setUpdateTime(null); // 修改时间
		// 更新需要
		if (StringUtils.isNotEmpty(id)) {
			Long orderId = Long.valueOf(id);
			rescueOrder.setId(orderId);
			rescueOrder.setFkOrderId(orderId);
		 }
		rescueOrder.setBelongedRegion(""); // 所属区域
		rescueOrder.setFkCarId(0L); // 故障车辆ID
		rescueOrder.setFkRescueMechanismId(0L); // 所属救援机构ID
		rescueOrder.setRescueExplain(""); // 救援说明
		rescueOrder.setCarPosition(""); // 故障车辆位置
		rescueOrder.setCarLon(""); // 故障车辆经度
		rescueOrder.setCarLat(""); // 故障车辆纬度
		rescueOrder.setWaitTime(0); // 等待时间
		rescueOrder.setRescueMileage(0.00); // 救援总里程
		rescueOrder.setRepairCharging(0.00); // 修理附加费用
		rescueOrder.setCreater(""); // 创建人姓名
		rescueOrder.setCreateTime(null); // 创建时间
		rescueOrder.setUpdater(""); // 修改人姓名
		rescueOrder.setUpdateTime(null); // 修改时间
		order.setRescueOrder(rescueOrder);
		return order;
		 
	 }

	
	
	
	
	
	
	 /**
	  * 字符 转 时间
	  * @param str
	  * @return
	  */
    public static Date getData(String str) {
		  Date date = null;
		  try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			} catch (ParseException e) {
				System.out.println("格式不正确");
				e.printStackTrace();
			}
		  return date;
	  }
	 
	  
	  /**删除账号
		 * @param request
		 * @return
		 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(HttpServletRequest request) {
		Map<String, String> map = RequestUtil.paramsToMap(request);
		Order order = new Order();
		long id = Long.valueOf(map.get("id"));
		order.setId(id);
		JSONObject json = new JSONObject();
		 MapRet deleteOrder = null;
		try {
		     deleteOrder = orderService.deleteOrder(order);
		     if ("10000".equals(deleteOrder.get("returnCode"))) {
		    	json= ResultUtil.success("删除成功!");
			}else {
				json= ResultUtil.failed("删除失败!");
			 }
		 } catch (Exception e) {
			    json= ResultUtil.failed("删除失败!");
			    e.printStackTrace();
		}
		return new ResponseEntity(json, HttpStatus.OK);
	}
	 
	 
	/**通过编码获取字典数据
	 * @return
	 */
	@RequestMapping(value = "/getListDataByCode")
	public ResponseEntity getListDataByCode(String dictionaryCode) {
		List<Map<String, String>> list=dictionaryDataService.getListDataByCode(dictionaryCode);
		return new ResponseEntity( list, HttpStatus.OK);
	} 
	 
	/**
	  * 救援订单列表 (多表)
	  * @param request
	  * @return
	  */
	/*@RequestMapping(value = "/getRescueOrderList")
	public ResponseEntity getRescueOrderList(HttpServletRequest request) {
		Map<String, Object> map = RequestUtil.paramsToMap2(request);
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		//String orderNo =  request.getParameter("orderNo");  //搜索值
		//String orderStatus =  request.getParameter("orderStatus"); //搜索值
		int pageNo = start/limit + 1;
		int pageSize = limit;
		Map<String, Object> param = new HashMap<String, Object>();
		 
		param.put("orderBy", "desc");
		 List<Order> queryListRescueOrder = null;
		try {
			queryListRescueOrder = this.orderService.queryRescueOrderList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return new ResponseEntity(queryListRescueOrder, HttpStatus.OK);
	} */
	 
}
