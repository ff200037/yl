package com.yile.micro.controller.coupon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.coupon.bean.Coupon;
import com.yile.micro.coupon.service.CouponService;
import com.yile.micro.system.service.SysWebService;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;

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
	
	/**优惠券主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/couponInitPage")
	public String couponInitPage(Model model) {
		return "system2/coupon/couponInitPage";
	}
	//详情
	@RequestMapping(value ="/couponDataInfoPage")
	public String couponDataInfoPage(Model model){
		return "system2/coupon/couponDataInfo/couponDataInfoPage";
	}
	//选项卡页面
	@RequestMapping("couponDetail")
	public String test(Model model){
		return "system2/coupon/couponDataInfo/couponDetail";
	}
	/**推广页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/extensionPage")
	public String extensionPage(Model model) {
		return "system2/coupon/extensionData/extensionPage";
	}
	
	/**新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addCouponPage")
	public String addCouponPage(Model model) {
		return "system2/coupon/addCouponData/addCouponDataPage";
	}
	
	/**修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateCouponPage")
	public String updateCouponPage(Model model) {
		return "system2/coupon/updateCoupon/updateCouponDataPage";
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
	
	/**查询优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCouponData")
	@ResponseBody
	public ResponseEntity getCouponData(HttpServletRequest request){
		Map< String, Object > map=RequestUtil.paramsToMap2(request);
		JSONObject json = couponService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	/**获取下拉框值
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCouponType")
	public ResponseEntity getCpnType(){
		List<Map<String, String>> list = sysWebService.getDictionaryDataListByCode("cpn_type");
		System.out.println(list);
		return new ResponseEntity(sysWebService.getDictionaryDataListByCode("cpn_type"), HttpStatus.OK);
	}
	
	/**新增优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveCoupon")
	public ResponseEntity saveCoupon(HttpServletRequest request)  {
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
			String strDate = map.get("eftDate").toString();
			Date date = UTCToCST(strDate);
			cp.setEftDate(date);
		}
		cp.setCpnNum(Long.parseLong(map.get("cpnNum")));
		if(map.get("cpnAmt1") != ""){
			cp.setCpnAmt1(Double.parseDouble(map.get("cpnAmt1")));
		}
		if(map.get("cpnAmt2") != ""){
			cp.setCpnAmt2(Double.parseDouble(map.get("cpnAmt2")));
		}
		cp.setCpnMarks(map.get("cpnMarks"));
		cp.setLimGetNum(Integer.parseInt(map.get("limGetNum")));
		if(map.get("eftLen") != ""){
			cp.setEftLen(Integer.parseInt(map.get("eftLen")));
		}
		cp.setAreaRange(map.get("areaRange"));
		cp.setPbe(map.get("pbe"));
		cp.setDateType(map.get("dateType"));
		//cpnName=啊啊啊, mjMoney=, custRange=1, invDate=}
		cp.setCpnName(map.get("cpnName"));
		if(map.get("mjMoney") != ""){
			cp.setMjMoney(Double.parseDouble(map.get("mjMoney")));
		}
		if(map.get("invDate") != ""){
		   String dateStr = map.get("invDate").toString();
		   Date date2 =  UTCToCST(dateStr);
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
			e.printStackTrace();
		}
		cp.setCreateTime(createTime1);
		cp.setAlrdyGetNum(Long.parseLong("0"));
		System.out.println("bean====="+cp.toString());
		couponService.insert(cp);
		return new ResponseEntity(ResultUtil.success("保存成功!"), HttpStatus.OK);
	}
	
	/**根据ID查询优惠券
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request)  {
		try {
			Map< String, String > map=RequestUtil.paramsToMap(request);
			System.out.println("map====="+map.toString());
			int id = Integer.parseInt(map.get("id"));
			Coupon cp = couponService.queryById(id);
			net.sf.json.JSONObject js = new net.sf.json.JSONObject().fromObject(cp);
			if(cp.getEftDate()!=null&&!"".equals(cp.getInvDate())){
				String eftDate = dateChange(cp.getEftDate().toString());
				js.put("eftDate", eftDate);
			}else{
				js.remove("eftDate");
			}
			if(cp.getInvDate()!=null&&!"".equals(cp.getInvDate())){
				String invDate = dateChange(cp.getInvDate().toString());
				js.put("invDate", invDate);
			}else {
				js.remove("invDate");
			}
			if(cp.getUpdateTime()==null||"".equals(cp.getUpdateTime())){
				js.remove("updateTime");
			}
			
			JSONObject json = ResultUtil.formDataJson(js);
			return new ResponseEntity(json, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	/**修改优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateCoupon")
	public ResponseEntity updateCoupon(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		Coupon cp = new Coupon();
		cp.setCpnType(map.get("cpnType"));
		cp.setUseCondType(map.get("useCondType"));
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		if(map.get("eftDate") != ""){
			Date date;
			try {
				date = sDateFormat.parse(map.get("eftDate"));
				cp.setEftDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		cp.setCpnNum(Long.parseLong(map.get("cpnNum")));
		if(map.get("cpnAmt1") != ""){
			cp.setCpnAmt1(Double.parseDouble(map.get("cpnAmt1")));
		}
		if(map.get("cpnAmt2") != ""){
			cp.setCpnAmt2(Double.parseDouble(map.get("cpnAmt2")));
		}
		cp.setCpnMarks(map.get("cpnMarks"));
		cp.setLimGetNum(Integer.parseInt(map.get("limGetNum")));
		if(map.get("eftLen") != ""){
			cp.setEftLen(Integer.parseInt(map.get("eftLen")));
		}
		cp.setAreaRange(map.get("areaRange"));
		cp.setPbe(map.get("pbe"));
		cp.setDateType(map.get("dateType"));
		//cpnName=啊啊啊, mjMoney=, custRange=1, invDate=}
		cp.setCpnName(map.get("cpnName"));
		if(map.get("mjMoney") != ""){
			cp.setMjMoney(Double.parseDouble(map.get("mjMoney")));
		}
	
		if(map.get("invDate") != ""){
			Date date2;
			try {
				date2 = sDateFormat.parse(map.get("invDate"));
				cp.setInvDate(date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		cp.setCustRange(map.get("custRange"));
		//cp.setCpnStatus("2");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String updateTime = df.format(new Date());
		Date updateTime1 = null;
		try {
			updateTime1 = df.parse(updateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cp.setUpdateTime(updateTime1);
		cp.setId(Long.parseLong(map.get("id")));
		//cp.setAlrdyGetNum(Long.parseLong("0"));
		couponService.update(cp);
		return new ResponseEntity(ResultUtil.success("保存成功!"), HttpStatus.OK);
	}
	
	
	/**删除优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delCouponData")
	public ResponseEntity delCouponData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = null;
		try{
			couponService.deleteById(Long.parseLong(map.get("id")));
			json =  ResultUtil.success("删除成功!");
		}catch(Exception e){
			e.printStackTrace();
			json =  ResultUtil.failed("无法删除");
		}
		
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@RequestMapping("cpnDetails")
	public ResponseEntity couponInfo(HttpServletRequest request){
		Map< String, String > map=RequestUtil.paramsToMap(request);
		try {
			Integer id = Integer.parseInt(map.get("id"));
			Coupon cp = couponService.queryById(id);
			JSONObject json = ResultUtil.formDataJson(cp);
			return new ResponseEntity(json,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}	
	
	//停用
	@RequestMapping("blockUp")
	public ResponseEntity couponBlockUp(HttpServletRequest request){
		try {
			Map<String, String> map = RequestUtil.paramsToMap(request);
			Long id =Long.valueOf((map.get("id").toString()));
			couponService.couponBlockUp(id);
			JSONObject json =  ResultUtil.success("操作成功!");
			return new ResponseEntity(json,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//启用
	@RequestMapping("couponUse")
	public ResponseEntity couponUse(HttpServletRequest request){
		try {
			Map<String, String> map = RequestUtil.paramsToMap(request);
			Long id =Long.valueOf((map.get("id").toString()));
			couponService.couponUse(id);
			JSONObject json =  ResultUtil.success("操作成功!");
			return new ResponseEntity(json,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**推广优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/extensionCoupon")
	public ResponseEntity extensionCoupon(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = null;
		if(map.get("spreadMode") == "1"){
			//发放优惠券
			json = couponService.grantCoupon(map);
		}else{
			//客户领取优惠券
			json = couponService.receiveCoupon(map);
		}
		//couponService.deleteById(Long.parseLong(map.get("id")));
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	 /**
	  * UCT ISO转北京时间 yyyy-MM-dd
	  * @param UTCStr
	  * @return
	  */
	 public static Date UTCToCST(String UTCStr){
		 	try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	        Date date = sdf.parse(UTCStr);
	        String a ="Sun Sep 30 00:00:00 CST 2018";
	         
	        
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
	        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
	        Date beiJingDate =  calendar.getTime();
	        
	        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	        sf.format(new Date(a));
	        sf.setLenient(false);
	        String str =sf.format(beiJingDate);
	        Date newDate= sf.parse(str);
	        return newDate;
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 		return null;
			}
	    }
	 public String dateChange(String date) throws ParseException{
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	     String str = sf.format(new Date(date));
	   
		 return str;
	 }
	
	
}






