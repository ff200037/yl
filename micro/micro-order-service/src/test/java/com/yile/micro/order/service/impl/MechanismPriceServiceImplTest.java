package com.yile.micro.order.service.impl;

import com.yile.micro.OrderApplication;
import com.yile.micro.order.bean.MechanismPrice;
import com.yile.micro.order.service.MechanismPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MechanismPriceServiceImplTest {
    @Autowired
    MechanismPriceService mechanismPriceService;

    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        MechanismPrice mechanismPrice = mechanismPriceService.queryById(Long.valueOf(5));
        // System.out.println(mechanismPrice.getId());
        //assertEquals(Long.valueOf(1), mechanismPrice.getId());
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        /*MechanismPrice mechanismPrice = new MechanismPrice();
        mechanismPrice.setPriceId(1L);
        mechanismPrice.setMechanismId(1L);
        mechanismPrice.setCreateTime(new Date());
        mechanismPriceService.insert(mechanismPrice);*/
    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
        /*mechanismPriceService.deleteById(Long.valueOf(1));
        MechanismPrice mechanismPrice = mechanismPriceService.queryById(1L);
        assertEquals(null, mechanismPrice);*/
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        /*MechanismPrice mechanismPrice = mechanismPriceService.queryById(2L);
        mechanismPrice.setCreateTime(new Date());
        mechanismPriceService.update(mechanismPrice);*/
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
       /* Map<String, Object> map = new HashMap<String, Object>();
        List<MechanismPrice> listMechanismPrice = mechanismPriceService.queryList(map);
        assertEquals(1, listMechanismPrice.size());*/
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
        /*Map<String, Object> map = new HashMap<String, Object>();
        Integer count = mechanismPriceService.queryCount(map);
        assertEquals(Integer.valueOf(1), count);*/
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