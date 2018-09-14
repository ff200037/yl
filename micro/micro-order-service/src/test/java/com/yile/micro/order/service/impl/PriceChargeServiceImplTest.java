package com.yile.micro.order.service.impl;

import com.yile.micro.OrderApplication;
import com.yile.micro.order.bean.PriceCharge;
import com.yile.micro.order.bean.PriceChargeDetail;
import com.yile.micro.order.service.PriceChargeService;
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
public class PriceChargeServiceImplTest {
    @Autowired
    PriceChargeService priceChargeService;

    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        PriceCharge priceCharge = priceChargeService.queryById(1L);
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        PriceCharge priceCharge = new PriceCharge();
        //priceCharge.setId(null);
        priceCharge.setFkCustId(1L);
        priceCharge.setFkOrderId(1L);
        priceCharge.setBillingType("02");
        priceCharge.setSettlementAmount(Double.valueOf(100));
        priceCharge.setPriceStatus("1");
        priceCharge.setCalcDate(new Date());
        priceCharge.setServiceType("1");
        priceCharge.setCreater("test");
        priceCharge.setCreateTime(new Date());
        priceCharge.setUpdater("test");
        priceCharge.setUpdateTime(new Date());

        priceChargeService.insert(priceCharge);
    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
        priceChargeService.deleteById(null);
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        PriceCharge priceCharge = priceChargeService.queryById(null);
        priceCharge.setId(null);
        priceCharge.setFkCustId(null);
        priceCharge.setFkOrderId(null);
        priceCharge.setBillingType(null);
        priceCharge.setSettlementAmount(null);
        priceCharge.setPriceStatus(null);
        priceCharge.setCalcDate(null);
        priceCharge.setServiceType(null);
        priceCharge.setCreater(null);
        priceCharge.setCreateTime(null);
        priceCharge.setUpdater(null);
        priceCharge.setUpdateTime(null);
        priceChargeService.update(priceCharge);
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PriceCharge> listPriceCharge = priceChargeService.queryList(map);
        if(listPriceCharge != null && listPriceCharge.size() > 0){
            for (PriceCharge priceCharge : listPriceCharge) {
                List<PriceChargeDetail> priceChargeDetails = priceCharge.getPriceChargeDetails();
                if(priceChargeDetails != null && priceChargeDetails.size() > 0){
                    for (PriceChargeDetail priceChargeDetail: priceChargeDetails) {
                        System.out.println(priceChargeDetail.getItemType());
                    }
                }
            }
        }
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer count = priceChargeService.queryCount(map);
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