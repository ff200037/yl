package com.yile.micro.order.service.impl;

import com.yile.micro.OrderApplication;
import com.yile.micro.order.bean.Price;
import com.yile.micro.order.service.PriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class PriceServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PriceService priceService;

    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        Price price = priceService.queryById(1L);
        logger.info("" + price.getId());
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        for (int i = 0; i < 25; i++) {
            Price price = new Price();
            price.setPriceName("紧急拖车定价" + (i+1));
            price.setRescueObject("1");
            price.setBasicsCharging(Double.valueOf(100));
            price.setAdditionalCharging("1");
            price.setServiceMileage(50);
            price.setExceedCharging(Double.valueOf(10));
            price.setExceedChargingMileage("2");
            price.setChargingExplain("每次服务里程不超过50公里，超出部分按10元/2公里计算");
            price.setCreater("test");
            price.setCreateTime(new Date());
            price.setUpdater("test");
            price.setUpdateTime(new Date());

            priceService.savePrice(price);
            System.out.println(price.getId());
        }

    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
        priceService.deleteById(1L);
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        Price price = priceService.queryById(null);

        price.setPriceName("紧急拖车定价");
        price.setRescueObject("1");
        price.setBasicsCharging(Double.valueOf(100));
        price.setAdditionalCharging("1");
        price.setServiceMileage(50);
        price.setExceedCharging(Double.valueOf(10));
        price.setExceedChargingMileage("2");
        price.setChargingExplain("每次服务里程不超过50公里，超出部分按10元/2公里计算");
        price.setCreater("test");
        price.setCreateTime(new Date());
        price.setUpdater("test");
        price.setUpdateTime(new Date());

        priceService.update(price);
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Price> listPrice = priceService.queryList(map);
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer count = priceService.queryCount(map);
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