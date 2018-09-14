package com.yile.micro.controller.coupon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.coupon.bean.Coupon;
import com.yile.micro.coupon.service.CouponService;
import com.yile.micro.system.service.SysWebService;
import com.yile.micro.util.RequestUtil;

/**
 * @author moqp
 * @version 创建时间：2018年9月3日 下午4:06:39
 */
@Controller
@RequestMapping("coupon/coupon")
public class CouponController {
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	private SysWebService sysWebService;
	
	//基本页面
	@RequestMapping("/couponInitPage")
	public String couponInitPage(Model model) {
		return "system2/coupon/couponInitPage";
	}
	
	@RequestMapping("/extensionPage")
	public String extensionPage(Model model) {
		return "system2/coupon/extensionData/extensionPage";
	}
	
	@RequestMapping("/addCouponPage")
	public String addCouponPage(Model model) {
		return "system2/coupon/addCouponData/addCouponDataPage";
	}
	
	//获取字典数据    	 规定参数 name
	@RequestMapping("/getDictionary")
	@ResponseBody
	public ResponseEntity getCpnType(HttpServletRequest request){
		Map< String, String > map=RequestUtil.paramsToMap(request);
		String name = map.get("name").toString();
		List<Map<String, String>> list = sysWebService.getDictionaryDataListByCode(name);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	//查询优惠券信息
	@RequestMapping("/getCouponData")
	@ResponseBody
	public ResponseEntity getCouponData(HttpServletRequest request){
		Map< String, Object > map=RequestUtil.paramsToMap2(request);
		JSONObject json = couponService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@RequestMapping("/getCouponType")
	public ResponseEntity getCpnType(){
		List<Map<String, String>> list = sysWebService.getDictionaryDataListByCode("cpn_type");
		System.out.println(list);
		return new ResponseEntity(sysWebService.getDictionaryDataListByCode("cpn_type"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveCoupon")
	public void saveCoupon(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		System.out.println("map====="+map.toString());
		//map====={cpnType=2, useCondType=1, eftDate=, cpnNum=20000, 
				//cpnAmt=120, cpnMarks=大大, limGetNum=2, 
				//eftLen=365, areaRange=0, pBe=0, dateType=3, 
				//cpnName=啊啊啊, mjMoney=, custRange=1, invDate=}
		Coupon cp = new Coupon();
		cp.setCpnType(map.get("cpnType"));
		cp.setUseCondType(map.get("useCondType"));
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		if(map.get("eftDate") != ""){
			
			Date date = null;
			try {
				date = sDateFormat.parse(map.get("eftDate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cp.setEftDate(date);
		}
		cp.setCpnNum(Long.parseLong(map.get("cpnNum")));
		cp.setCpnAmt(Double.parseDouble(map.get("cpnAmt")));
		cp.setCpnMarks(map.get("cpnMarks"));
		cp.setLimGetNum(Integer.parseInt(map.get("limGetNum")));
		cp.setEftLen(Integer.parseInt(map.get("eftLen")));
		cp.setAreaRange(map.get("areaRange"));
		cp.setPbe(map.get("pBe"));
		cp.setDateType(map.get("dateType"));
		//cpnName=啊啊啊, mjMoney=, custRange=1, invDate=}
		cp.setCpnName(map.get("cpnName"));
		cp.setMjMoney(Double.parseDouble(map.get("mjMoney")));
		if(map.get("invDate") != ""){
			Date date2 = null;
			try {
				date2 = sDateFormat.parse(map.get("invDate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cp.setInvDate(date2);
		}
		cp.setCustRange(map.get("custRange"));
		cp.setCpnStatus("2");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());
		Date createTime1 = null;
		try {
			createTime1 = df.parse(createTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cp.setCreateTime(createTime1);
		cp.setAlrdyGetNum(Long.parseLong("0"));
		System.out.println("bean====="+cp.toString());
		couponService.insert(cp);
		
	}
}



