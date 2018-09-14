package com.yile.micro.user.service;

import com.yile.micro.base.service.IBaseService;
import com.yile.micro.user.bean.Customer;
import com.yile.micro.user.bean.RescueCustomer;
import com.yile.micro.user.bean.RescueRescues;

public interface CustomerService extends IBaseService<Customer> {

	void rescueCustomerSave(RescueCustomer rescueCustomer);// 添加方法（车主表、用户表）

	void rescueCustomerDelete(Long id);// 删除方法（车主表、用户表）

	void rescueCustomerUpdateById(RescueCustomer rescueCustomer);// 更新方法（车主表、用户表）

	void rescueRescuesSave(RescueRescues rescueRescues);// 添加方法（救援人员表、用户表）

	void rescueRescuesDelete(Long id);// 删除方法（救援人员表、用户表）

	void rescueRescuesUpdateById(RescueRescues rescueRescues);// 更新方法（救援人员表、用户表）

}
