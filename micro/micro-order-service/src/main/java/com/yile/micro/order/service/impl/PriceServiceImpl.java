package com.yile.micro.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.MechanismPrice;
import com.yile.micro.order.bean.Price;
import com.yile.micro.order.exception.PriceException;
import com.yile.micro.order.mapper.MechanismPriceMapper;
import com.yile.micro.order.mapper.PriceMapper;
import com.yile.micro.order.service.PriceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Transactional
@Service("priceService")
public class PriceServiceImpl extends BaseService<Price> implements PriceService {

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private MechanismPriceMapper mechanismPriceMapper;

    @PostConstruct
    public void setMapper() {
        super.setMapper(priceMapper);
    }

    @Override
    public PageInfo<Price> getPriceListData(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Price> list = this.priceMapper.queryList(map);
        PageInfo<Price> pricePage = new PageInfo<Price>(list);
        return pricePage;
    }

    @Override
    public void savePrice(Price price) {
    	/*
		        校验：
		        同一救援项通用定价模板只能有一条记录；
		        同一救援项同一机构在机构定价关联表中只能有一条记录。
    	*/
    	String mechanismIdStrs = price.getMechanismIdStrs();
        List<String> mechanismIdList = null;
        if(StringUtils.isNotBlank(mechanismIdStrs)){
            mechanismIdList = Arrays.asList(mechanismIdStrs.split(","));
        }
        
        if(mechanismIdList != null && mechanismIdList.size() > 0){
            saveMechanismPrice(mechanismIdList, price.getRescueObject(), price.getId());
        } else {
            int count = priceMapper.queryCountByRescueObject(price.getRescueObject());
            if(count > 0){
                throw new PriceException("该救援项的定价规则已存在！");
            }
            priceMapper.insert(price);
        }
    }

    @Override
    public void updatePrice(Price price) {
        priceMapper.update(price);

        String mechanismIdStrs = price.getMechanismIdStrs();
        List<String> mechanismIdList = null;
        if(StringUtils.isNotBlank(mechanismIdStrs)){
            mechanismIdList = Arrays.asList(mechanismIdStrs.split(","));
        }

        if(mechanismIdList != null && mechanismIdList.size() > 0){
            Long priceId = price.getId();
            mechanismPriceMapper.deleteByPriceId(priceId);

            saveMechanismPrice(mechanismIdList, price.getRescueObject(), priceId);
        }
    }

    /**
     * 保存定价与救援机构关联信息
     * @param mechanismIdList
     * @param rescueObject
     * @param priceId
     */
    private void saveMechanismPrice(List<String> mechanismIdList, String rescueObject, Long priceId) {
        for(String mechanismIdStr : mechanismIdList){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rescueObject", rescueObject);
            map.put("mechanismId", Long.valueOf(mechanismIdStr));
            int count = mechanismPriceMapper.queryCountByRescueObject(map);
            if(count > 0){
                throw new PriceException("该机构救援项的定价规则已存在！");
            }

            MechanismPrice mechanismPrice = new MechanismPrice();
            mechanismPrice.setPriceId(priceId);
            mechanismPrice.setMechanismId(Long.valueOf(mechanismIdStr));
            mechanismPrice.setCreateTime(new Date());
            mechanismPriceMapper.insert(mechanismPrice);
        }
    }

    @Override
    public void deletePriceById(Long id) {
        priceMapper.deleteById(id);
        mechanismPriceMapper.deleteByPriceId(id);
    }
    
    @Override
    public PageInfo<Map < String, Object >> getMechanismListData(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map < String, Object >> list = mechanismPriceMapper.queryMechanismList(map);
        PageInfo<Map < String, Object >> pricePage = new PageInfo<Map < String, Object >>(list);
        return pricePage;
    }
    
    


}
