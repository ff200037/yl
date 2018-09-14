package com.yile.micro.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.Price;
import com.yile.micro.order.mapper.MechanismPriceMapper;
import com.yile.micro.order.mapper.PriceMapper;
import com.yile.micro.order.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Transactional
@Service("priceService")
public class PriceServiceImpl extends BaseService<Price> implements PriceService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private MechanismPriceMapper mechanismPriceMapper;

    @PostConstruct
    public void setMapper() {
        super.setMapper(priceMapper);
    }

    @Override
    public PageInfo<Price> getListData(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Price> list = this.priceMapper.getListData(map);
        PageInfo<Price> pricePage = new PageInfo<Price>(list);
        return pricePage;
    }

    @Override
    public void savePrice(Price price) {
        /*
            校验：同一救援项通用定价模板只能增加一条；
                  同一救援项同一机构在机构定价关联表中只能有一条记录。
         */
        logger.info("price{}", price);
        priceMapper.insert(price);

        //如果存在关联机构则插入定价与机构关联表

    }

    @Override
    public void updatePrice(Price price) {
        /*
            校验：同一救援项通用定价模板只能增加一条；
                  同一救援项同一机构在机构定价关联表中只能有一条记录。
         */
        logger.info("price{}", price);
        priceMapper.update(price);

        //更新定价机构关联表

    }

    @Override
    public void deletePriceById(Long id) {
        priceMapper.deleteById(id);
        //删除定价机构关联表
    }


}
