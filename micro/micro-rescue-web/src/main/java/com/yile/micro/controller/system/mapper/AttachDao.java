package com.yile.micro.controller.system.mapper;

import com.yile.micro.controller.system.bean.Attach;


public interface AttachDao {

	void saveAttach(Attach attach);

	Attach getAttach(String attachNo);
}