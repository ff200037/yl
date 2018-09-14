package com.yile.micro.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yile.micro.order.bean.Price;
import com.yile.micro.order.service.PriceService;
import com.yile.micro.system.bean.AccountBo;
import com.yile.micro.system.service.SysWebService;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
        Map< String, Object > param = RequestUtil.paramsToMap2(request);

    	int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int pageNum = start/limit + 1;
		int pageSize = limit;

		PageInfo<Price> page = priceService.getListData(param, pageNum, pageSize);
		
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
		return "price/addPricePage/addPrice";
	}

    @RequestMapping("/savePrice")
    public ResponseEntity savePrice(Price price) {
        if(price.getId() == null){
            price.setCreater(this.getAccountName());
            price.setCreateTime(new Date());
            priceService.savePrice(price);
        } else {
            System.out.println(price.getId());
            price.setUpdater(this.getAccountName());
            price.setUpdateTime(new Date());
            priceService.update(price);
        }

        JSONObject json = ResultUtil.success("保存成功!");
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping("/queryPriceById")
    public ResponseEntity queryPriceById(@RequestParam("id") Long id) {
        Price price = priceService.queryById(id);
        JSONObject json = ResultUtil.formDataJson(price);
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping("/deletePriceById")
    @ResponseBody
    public ResponseEntity deletePriceById(@RequestParam("id") Long id,HttpServletRequest request) {
    	Map< String, Object > param = RequestUtil.paramsToMap2(request);
        priceService.deletePriceById(id);
        JSONObject json = ResultUtil.success("删除成功!");
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
     * 获取救援项目字典数据
     * @param
     * @return
     */
    @RequestMapping("/getRescueObject")
    public ResponseEntity getRescueObject() {
        List<Map<String, String>> list = sysWebService.getDictionaryDataListByCode("rescue_object");
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
