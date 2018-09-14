package com.yile.micro.order.service;

import com.github.pagehelper.PageInfo;
import com.yile.micro.base.service.IBaseService;
import com.yile.micro.order.bean.Price;

import java.util.Map;

public interface PriceService extends IBaseService<Price> {

	PageInfo<Price> getListData(Map<String, Object> map, int pageNum, int pageSize);

    void savePrice(Price price);

    void updatePrice(Price price);

    void deletePriceById(Long id);
}

