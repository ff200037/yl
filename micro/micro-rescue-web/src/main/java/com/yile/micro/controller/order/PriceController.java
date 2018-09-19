package com.yile.micro.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yile.micro.order.bean.Price;
import com.yile.micro.order.exception.PriceException;
import com.yile.micro.order.service.PriceService;
import com.yile.micro.system.bean.AccountBo;
import com.yile.micro.system.service.SysWebService;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定价模板控制层
 * @author hanfa
 */
@Controller
@RequestMapping("/price/template")
public class PriceController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PriceService priceService;

    @Autowired
    private SysWebService sysWebService;

    /**
     * 定价模板列表页面
     *
     * @param
     * @return
     */
    @RequestMapping("/pricePage")
    public String listPricePage() {
        return "price/pricePage";
    }

    /**
     * 获取定价模板列表数据
     * @param request
     * @return
     */
    @RequestMapping("/getPriceData")
    public ResponseEntity getPriceData(HttpServletRequest request) {
        Map< String, Object > paramMap = RequestUtil.paramsToMap2(request);

    	int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int pageNum = start/limit + 1;
		int pageSize = limit;

		PageInfo<Price> page = priceService.getPriceListData(paramMap, pageNum, pageSize);
		
		Map<String, String> rescueObjectMap = sysWebService.getDictionaryDataMap("rescue_object");
		List<Price> priceList = page.getList();
		if(priceList != null && priceList.size() > 0){
            for(Price price : priceList){
                price.setRescueObjectName(rescueObjectMap.get(price.getRescueObject()));
            }
        }

		JSONObject json = ResultUtil.getPageResult(page.getTotal(), priceList);
		return new ResponseEntity(json, HttpStatus.OK);
    }
    
    @RequestMapping("/addPricePage")
	public String addPricePage() {
		return "price/addPrice/addPrice";
	}

    @RequestMapping("/savePrice")
    public ResponseEntity savePrice(Price price) {
    	try{            
    		if(price.getId() == null){
                price.setCreater(this.getAccountName());
                price.setCreateTime(new Date());                 
                priceService.savePrice(price);
            } else {
                price.setUpdater(this.getAccountName());
                price.setUpdateTime(new Date());
                priceService.updatePrice(price);
            }
    		
    	}catch (Exception  e){
    		logger.error(e.getMessage(), e);
    		if(e instanceof PriceException){                
                return new ResponseEntity(ResultUtil.failed("保存失败：" + e.getMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity(ResultUtil.failed("保存失败：系统异常"), HttpStatus.OK);
            }
    	}
        
        return new ResponseEntity(ResultUtil.success("保存成功!"), HttpStatus.OK);
    }

    @RequestMapping("/queryPriceById")
    public ResponseEntity queryPriceById(@RequestParam("id") Long id) {
        Price price = priceService.queryById(id);
        
        JSONObject json = ResultUtil.formDataJson(price);
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping("/deletePriceById")
    @ResponseBody
    public ResponseEntity deletePriceById(@RequestParam("id") Long id) {
        priceService.deletePriceById(id);
        JSONObject json = ResultUtil.success("删除成功!");
        return new ResponseEntity(json, HttpStatus.OK);
    }
    
    @RequestMapping("/priceDetailPage")
	public String priceDetailPage() {
		return "price/priceDetail/priceDetail";
	}
    
    @RequestMapping("/getMechanismPage")
	public String getMechanismPage() {
		return "price/mechanism/mechanism";
	}
    
    /**
     * 获取救援机构列表数据
     * @param request
     * @return
     */
    @RequestMapping("/getMechanismData")
    public ResponseEntity getMechanismData(HttpServletRequest request) {
        Map< String, Object > paramMap = RequestUtil.paramsToMap2(request);

    	int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int pageNum = start/limit + 1;
		int pageSize = limit;

		PageInfo<Map < String, Object >> page = priceService.getMechanismListData(paramMap, pageNum, pageSize);
		
		Map<String, String> cityMap = sysWebService.getDictionaryDataMap("order_area");
		List<Map < String, Object >> mechanismList = page.getList();
		if(mechanismList != null && mechanismList.size() > 0){
            for(Map mechanismMap : mechanismList){
            	mechanismMap.put("cityName", cityMap.get(mechanismMap.get("city")));
            }
        }

		JSONObject json = ResultUtil.getPageResult(page.getTotal(), mechanismList);
		return new ResponseEntity(json, HttpStatus.OK);
    }

    /**
     * 通过定价ID查询关联机构
     * @param id
     * @return
     */
    @RequestMapping("/getMechanismByPriceId")
    public ResponseEntity getMechanismByPriceId(@RequestParam("id") Long id) {
        System.out.println(id);
        /*Price price = priceService.queryById(id);
        JSONObject json = ResultUtil.formDataJson(price);
        return new ResponseEntity(json, HttpStatus.OK);*/
        return null;
    }

    /**
     * 获取字典数据
     * @param
     * @return
     */
    @RequestMapping("/getDictionaryDataListByCode")
    public ResponseEntity getDictionaryDataListByCode(@RequestParam("code") String code) {
        List<Map<String, String>> list = sysWebService.getDictionaryDataListByCode(code);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    /**
     * 获取当前登录账号名称
     * @return
     */
    private String getAccountName() {
        AccountBo accountBo = sysWebService.getCurrentAccount();
        String accountName = "";
        if(accountBo != null){
            accountName = accountBo.getAccountName();
        }
        return accountName;
    }

}
