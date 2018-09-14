package com.yile.micro.user.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.user.bean.Customer;
import com.yile.micro.user.bean.RescueCustomer;
import com.yile.micro.user.bean.RescueRescues;
import com.yile.micro.user.mapper.CustomerMapper;
import com.yile.micro.user.mapper.RescueCustomerMapper;
import com.yile.micro.user.mapper.RescueRescuesMapper;

@Transactional
@Service("customerService")
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private RescueCustomerMapper rescueCustomerMapper;
	@Autowired
	private RescueRescuesMapper rescueRescuesMapper;

	@PostConstruct
	public void setMapper() {
		super.setMapper(customerMapper);
	}

	// 添加主副表（用户表、车主表）KuangXu
	@Override
	public void rescueCustomerSave(RescueCustomer rescueCustomer) {
		Customer customer = rescueCustomerInfo(rescueCustomer);
		customerMapper.insert(customer);
		rescueCustomer.setFkTcustomerId(customer.getCustomerId());
		rescueCustomerMapper.insert(rescueCustomer);

	}

	// 删除主副表（用户表、车主表）KuangXu
	public void rescueCustomerDelete(Long id) {
		try {
			customerMapper.deleteById(id);
			rescueCustomerMapper.deleteByCustomerId(id);
		} catch (Exception e) {
			System.out.println("执行过程中方法错误");
			throw new RuntimeException();
		}
	}

	// 更新主副表（用户表、车主表）KuangXu
	public void rescueCustomerUpdateById(RescueCustomer rescueCustomer) {

		try {
			Customer customer = rescueCustomerInfo(rescueCustomer);
			rescueCustomer.setFkTcustomerId(Long.valueOf(rescueCustomer.getId()));
			customer.setCustomerId(Long.valueOf(rescueCustomer.getId()));
			customerMapper.update(customer);
			rescueCustomerMapper.updateByCustomerId(rescueCustomer);
		} catch (Exception e) {
			System.out.println("执行过程中方法错误");
			throw new RuntimeException();
		}
	}

	// 封装耦合的实体,KuangXu
	public Customer rescueCustomerInfo(RescueCustomer rescueCustomer) {
		Customer customer = new Customer();
		if (rescueCustomer.getUserName() != null) {
			customer.setUserName(rescueCustomer.getUserName());
		}
		if (rescueCustomer.getSex() != null) {
			customer.setSex(rescueCustomer.getSex());
		}
		if (rescueCustomer.getIdCard() != null) {
			customer.setIdCard(rescueCustomer.getIdCard());
		}
		if (rescueCustomer.getUserNumber() != null) {
			customer.setUserNumber(rescueCustomer.getUserNumber());
		}
		if (rescueCustomer.getLoginPassword() != null) {
			customer.setLoginPassword(rescueCustomer.getLoginPassword());
		}
		customer.setAge(rescueCustomer.getAge());
		customer.setBirthday(rescueCustomer.getBirthday());
		customer.setLoginAccount(rescueCustomer.getLoginAccount());
		customer.setWorkType(rescueCustomer.getWorkType());
		customer.setContactAddress(rescueCustomer.getContactAddress());
		customer.setCreater(rescueCustomer.getCreater());
		customer.setCreateTime(rescueCustomer.getCreateTime());
		customer.setUpdater(rescueCustomer.getUpdater());
		customer.setUpdateTime(rescueCustomer.getUpdateTime());

		return customer;

	}

	// 添加主副表（救援人员表、用户表）
	@Override
	public void rescueRescuesSave(RescueRescues rescueRescues) {
		Customer customer = rescueRescuesInfo(rescueRescues);
		customerMapper.insert(customer);
		rescueRescues.setFkTcustomerId(customer.getCustomerId());
		rescueRescues.setFkRescueStoreId(rescueRescues.getFkRescueStoreId());// 暂时获取，写前端页面再修改
		rescueRescuesMapper.insert(rescueRescues);

	}

	// 删除主副表（救援人员表、用户表）
	@Override
	public void rescueRescuesDelete(Long id) {
		try {
			customerMapper.deleteById(id);
			rescueRescuesMapper.deleteByIdByFkTcustomerId(id);
		} catch (Exception e) {
			System.out.println("执行过程中方法错误");
			throw new RuntimeException();
		}

	}

	//// 更新主副表（救援人员表、用户表）
	@Override
	public void rescueRescuesUpdateById(RescueRescues rescueRescues) {
		try {
			Customer customer = rescueRescuesInfo(rescueRescues);
			customer.setCustomerId(Long.valueOf(rescueRescues.getId()));
			rescueRescues.setFkTcustomerId(rescueRescues.getId());// 暂时获取，写前端页面再修改
			customerMapper.update(customer);
			rescueRescuesMapper.UpdateByFkTcustomerId(rescueRescues);
		} catch (Exception e) {
			System.out.println("执行过程中方法错误");
			throw new RuntimeException();
		}

	}

	// 封装耦合的实体,KuangXu
	public Customer rescueRescuesInfo(RescueRescues rescueRescues) {
		Customer customer = new Customer();
		if (rescueRescues.getUserName() != null) {
			customer.setUserName(rescueRescues.getUserName());
		}
		if (rescueRescues.getSex() != null) {
			customer.setSex(rescueRescues.getSex());
		}
		if (rescueRescues.getIdCard() != null) {
			customer.setIdCard(rescueRescues.getIdCard());
		}
		if (rescueRescues.getUserNumber() != null) {
			customer.setUserNumber(rescueRescues.getUserNumber());
		}
		if (rescueRescues.getLoginPassword() != null) {
			customer.setLoginPassword(rescueRescues.getLoginPassword());
		}
		customer.setAge(rescueRescues.getAge());
		customer.setBirthday(rescueRescues.getBirthday());
		customer.setLoginAccount(rescueRescues.getLoginAccount());
		customer.setWorkType(rescueRescues.getWorkType());
		customer.setContactAddress(rescueRescues.getContactAddress());
		customer.setCreater(rescueRescues.getCreater());
		customer.setCreateTime(rescueRescues.getCreateTime());
		customer.setUpdater(rescueRescues.getUpdater());
		customer.setUpdateTime(rescueRescues.getUpdateTime());

		return customer;

	}

}
