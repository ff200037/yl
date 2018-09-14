package com.yile.micro.order.service.impl;

import com.yile.micro.OrderApplication;
import com.yile.micro.order.bean.PriceChargeDetail;
import com.yile.micro.order.service.PriceChargeDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class PriceChargeDetailServiceImplTest {	
	@Autowired
	PriceChargeDetailService priceChargeDetailService;
	
    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
    	PriceChargeDetail priceChargeDetail = priceChargeDetailService.queryById(null);
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
    	PriceChargeDetail priceChargeDetail = new PriceChargeDetail();    	
    	//priceChargeDetail.setId(null);
    	priceChargeDetail.setFkPrcId(1L);
    	priceChargeDetail.setItemType("1");
    	priceChargeDetail.setPricingAmt(Double.valueOf(100));
    	priceChargeDetail.setAmount("1");
    	priceChargeDetail.setPrice(Double.valueOf(100));
    	priceChargeDetail.setItemDesc("基础收费");
		priceChargeDetail.setDataOpenTime(new Date());
		priceChargeDetail.setDataOpenType("I");
		priceChargeDetail.setItemCode(null);
    	priceChargeDetail.setRescueProject("1");
    	priceChargeDetailService.insert(priceChargeDetail);
    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
    	priceChargeDetailService.deleteById(null);
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
    	PriceChargeDetail priceChargeDetail = priceChargeDetailService.queryById(null);
    	priceChargeDetail.setId(null);
    	priceChargeDetail.setFkPrcId(null);
    	priceChargeDetail.setItemType(null);
    	priceChargeDetail.setPricingAmt(null);
    	priceChargeDetail.setAmount(null);
    	priceChargeDetail.setPrice(null);
    	priceChargeDetail.setItemDesc(null);
    	priceChargeDetail.setDataOpenTime(null);
    	priceChargeDetail.setDataOpenType(null);
    	priceChargeDetail.setItemCode(null);
    	priceChargeDetail.setRescueProject(null);
    	priceChargeDetailService.update(priceChargeDetail);
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<PriceChargeDetail> listPriceChargeDetail = priceChargeDetailService.queryList(map);
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        Integer count = priceChargeDetailService.queryCount(map);
    }

    /**
     * 分页查询
     *
     * @throws Exception
     */
    @Test
    public void queryPage() throws Exception {

    }
}